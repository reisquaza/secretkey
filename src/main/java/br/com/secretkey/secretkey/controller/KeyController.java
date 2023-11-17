package br.com.secretkey.secretkey.controller;

import br.com.secretkey.secretkey.dto.KeyDto;
import br.com.secretkey.secretkey.exception.AppException;
import br.com.secretkey.secretkey.model.Key;
import br.com.secretkey.secretkey.service.EncryptService;
import br.com.secretkey.secretkey.service.KeyService;
import br.com.secretkey.secretkey.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/keys")
public class KeyController {
    private final KeyService keyService;
    private final EncryptService encryptService;
    private final TokenService tokenService;

    public KeyController(KeyService keyService, EncryptService encryptService, TokenService tokenService) {
        this.keyService = keyService;
        this.encryptService = encryptService;
        this.tokenService = tokenService;
    }

    @PostMapping()
    public ResponseEntity<Key> createKey(
            @Valid @RequestParam(name = "generatePassword", required = false) boolean generatePassword,
            @RequestBody KeyDto keyData,
            @RequestHeader("Authorization") String bearerToken) throws Exception {
        if (bearerToken == null) {
            throw new AppException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        final String token = bearerToken.substring(7);

        final UUID userId = tokenService.retrieveUserId(token);

        keyData.setUserId(userId);

        if (generatePassword) {
            String generatedPassword = keyService.generateStrongPassword(15);
            String encryptedPassword = encryptService.encrypt(generatedPassword);

            keyData.setPassword(encryptedPassword);

            final Key key = keyService.createKey(keyData);

            key.setPassword(generatedPassword);

            return new ResponseEntity<Key>(key, HttpStatus.CREATED);
        }

        final Key key = keyService.createKey(keyData);

        return new ResponseEntity<Key>(key, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Key>> readKeys(@RequestHeader("Authorization") String bearerToken) throws Exception {
        final String token = bearerToken.substring(7);

        final UUID userId = tokenService.retrieveUserId(token);

        final List<Key> keys = keyService.readKeys(userId);

        for (Key key : keys) {
            String encryptedPassword = key.getPassword();

            String decryptedPassword = encryptService.decrypt(encryptedPassword);

            key.setPassword(decryptedPassword);
        }

        return new ResponseEntity<List<Key>>(keys, HttpStatus.OK);
    }

}
