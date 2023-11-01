package br.com.secretkey.secretkey.controller;

import br.com.secretkey.secretkey.dto.KeyDto;
import br.com.secretkey.secretkey.model.Key;
import br.com.secretkey.secretkey.service.KeyService;
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

    public KeyController(KeyService keyService) {
        this.keyService = keyService;
    }

    @PostMapping()
    public ResponseEntity<Key> createKey(@Valid @RequestParam(name = "generatePassword", required = false) boolean generatePassword , @RequestBody KeyDto keyData) {
        if (generatePassword) {
            keyData.setPassword(keyService.generateStrongPassword(12));

            final Key key = keyService.createKey(keyData);

            return new ResponseEntity<Key>(key, HttpStatus.CREATED);
        }

        final Key key = keyService.createKey(keyData);

        return new ResponseEntity<Key>(key, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Key>> readKeys(@RequestParam(name = "userId") UUID userId ) {
        final List<Key> keys = keyService.readKeys(userId);

        return new ResponseEntity<List<Key>>(keys, HttpStatus.OK);
    }

}
