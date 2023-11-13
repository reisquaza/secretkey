package br.com.secretkey.secretkey.service;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.UUID;

public interface TokenService {

    String createToken(String name, String value);

    UUID retrieveUserId(String token);
}
