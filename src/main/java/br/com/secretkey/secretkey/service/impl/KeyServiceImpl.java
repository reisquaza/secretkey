package br.com.secretkey.secretkey.service.impl;

import br.com.secretkey.secretkey.dto.KeyDto;
import br.com.secretkey.secretkey.exception.AppException;
import br.com.secretkey.secretkey.model.Key;
import br.com.secretkey.secretkey.model.User;
import br.com.secretkey.secretkey.repository.KeyRepository;
import br.com.secretkey.secretkey.repository.UserRepository;
import br.com.secretkey.secretkey.service.KeyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
public class KeyServiceImpl implements KeyService {
    private final KeyRepository keyRepository;
    private final UserRepository userRepository;

    public KeyServiceImpl(KeyRepository keyRepository, UserRepository userRepository) {
        this.keyRepository = keyRepository;
        this.userRepository = userRepository;
    }

    public String generateStrongPassword(int length) {
        if (length < 8) {
            throw new AppException("Password length must be at least 8 characters.", HttpStatus.CONFLICT);
        }

        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    public Key createKey(final KeyDto keyDto)  {
        final User user = userRepository.findById(keyDto.getUserId()).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        final Key key = new Key(user, keyDto.getPassword(), keyDto.getReference());

        return keyRepository.save(key);
    }

    public List<Key> readKeys(final UUID userId)  {
        return keyRepository.findByUserId(userId);
    }
 }
