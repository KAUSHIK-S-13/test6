package com.logic.spaza.response;

import com.logic.spaza.model.User;
import lombok.Getter;

@Getter
public class LoginResponse {
    private User user;

    private String token;

    private String refreshToken;

    public LoginResponse(User user, String token, String refreshToken) {
        this.user = user;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
