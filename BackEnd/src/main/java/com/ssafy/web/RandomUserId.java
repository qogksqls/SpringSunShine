package com.ssafy.web;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 13자리 난수 유저 아이디를 만들어 주는 클래스 
 * 치료사 : t+12자리 난수
 * 부모 : p+12자리 난수 
 * 6자리는 난수 + 6자리는 시간 */
public class RandomUserId {
	public static String RandomId() {
		int leftLimit = 48; //'0'
		int rightLimit = 122; // 'z'
		int targetStringLenght = 6; //12자리
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit+1)
				                        .filter(i -> (i<=57 || i>=65)&&(i<=90 || i>=97))
				                        .limit(targetStringLenght)
				                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				                        .toString();
		return generatedString;
	}
	
	// 현재 날짜 구하기 6자리
	public static String getDate() {
		Date date = new Date();
		
		SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMdd");
		
		String dateresult= date1.format(date).substring(2);
		return dateresult;
		
	}
	public static String makeTheraId() {
		return "t"+RandomId()+getDate();
	}
	
	public static String makeParentId() {
		return "p"+RandomId()+getDate();	
	}
	
	public static String makeChildId() {
		return "c" + RandomId() + getDate();
	}
}
