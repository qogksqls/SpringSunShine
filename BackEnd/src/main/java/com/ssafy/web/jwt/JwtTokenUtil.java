package com.ssafy.web.jwt;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.ssafy.web.service.RedisService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil {
	
	@Autowired
	RedisService redisService;

	private static String secretKey;

	
	 public static final String TOKEN_PREFIX = "Bearer ";
	 public static final String HEADER_STRING = "Authorization";
	 public static final String ISSUER = "sssa606.com";

	 
	 public JwtTokenUtil(@Value("${jwt.secret}")String secretKey) {
		 this.secretKey = secretKey;

	 }
	 
//	 public void setExpirationTime() {
//		 JwtTokenUtil.expirationTime = expirationTime;
//	 }
	 
	 public static JWTVerifier getVerifier() {
		 return JWT.require(Algorithm.HMAC512(secretKey.getBytes()))
				 .withIssuer(ISSUER)
				 .build();
	 }
	 
	 //accessToken 설정 
	 public String createAccessToken(String id) {
		 Long tokenInvalidTime = 1000L * 60 * 60; // 1시간 
		 return this.getToken(id, tokenInvalidTime);
	 }
	 
	 //refreshToken 설정
	 public String createRefreshToken(String id) {
		 Long tokenInvalidTime = 1000L * 60 * 60 * 24 * 3; // 3일
		 String refreshToken = this.getToken(id, tokenInvalidTime);
		 //redis 에 토큰을 저장 
		 redisService.setValues(id, refreshToken, Duration.ofMillis(tokenInvalidTime));
		 return refreshToken;
	 }
	 
	private String getToken(String id, Long tokenInvalidTime) {
//		 Date expires = JwtTokenUtil.getTokenExpiration(expirationTime);
//		 return JWT.create()
//				 .withSubject(id)
//				 .withExpiresAt(expires)
//				 .withIssuer(ISSUER)
//				 .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
//				 .sign(Algorithm.HMAC512(secretKey.getBytes()));
		 Claims claims = Jwts.claims().setSubject(id);
		 Date date = new Date();
		 return Jwts.builder()
				 .setClaims(claims) // 발행 유저 정보 
				 .setIssuedAt(date) // 발행 시간 
				 .setExpiration(new Date(date.getTime()+tokenInvalidTime)) // 토큰 유효 시간 
				 .signWith(SignatureAlgorithm.HS256, secretKey)// 해싱 알고리즘 + 키로 서명
				 .compact();
	}
	 
	public static String getToken(Instant expires, String id) {
		return JWT.create()
		         .withSubject(id)
		         .withExpiresAt(Date.from(expires))
		         .withIssuer(ISSUER)
		         .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
		         .sign(Algorithm.HMAC512(secretKey.getBytes()));
		}

	 
	 public static Date getTokenExpiration(int expirationTime) {
		 Date now = new Date();
		 return new Date(now.getTime() + expirationTime);
	 }
	 
	 
	 
	 
	 
	 //================================================
	 public static void handleError(String token) {
		    JWTVerifier verifier = JWT
		            .require(Algorithm.HMAC512(secretKey.getBytes()))
		            .withIssuer(ISSUER)
		            .build();
		
		    try {
		        verifier.verify(token.replace(TOKEN_PREFIX, ""));
		    } catch (AlgorithmMismatchException ex) {
		        throw ex;
		    } catch (InvalidClaimException ex) {
		        throw ex;
		    } catch (SignatureGenerationException ex) {
		        throw ex;
		    } catch (SignatureVerificationException ex) {
		        throw ex;
		    } catch (TokenExpiredException ex) {
		        throw ex;
		    } catch (JWTCreationException ex) {
		        throw ex;
		    } catch (JWTDecodeException ex) {
		        throw ex;
		    } catch (JWTVerificationException ex) {
		        throw ex;
		    } catch (Exception ex) {
		        throw ex;
		    }
		}
		
		public static void handleError(JWTVerifier verifier, String token) {
		    try {
		        verifier.verify(token.replace(TOKEN_PREFIX, ""));
		    } catch (AlgorithmMismatchException ex) {
		        throw ex;
		    } catch (InvalidClaimException ex) {
		        throw ex;
		    } catch (SignatureGenerationException ex) {
		        throw ex;
		    } catch (SignatureVerificationException ex) {
		        throw ex;
		    } catch (TokenExpiredException ex) {
		        throw ex;
		    } catch (JWTCreationException ex) {
		        throw ex;
		    } catch (JWTDecodeException ex) {
		        throw ex;
		    } catch (JWTVerificationException ex) {
		        throw ex;
		    } catch (Exception ex) {
		        throw ex;
		    }
		}

	 
	
	
}
