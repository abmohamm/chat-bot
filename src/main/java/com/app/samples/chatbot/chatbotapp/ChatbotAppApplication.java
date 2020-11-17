package com.app.samples.chatbot.chatbotapp;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatbotAppApplication.
 */
@SpringBootApplication
@EnableSwagger2
public class ChatbotAppApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ChatbotAppApplication.class, args);
	}

	/**
	 * Swagger configuration.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
						.select()
						.paths(PathSelectors.ant("/chatbot/*"))
						.apis(RequestHandlerSelectors.basePackage("com.app.samples.chatbot"))
						.build()
						.apiInfo(apiInfo());
	}
	
	/**
	 * Api info.
	 *
	 * @return the api info
	 */
	private ApiInfo apiInfo() {
		return new ApiInfo("CHATBOT API",
							"Sample API for Chatbot Communciation", 
							"1.0",
							"Free to Use",
							new Contact("Abid Mohd",
										"https://github.com/abmohamm", 
										"abid3022@gmail.com"),
							"API License",
							"https://github.com/abmohamm",
							Collections.emptyList());
	}
}
