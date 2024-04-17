package com.logic.spaza.service;

import org.springframework.stereotype.Service;
import com.logic.spaza.response.AuthResponse;
import com.logic.spaza.response.LoginResponse;
import com.logic.spaza.model.User;

@Service
public interface AuthService {
	LoginResponse login(AuthResponse response) throws Exception;

    User getUserInfo(String userName) throws Exception;

	com.logic.spaza.model.User getUser(String username) throws Exception;
}
