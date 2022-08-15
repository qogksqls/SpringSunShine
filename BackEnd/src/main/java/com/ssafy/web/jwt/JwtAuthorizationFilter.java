package com.ssafy.web.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssafy.web.db.entity.User;
import com.ssafy.web.db.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	@Autowired
	UserRepository userRepo;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepo) {
		super(authenticationManager);
		this.userRepo = userRepo;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
					throws ServletException, IOException{
		//요청 헤더에서 값 찾기 
		String header =request.getHeader(JwtTokenUtil.HEADER_STRING);
		log.debug("test: filter-토큰찾기");
		log.debug("헤더값 : "+header);
		//올바른 토큰 형태의 값인지 확인하기 ( 토큰이 아니라면 ? -> 그냥 넘겨 )
		if(header == null || !header.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
	
		
		//토큰 o
		try {
			//앞부분을 떼어내고 토큰값 추출, 인증정보얻어오기
            Authentication authentication = getAuthentication(request);
            // jwt 토큰으로 부터 획득한 인증 정보(authentication) 설정.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(TokenExpiredException e){ 
        	log.debug("filter-유효기간만료");
//        	throw new JwtException("Expired token");
        	request.setAttribute("exception", ErrorCode.EXPIRED_TOKEN.getErrorCode());
        }catch(Exception ex) {
        	log.debug("filter-유효하지않은토큰");
//        	throw new IOException("wrong token");
//        	sendErrorResponse(response, "올바르지 않은 형식의 토큰입니다.");
        	request.setAttribute("exception", ErrorCode.INVALID_TOKEN.getErrorCode());
        }
		
		
      
        chain.doFilter(request, response);
	}

		
		@Transactional(readOnly = true)
		public Authentication getAuthentication(HttpServletRequest request) throws Exception{
			String token = request.getHeader(JwtTokenUtil.HEADER_STRING);
			log.debug("test: filter-토큰검사 및 인증정보 추출");
				//토큰 검증기 받아오기 
				JWTVerifier verifier = JwtTokenUtil.getVerifier();
				//토큰에 이상이 있는지 ? 있으면 예외 ------> 유효기간 만료시 , 토큰 재발급 하기 위한 리프레시 토큰 받아야함 
				JwtTokenUtil.handleError(token);
				//실제 검증 + 복호화(내부적으로 복호화도 해줌)
				DecodedJWT decodedJWT = verifier.verify(token.replace(JwtTokenUtil.TOKEN_PREFIX, ""));
				log.debug("decode header : "+decodedJWT);
				String id = decodedJWT.getSubject(); 
				
				if(id != null) {
					// 계정 정보 아이디로, 실제 디비에 해당 유저가 있는지 조회
					User user= userRepo.findUserById(id).orElseThrow(()->new Exception("해당 id 정보를 갖는 회원이 없습니다."));
						// 디비에 저장되있는 경우, 인증 정보 생성 
						log.debug("test: 정상 user 입니다");
						SssUserDetails userDetails = new SssUserDetails(user);
						Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
						return authentication;
				}
				
				return null ;
		}
		
		private void sendErrorResponse(HttpServletResponse response, String message) throws IOException{
			response.setCharacterEncoding("utf-8");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().println(message);
		}
	

}	
