package com.dcc.dto;

public class AuthenticationResoponce {
    private  String token;

    public AuthenticationResoponce(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
