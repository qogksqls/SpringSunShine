package com.ssafy.web.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.ssafy.web.model.response.ParentResponse;

import ch.qos.logback.core.status.Status;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String exception = (String)request.getAttribute("exception");
		ErrorCode errorCode;
		
		log.debug("예외 handling : "+exception);
		
		if(exception.equals(ErrorCode.EXPIRED_TOKEN.getErrorCode())) {
			errorCode = ErrorCode.EXPIRED_TOKEN;
			setResponse(response, errorCode);
			return;
		}if(exception.equals(ErrorCode.INVALID_TOKEN.getErrorCode())) {
			errorCode = ErrorCode.INVALID_TOKEN;
			setResponse(response,errorCode);
			return;
		}
	}
	private void setResponse(HttpServletResponse response, ErrorCode errorCode) 
	throws IOException{
		response.setContentType("application/json;charset=UTF-8");
//		response.setStatus(401);
		response.setStatus(Integer.parseInt(errorCode.getErrorCode()));
//		response.getWriter().println("{ \"message\" : \"" + errorCode.getErrorMessage()
//        + "\", \"code\" : \"" +  errorCode.getErrorCode()
//        + ", \"errors\" : [ ] }");
	}
	
}