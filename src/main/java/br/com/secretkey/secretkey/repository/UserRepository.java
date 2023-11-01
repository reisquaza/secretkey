package br.com.secretkey.secretkey.repository;

import br.com.secretkey.secretkey.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(UUID id);
    boolean existsUserByDocument(final String document);
    boolean existsUserByEmail(final String email);
}
