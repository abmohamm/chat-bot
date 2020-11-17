package com.app.samples.chatbot.chatbotapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.samples.chatbot.chatbotapp.model.Chatbot;

public class ChatbotRowMapper implements RowMapper<Chatbot> {
	
	private static final String CUSTOMER_TEXT = "CUSTOMER_TEXT";
	private static final String REPRESENTATIVE_TEXT = "REPRESENTATIVE_TEXT";

	@Override
	public Chatbot mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Chatbot chatbot = new Chatbot();
		chatbot.setCustomerText(resultSet.getString(CUSTOMER_TEXT));
		chatbot.setRepresentativeText(resultSet.getString(REPRESENTATIVE_TEXT));
		return chatbot;
	}

}
