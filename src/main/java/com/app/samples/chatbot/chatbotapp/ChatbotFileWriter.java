package com.app.samples.chatbot.chatbotapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import com.app.samples.chatbot.chatbotapp.model.Chatbot;

public class ChatbotFileWriter {

	Logger logger = Logger.getLogger(ChatbotFileWriter.class.getName());
	
	public File createChatbotFile() {
		
		File file = null;
		try {
			file = new File("chatbot.txt");
			if(file.createNewFile()) {
				System.out.println("File Location : " + file.getAbsolutePath());
				logger.info("File Location : " + file.getPath());
			}
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		return file;
	}
	
	public File writeChatbotConversation(File chatbotFile, List<Chatbot> chatbots) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(chatbotFile,true);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(Chatbot chatbot : chatbots) {
				if(chatbot != null) {
						bufferedWriter.write("Customer : " + chatbot.getCustomerText());
						bufferedWriter.newLine();
						bufferedWriter.write("Representative : " + chatbot.getRepresentativeText());
						bufferedWriter.newLine();
				}
				bufferedWriter.newLine();
				bufferedWriter.newLine();				
			}
			bufferedWriter.close();
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		return chatbotFile;
	}
}
