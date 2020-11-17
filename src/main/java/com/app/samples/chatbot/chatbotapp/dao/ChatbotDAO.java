package com.app.samples.chatbot.chatbotapp.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.app.samples.chatbot.chatbotapp.model.Chatbot;

@Repository
public class ChatbotDAO {
	
	Logger logger = Logger.getLogger(ChatbotDAO.class.getName());
	
	private static final String ADD_CHATBOT = "ADD_CHATBOT";
	private static final String P_CUSTOMER_TEXT = "P_CUSTOMER_TEXT";
	private static final String CUSTOMER_TEXT = "CUSTOMER_TEXT";
	private static final String REPRESENTATIVE_TEXT = "REPRESENTATIVE_TEXT";
	private static final String P_REPRESENTATIVE_TEXT = "P_REPRESENTATIVE_TEXT";
	private static final String MESSAGE_RESULT = "MESSAGE_RESULT";
	private static final String CHATBOT_ID = "CHATBOT_ID";
	private static final String GET_CHATBOT_QUERY = "SELECT CUSTOMER_TEXT,REPRESENTATIVE_TEXT FROM CHATBOT";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Chatbot addChatbot(BufferedReader customerBufferedReader, BufferedReader representativeBufferedReader) {
		Chatbot chatbot = null;
		String customerText = "";
		String representativeText = "";
		try {
			chatbot = new Chatbot();
			while((customerText=customerBufferedReader.readLine()) != null &&
					(representativeText = representativeBufferedReader.readLine()) != null) {
				if(!StringUtils.isEmpty(customerText) || !StringUtils.isEmpty(representativeText)) {
					chatbot.setCustomerText(customerText);
					chatbot.setRepresentativeText(representativeText);
					callAddChatbotStoredProcedure(chatbot);
				}
			}
 		}
		catch(DataAccessException dataAccessException) {
			dataAccessException.printStackTrace();
		}		
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		return chatbot;
	}
	
	public Chatbot callAddChatbotStoredProcedure(Chatbot chatbot) {
		SimpleJdbcCall simpleJdbcCall = null;
		Map<String,Object> inParamMap = null;
		SqlParameterSource in = null;
		Map<String, Object> simpleJdbcCallResult = null;
		String storedProcResult = "";
		try {
			inParamMap = new HashMap<String,Object>();
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(ADD_CHATBOT);
			if(chatbot != null) {
				inParamMap.put(P_CUSTOMER_TEXT, chatbot.getCustomerText());
				inParamMap.put(P_REPRESENTATIVE_TEXT, chatbot.getRepresentativeText());
			}
			in = new MapSqlParameterSource(inParamMap);
			simpleJdbcCallResult = simpleJdbcCall.execute(in);
			if(simpleJdbcCallResult != null) {
				storedProcResult = (String)simpleJdbcCallResult.get(MESSAGE_RESULT);
				chatbot.setResultText(storedProcResult);
			}
		}
		catch(DataAccessException dataAccessException ) {
			dataAccessException.printStackTrace();
		}
		return chatbot;
	}
	
	public List<Chatbot> callGetChatbot() {
		Chatbot chatbot = null;
		List<Chatbot> chatbotList = null;
		List<Map<String, Object>> chatbotRecords = null;
		try {
			chatbotRecords = jdbcTemplate.queryForList(GET_CHATBOT_QUERY);
			chatbotList = new ArrayList<>();
			for(Map<String, Object> chatbotRecord : chatbotRecords) {
				chatbot = new Chatbot();
				chatbot.setCustomerText((String)chatbotRecord.get(CUSTOMER_TEXT));
				chatbot.setRepresentativeText((String)chatbotRecord.get(REPRESENTATIVE_TEXT));
				chatbotList.add(chatbot);				
			}
		}
		catch(DataAccessException dataAccessException) {
			dataAccessException.printStackTrace();
		}
		return chatbotList;
	}
}
