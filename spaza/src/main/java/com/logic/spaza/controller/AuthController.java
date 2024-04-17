package com.logic.spaza.controller;
import com.logic.spaza.securityconfig.JWTUtils;
import com.logic.spaza.response.AuthResponse;
import com.logic.spaza.response.LoginResponse;
import com.logic.spaza.response.LoginRequest;
import com.logic.spaza.service.AuthService;
import com.logic.spaza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtils jwtUtils;

@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
		if (loginRequest == null) {
			throw new Exception("Bad Request");
		}
		AuthResponse response = authenticate(loginRequest.getUserName(), loginRequest.getPassword());
		return authService.login(response);
	}

	@PostMapping("/refresh")
	public AuthResponse refreshToken(@RequestParam String token) throws Exception {
		String userName = jwtUtils.getUsernameFromToken(token);
		if (userName==null){
			throw new Exception("Bad Request");
		}
		User user= authService.getUserInfo(userName);
		if (user==null){
			throw new Exception("Bad Request");
		}
		return  getRefreshInfo(userName);
	}
	

	
	public AuthResponse authenticate(String username, String password) throws Exception {
		String token = null;
		String refreshToken = null;

		try {
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			token = jwtUtils.generateToken(authentication);
			refreshToken = jwtUtils.refreshToken(token);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		return new AuthResponse(token, refreshToken, jwtUtils.getUsernameFromToken(token));
	}

	public AuthResponse getRefreshInfo(String username) throws Exception {
		String token = null;
		String refreshToken = null;

		try {
			Map<String,Object> claims=new HashMap<>();
			token = jwtUtils.getAccessToken(claims, username);
			refreshToken = jwtUtils.refreshToken(token);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		return new AuthResponse(token, refreshToken, jwtUtils.getUsernameFromToken(token));
	}
}