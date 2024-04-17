package com.logic.spaza.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse implements Serializable {
    public AuthResponse(String token, String refreshToken, String user) {
        this.token = token;
        this.user = user;
        this.refreshToken = refreshToken;
    }

    private static final long serialVersionUID = 8286210631647330695L;

    private String user;
    
    private String token;

    private String refreshToken;
}
