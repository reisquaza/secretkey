package br.com.secretkey.secretkey.service;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface TokenService {

    String createToken(String name, String value);

    String retrieveUserId(String token);
}
