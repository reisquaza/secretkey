package br.com.secretkey.secretkey.service.impl;

import br.com.secretkey.secretkey.dto.UserDto;
import br.com.secretkey.secretkey.exception.AppException;
import br.com.secretkey.secretkey.model.User;
import br.com.secretkey.secretkey.repository.UserRepository;
import br.com.secretkey.secretkey.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private  void validateEmailDocument(final UserDto userDto) {
        if (userRepository.existsUserByEmail(userDto.getEmail())) {
            throw new AppException("Email already in use", HttpStatus.CONFLICT);
        }

        if (userRepository.existsUserByDocument(userDto.getDocument())) {
            throw new AppException("Document already in use", HttpStatus.CONFLICT);
        }
    }
    public User createUser(final UserDto userDto) {
        validateEmailDocument(userDto);

        final User user = new User(userDto.getName(), userDto.getDocument(), userDto.getEmail(), userDto.getPassword());

        return userRepository.save(user);
    }

    public List<User> readUsers() {
        return userRepository.findAll();
    }

    public User retrieveUser(final UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }

    public User updateUser(final UserDto userDto, final UUID id) {
        validateEmailDocument(userDto);

        final User user = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setDocument(userDto.getDocument());


        return userRepository.save(user);
    }

    public void deleteUser(final UUID id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        user.setActive(false);
        user.setDeletedAt(new Date());

        userRepository.save(user);
    }
}
