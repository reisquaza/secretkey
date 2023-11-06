package br.com.secretkey.secretkey.service;

import br.com.secretkey.secretkey.dto.KeyDto;
import br.com.secretkey.secretkey.exception.AppException;
import br.com.secretkey.secretkey.model.Key;
import br.com.secretkey.secretkey.model.User;
import br.com.secretkey.secretkey.repository.KeyRepository;
import br.com.secretkey.secretkey.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;


public interface KeyService {
    String generateStrongPassword(int length);

    Key createKey(final KeyDto keyData);

    List<Key> readKeys(final UUID userId);
 }
