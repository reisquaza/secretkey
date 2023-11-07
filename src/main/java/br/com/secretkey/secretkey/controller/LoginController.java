package br.com.secretkey.secretkey.controller;

import br.com.secretkey.secretkey.dto.LoginDto;
import br.com.secretkey.secretkey.model.Token;
import br.com.secretkey.secretkey.service.LoginService;
import br.com.secretkey.secretkey.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Token> login(@RequestBody final LoginDto loginData) {
        final Token login = loginService.authenticate(loginData);

        return new ResponseEntity<Token>(login, HttpStatus.OK);
    }
}
