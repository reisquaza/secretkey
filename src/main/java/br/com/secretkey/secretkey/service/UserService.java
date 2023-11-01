package br.com.secretkey.secretkey.service;

import br.com.secretkey.secretkey.dto.UserDto;
import br.com.secretkey.secretkey.exception.AppException;
import br.com.secretkey.secretkey.model.User;
import br.com.secretkey.secretkey.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private  void validateEmailDocument(final UserDto userData) {
        if (userRepository.existsUserByEmail(userData.getEmail())) {
            throw new AppException("Email already in use", HttpStatus.CONFLICT);
        }

        if (userRepository.existsUserByDocument(userData.getDocument())) {
            throw new AppException("Document already in use", HttpStatus.CONFLICT);
        }
    }
    public User createUser(final UserDto userData) {
        validateEmailDocument(userData);

        final User user = new User(userData.getName(), userData.getDocument(), userData.getEmail(), userData.getPassword());

        return userRepository.save(user);
    }

    public List<User> readUsers() {
        return userRepository.findAll();
    }

    public User retrieveUser(final UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }

    public User updateUser(final UserDto userData, final UUID id) {
        validateEmailDocument(userData);

        final User user = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        user.setName(userData.getName());
        user.setPassword(userData.getPassword());
        user.setEmail(userData.getEmail());
        user.setDocument(userData.getDocument());


        return userRepository.save(user);
    }

    public void deleteUser(final UUID id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        user.setActive(false);

        userRepository.save(user);
    }
}
