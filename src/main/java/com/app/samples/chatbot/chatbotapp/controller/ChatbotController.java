package com.app.samples.chatbot.chatbotapp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.samples.chatbot.chatbotapp.ChatbotFileWriter;
import com.app.samples.chatbot.chatbotapp.dao.ChatbotDAO;
import com.app.samples.chatbot.chatbotapp.model.Chatbot;

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {
	
	@Autowired
	private ChatbotDAO chatbotDAO;
	
	private static String CLASS_NAME = "ChatbotController";
	
	Logger logger = Logger.getLogger(ChatbotController.class.getName());
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testEndPoint() {
		String METHOD_NAME = "testEndPoint()";
		logger.log(Level.INFO, "<======================executing method : " + METHOD_NAME + "======================>" + CLASS_NAME);
		return "test response";
	}
	
//	@PostMapping("/upload_multipe_files")
//	@Produces("text/plain")
	@RequestMapping(value = "/upload_multipe_files", method = RequestMethod.POST, produces = "text/plain")
	public ResponseEntity<String> uploadTextFile(@RequestParam("customer") MultipartFile customerMultipartFile,
												 @RequestParam("representative") MultipartFile representativeMultipartFile) {
		List<Chatbot> chatbotList = null;
		InputStream customerInputStream = null;
		InputStream representativeInputStream = null;
		BufferedReader customerBufferedReader = null;
		BufferedReader representativeBufferedReader = null;
		ChatbotFileWriter chatbotFileWriter = null;
		File chatbotFile = null;
		ResponseEntity<String> response = null;
		//Log Files Information and save that data to Database
		try {			
			logger.info("File name is : "+customerMultipartFile.getOriginalFilename());
			logger.info("File name is : "+representativeMultipartFile.getOriginalFilename());
			if(customerMultipartFile != null && representativeMultipartFile != null) {
				customerInputStream = customerMultipartFile.getInputStream();
				customerBufferedReader = new BufferedReader(new InputStreamReader(customerInputStream));
				representativeInputStream = representativeMultipartFile.getInputStream();
				representativeBufferedReader = new BufferedReader(new InputStreamReader(representativeInputStream));
				if(customerBufferedReader != null && representativeBufferedReader != null) {
					chatbotDAO.addChatbot(customerBufferedReader, representativeBufferedReader);
					//Now Pull out Data from Database
					chatbotList = chatbotDAO.callGetChatbot();
				}
			}
			//Implement Logics to Write Chatbot List to a File here
			//1.Create File First
			chatbotFileWriter = new ChatbotFileWriter();
			chatbotFile = chatbotFileWriter.createChatbotFile();
			logger.info("File Location getAbsolutePath =======> 1 : " + chatbotFile.getAbsolutePath());
			logger.info("File Location getPath =======> 1 : " + chatbotFile.getPath());			
			//2.Pass Data along with the File to write
			chatbotFile = chatbotFileWriter.writeChatbotConversation(chatbotFile,chatbotList);
			logger.info("File Location getAbsolutePath =======> 2 : " + chatbotFile.getAbsolutePath());
			logger.info("File Location getPath =======> 2 : " + chatbotFile.getPath());
		
		} 
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			try {
				if(customerInputStream != null) {
					customerInputStream.close();
				}
				if(representativeInputStream != null) {
					representativeInputStream.close();
				}
				if(customerBufferedReader != null) {
					customerBufferedReader.close();
				}
				if(representativeBufferedReader != null) {
					representativeBufferedReader.close();
				}
			}
			catch(IOException exception) {
				exception.printStackTrace();
			}
		}
		return response;
	}
}
