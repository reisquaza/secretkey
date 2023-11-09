package br.com.secretkey.secretkey.service.impl;

import br.com.secretkey.secretkey.dto.LoginDto;
import br.com.secretkey.secretkey.exception.AppException;
import br.com.secretkey.secretkey.model.Token;
import br.com.secretkey.secretkey.model.User;
import br.com.secretkey.secretkey.repository.UserRepository;
import br.com.secretkey.secretkey.service.LoginService;
import br.com.secretkey.secretkey.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final TokenService tokenService;


    public LoginServiceImpl(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public Token authenticate(LoginDto loginDto) {
        final User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if (!Objects.equals(loginDto.getPassword(), user.getPassword())) {
            throw new AppException("Invalid email/password", HttpStatus.UNAUTHORIZED);
        }

        String token = tokenService.createToken("userId", user.getId().toString());

        return new Token(token, user.getId());
    }
}
