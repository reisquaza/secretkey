package br.com.secretkey.secretkey.controller;

import br.com.secretkey.secretkey.dto.UserDto;
import br.com.secretkey.secretkey.model.User;
import br.com.secretkey.secretkey.service.KeyService;
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

        public UserController(UserService userService, KeyService keyService) {
            this.userService = userService;
            this.keyService = keyService;
        }


        @PostMapping
        public ResponseEntity<User> createUser(@Valid @RequestBody final UserDto userData) {
            final User user = userService.createUser(userData);

            return new ResponseEntity<User>(user,HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<User>> readUsers() {
            final List<User> users = userService.readUsers();

            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> retrieveUser(@PathVariable String id) {
            final User user = userService.retrieveUser(UUID.fromString(id));

            return new ResponseEntity<User>(user, HttpStatus.OK);
        }

}
