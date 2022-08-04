package com.ssafy.web.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.service.AuthService;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter{
	private AuthService authService;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthService authService ) {
		super(authenticationManager);
		this.authService = authService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException{
		//요청 헤더에서 JWT 토큰 찾기 
		String header =request.getHeader(JwtTokenUtil.HEADER_STRING);
		
		if(header == null || !header.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		try {
            // If header is present, try grab user principal from database and perform authorization
            Authentication authentication = getAuthentication(request);
            // jwt 토큰으로 부터 획득한 인증 정보(authentication) 설정.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            ResponseBodyWriteUtil.sendError(request, response, ex);
            return;
        }
        
        filterChain.doFilter(request, response);
	}

		
		@Transactional(readOnly = true)
		public Authentication getAuthentication(HttpServletRequest request) throws Exception{
			String token = request.getHeader(JwtTokenUtil.HEADER_STRING);
			//요청 헤더에 jwt 토큰이 포함된 경우, 토큰 검증 및 인증 처리 진행 
			if(token != null) {
				JWTVerifier verifier = JwtTokenUtil.getVerifier();
				JwtTokenUtil.handleError(token);
				DecodedJWT decodedJWT = verifier.verify(token.replace(JwtTokenUtil.TOKEN_PREFIX, ""));
				String id = decodedJWT.getSubject(); 
				
				if(id != null) {
					// 계정 정보 아이디로, 실제 디비에 해당 유저가 있는지 조회
					User user= authService.getUserById(id);
				
					if(user!=null) {
						// 디비에 저장되있는 경우, 인증 정보 생성 
						SssUserDetails userDetails = new SssUserDetails(user);
						UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(id, null, userDetails.getAuthorities());
						jwtAuthentication.setDetails(userDetails);
						return jwtAuthentication;
						
						
					}
				}
				
				return null ;
	
			}
			
			return null;
		}
	

}	
