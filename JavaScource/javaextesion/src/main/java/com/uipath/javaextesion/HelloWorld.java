package com.uipath.javaextesion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
	private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

	
	static {
		  try {
		        InputStream input =new FileInputStream("/uipath/java/HelloWorld_Log4j.properties");
		        Properties prop = new Properties();
		        prop.load(input);
		        PropertyConfigurator.configure(prop);
		    } catch (IOException e) {
		        System.out.println("ERROR: HelloWorld_Log4j.properties");
		    }
	}
	
	
	public static String welcomeMessage(String firstName, String lastName) {
		logger.info("Enter into welcomeMessage(): firstName=" + firstName + ", lastName=" + lastName);
		String message = "Welcome to UiPath!! " + lastName + ", " + firstName;
		logger.info("Exit from welcomeMessage() with message= " + message);
		return message;
	}

	public static void main(String[] args) {
		System.out.println(welcomeMessage("Jagadeesh", "Punnati"));
	}
}
