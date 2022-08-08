package com.ssafy.web.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.service.AuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter{
	private AuthService authService;
	
	@Autowired
	SssUserDetailService sssUserDetailService;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthService authService ) {
		super(authenticationManager);
		this.authService = authService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException{
		//요청 헤더에서 JWT 토큰 찾기 
		String header =request.getHeader(JwtTokenUtil.HEADER_STRING);
		log.debug("test: filter-doFilter입니다");
		//헤더가 비어있거나 token 값이 아니면, 그냥 넘겨버리기 
		if(header == null || !header.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
	
		try {
			//토큰의 형식을 가지고 있는 헤더라면, 앞부분을 떼어내고 토큰값 추출, 인증정보얻어오기
            Authentication authentication = getAuthentication(request);
            // jwt 토큰으로 부터 획득한 인증 정보(authentication) 설정.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception ex) {
            ResponseBodyWriteUtil.sendError(request, response, ex);
            return;
        }
        
        filterChain.doFilter(request, response);
	}

		
		@Transactional(readOnly = true)
		public Authentication getAuthentication(HttpServletRequest request) throws Exception{
			String token = request.getHeader(JwtTokenUtil.HEADER_STRING);
			log.debug("test: filter-getAuthentication입니다");
			//요청 헤더에 jwt 토큰이 포함된 경우, 토큰 검증 및 인증 처리 진행 
			if(token != null) {
				//토큰 검증기 받아오기 
				JWTVerifier verifier = JwtTokenUtil.getVerifier();
				//토큰에 이상이 있는지 ? 있으면 예외 ------> 유효기간 만료시 , 토큰 재발급 하기 위한 리프레시 토큰 받아야함 
				JwtTokenUtil.handleError(token);
				//실제 검증 + 복호화(내부적으로 복호화도 해줌)
				DecodedJWT decodedJWT = verifier.verify(token.replace(JwtTokenUtil.TOKEN_PREFIX, ""));
				
				String id = decodedJWT.getSubject(); 
				
				if(id != null) {
					// 계정 정보 아이디로, 실제 디비에 해당 유저가 있는지 조회
					User user= authService.getUserById(id);
				    
					if(user!=null) {
						// 디비에 저장되있는 경우, 인증 정보 생성 
						log.debug("test: filter-user!=null입니다");
//						SssUserDetails userDetails = new SssUserDetails(user);
						UserDetails userDetails = sssUserDetailService.loadUserByUsername(id);
//						UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(id, null, userDetails.getAuthorities());
						UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
						jwtAuthentication.setDetails(userDetails);
						return jwtAuthentication;
					}
				}
				
				return null ;
	
			}
			
			return null;
		}
	

}	
