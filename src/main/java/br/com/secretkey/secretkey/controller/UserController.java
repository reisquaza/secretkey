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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

        private final UserService userService;
        private final KeyService keyService;
        private final TokenService tokenService;


    public UserController(UserService userService, KeyService keyService, TokenService tokenService) {
        this.userService = userService;
        this.keyService = keyService;
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

            final String userId = tokenService.retrieveUserId(token);

            final User user = userService.retrieveUser(UUID.fromString(userId));

            return new ResponseEntity<User>(user, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
            userService.deleteUser(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

}
