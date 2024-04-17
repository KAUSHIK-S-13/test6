package com.logic.spaza.serviceimpl;
import com.logic.spaza.response.AuthResponse;
import com.logic.spaza.response.LoginResponse;
import com.logic.spaza.service.AuthService;
import com.logic.spaza.model.User;

import com.logic.spaza.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public LoginResponse login(AuthResponse response) throws Exception {
		if (response != null) {
			Optional<com.logic.spaza.model.User> user= userRepository.findByUserName(response.getUser());
			if (user.isEmpty()){
				throw new Exception("Not Found");
			}
			return new LoginResponse(modelMapper.map(user.get(), User.class), response.getToken(),
					response.getRefreshToken());
		} else
			throw new Exception("Login Failed.!");
	}

	@Override
	public User getUserInfo(String email) throws Exception {
		Optional<com.logic.spaza.model.User> user = userRepository.findByUserName(email);
		if (user.isEmpty()){
			throw new Exception("User Not found");
		}
		return modelMapper.map(user.get(), User.class);
	}


	@Override
	public com.logic.spaza.model.User getUser(String mobile) throws Exception {
		Optional<com.logic.spaza.model.User> user= userRepository.findByUserName(mobile);
		if(user.isEmpty()){
			throw new Exception("Not found");
		}
		return user.get();
	}
}
