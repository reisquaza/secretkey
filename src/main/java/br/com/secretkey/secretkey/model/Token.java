package br.com.secretkey.secretkey.model;

import java.util.UUID;

public class Token {
    public String token;
    public UUID userId;

    public Token(String token, UUID userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
