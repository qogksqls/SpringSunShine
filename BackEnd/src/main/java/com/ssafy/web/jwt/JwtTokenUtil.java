package com.ssafy.web.jwt;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.ssafy.web.service.RedisService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;


@Slf4j
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
	 
	 //토큰 검증기  
	 public static JWTVerifier getVerifier() {
		 return JWT.require(Algorithm.HMAC512(secretKey.getBytes()))
				 .withIssuer(ISSUER)
				 .build();
	 }
	 
	 
	 //accessToken 설정 
	 public String createAccessToken(String id) {
		 log.debug("test: jwt-crateToken입니다");
		 Long tokenInvalidTime = 1000L * 60 * 30; // 30분
		 return this.getToken(id, tokenInvalidTime);
	 }
	 
	 //refreshToken 설정
	 public String createRefreshToken(String id) {
		 Long tokenInvalidTime = 1000L * 60 * 60 * 24 * 30; // 30일
		 String refreshToken = this.getToken(id, tokenInvalidTime);
		 //redis 에 토큰을 저장 : get id값 
		 redisService.setValues(id, refreshToken, Duration.ofMillis(tokenInvalidTime));
		 return refreshToken;
	 }
	 
	private String getToken(String id, Long tokenInvalidTime) {
		Date expires = JwtTokenUtil.getTokenExpiration(tokenInvalidTime);
		return JWT.create()
				.withSubject(id) // 발행 유저정보
				.withExpiresAt(expires) // 만료시간 
				.withIssuer(ISSUER) // 발행정보
				.withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
				.sign(Algorithm.HMAC512(secretKey.getBytes()));
	}
	 
	public static String getToken(Instant expires, String id) {
		return JWT.create()
		         .withSubject(id)
		         .withExpiresAt(Date.from(expires))
		         .withIssuer(ISSUER)
		         .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
		         .sign(Algorithm.HMAC512(secretKey.getBytes()));
		}
	
	//AccessToken 재발급을 위한 refresh Token 체크 
	public int checkRefreshToken(String id, String refreshToken) {
		String redisRT = redisService.getValues(id);
		if(!refreshToken.equals(redisRT)) return 0; // 유효하지 않은 리프레시 토큰 
		return 1; 
	}

	 // 만료 날짜 : 현재시간 + 유효기간 
	 public static Date getTokenExpiration(Long tokenInvalidTime) {
		 Date now = new Date();
		 return new Date(now.getTime() + tokenInvalidTime);
	 }
	 
	 
	 // ------------- ( 로그아웃 ) 
	 //accessToken 에서 아이디 얻기  
	 public String getAuth(String accessToken) {
		 Claims claims = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(accessToken)
				 .getBody();
		return  claims.getSubject();
	 }
	 
	 //블랙리스트로 redis에 저장할 accessToken 의 남은 만료시간
	 public Long getExpiration(String accessToken) {
	        // accessToken 남은 유효시간
	        Date expiration = Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(accessToken).getBody().getExpiration();
	        // 현재 시간
	        Long now = new Date().getTime();
	        return (expiration.getTime() - now);
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
		    } catch (ExpiredJwtException ex) {
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
