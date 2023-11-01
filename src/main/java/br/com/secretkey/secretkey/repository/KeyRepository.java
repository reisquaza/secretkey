package br.com.secretkey.secretkey.repository;

import br.com.secretkey.secretkey.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface KeyRepository extends JpaRepository<Key, Long> {
    List<Key> findByUserId(UUID userId);
}
