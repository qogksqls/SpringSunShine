package com.ssafy.web.service;

import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	
	private MimeMessage createMessage(String to) throws Exception{
		MimeMessage message = javaMailSender.createMimeMessage();
		
		message.addRecipients(Message.RecipientType.TO, to);
		message.setSubject("봄날의 햇살 회원가입 인증 번호 입니다.");
		message.setText("이메일 인증코드 : "+createKey());
		
		message.setFrom(new InternetAddress("봄날의햇살"));
		
		return message;
	}
	
	//인증코드
	public static String createKey() {
		StringBuffer key = new StringBuffer();
		Random random = new Random();
		
		for(int i=0; i<6; i++) {
			key.append(random.nextInt(10));
		}
		return key.toString();
	}
	
	public void sendMail(String to) throws Exception{
		MimeMessage mimeMessage= createMessage(to);
		try {
			javaMailSender.send(mimeMessage);
			
		}catch(MailException me) {
			me.printStackTrace();
			throw new IllegalAccessException();
		}
	}
	
	
}
