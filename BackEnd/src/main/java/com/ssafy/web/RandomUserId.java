package com.ssafy.web;
import java.util.Random;

/**
 * 13자리 난수 유저 아이디를 만들어 주는 클래스 
 * 치료사 : t+12자리 난수
 * 부모 : p+12자리 난수 */
public class RandomUserId {
	public static String RandomId() {
		int leftLimit = 48; //'0'
		int rightLimit = 122; // 'z'
		int targetStringLenght = 12; //12자리
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit+1)
				                        .filter(i -> (i<=57 || i>=65)&&(i<=90 || i>=97))
				                        .limit(targetStringLenght)
				                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				                        .toString();
		return generatedString;
	}
	public static String makeTheraId() {
		return "t"+RandomId();
	}
	
	public static String makeParentId() {
		return "p"+RandomId();	
	}
}
