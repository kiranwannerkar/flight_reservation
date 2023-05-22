package com.psa.flight_reservation_app.utility;


import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component // I want to generate its class object using autowired annotation bcz if it is
			// ordinary class u cannot create bean of class using autowired
public class EmailUtil {

	@Autowired
	JavaMailSender mailSender; // it is an interface present inside mail jar

	public void sendItinerary(String toAddress, String filepath) {//toAddress-> to whom i want to send this email,  String filepath-> path from where file should attach
		
		MimeMessage message = mailSender.createMimeMessage();


		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message);
			messageHelper.setTo(toAddress); //it will give comopile time error so hence put it inside the try block
			messageHelper.setSubject("Itinerary of Flight");
			messageHelper.setText("Please find the attached");
			messageHelper.addAttachment("Itinerary", new File(filepath));
			
			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	}