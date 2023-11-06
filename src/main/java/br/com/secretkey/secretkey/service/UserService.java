package br.com.secretkey.secretkey.service;

import br.com.secretkey.secretkey.dto.UserDto;
import br.com.secretkey.secretkey.exception.AppException;
import br.com.secretkey.secretkey.model.User;
import br.com.secretkey.secretkey.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface UserService {
     User createUser(final UserDto userData);

     List<User> readUsers();

     User retrieveUser(final UUID id);

     User updateUser(final UserDto userData, final UUID id);

     void deleteUser(final UUID id);
}
