package br.com.secretkey.secretkey.service;

import br.com.secretkey.secretkey.dto.LoginDto;
import br.com.secretkey.secretkey.model.Token;

public interface LoginService {
    Token authenticate(LoginDto loginDto);
}
