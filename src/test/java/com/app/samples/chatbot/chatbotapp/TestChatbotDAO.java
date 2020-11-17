package com.app.samples.chatbot.chatbotapp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.app.samples.chatbot.chatbotapp.dao.ChatbotDAO;
import com.app.samples.chatbot.chatbotapp.model.Chatbot;

public class TestChatbotDAO extends ChatbotAppApplicationTests {

	@Autowired
	ChatbotDAO chatbotDAO;
	
	@Test
	public void testCallStoredProcedure() {
		Chatbot chatbot = null;
		
		try {
			chatbot = new Chatbot("test_customer", "test_representative");
			chatbotDAO.callStoredProcedure(chatbot);
		}
		catch(DataAccessException dataAccessException) {
			dataAccessException.printStackTrace();
		}
	}
}
