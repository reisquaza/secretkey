package br.com.secretkey.secretkey.service;

import br.com.secretkey.secretkey.dto.LoginDto;
import br.com.secretkey.secretkey.model.Token;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface LoginService {
    Token authenticate(LoginDto loginDto);
}
