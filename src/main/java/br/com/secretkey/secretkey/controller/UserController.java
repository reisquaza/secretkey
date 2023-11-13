package br.com.secretkey.secretkey.controller;

import br.com.secretkey.secretkey.dto.UserDto;
import br.com.secretkey.secretkey.exception.AppException;
import br.com.secretkey.secretkey.model.User;
import br.com.secretkey.secretkey.service.KeyService;
import br.com.secretkey.secretkey.service.TokenService;
import br.com.secretkey.secretkey.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

        private final UserService userService;
        private final TokenService tokenService;


    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }


        @PostMapping
        public ResponseEntity<User> createUser(@Valid @RequestBody final UserDto userData) {
            final User user = userService.createUser(userData);

            return new ResponseEntity<User>(user,HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<User> retrieveUser(@RequestHeader("Authorization") String bearerToken) {
            if (bearerToken == null) {
                throw new AppException("Unauthorized", HttpStatus.UNAUTHORIZED);
            }

            final String token = bearerToken.substring(7);

            final UUID userId = tokenService.retrieveUserId(token);

            final User user = userService.retrieveUser(userId);

            return new ResponseEntity<User>(user, HttpStatus.OK);
        }

        @DeleteMapping
        public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String bearerToken) {
            if (bearerToken == null) {
                throw new AppException("Unauthorized", HttpStatus.UNAUTHORIZED);
            }

            final String token = bearerToken.substring(7);

            final UUID userId = tokenService.retrieveUserId(token);

            userService.deleteUser(userId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

}
