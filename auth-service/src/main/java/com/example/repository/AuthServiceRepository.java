package com.example.repository;

import com.example.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthServiceRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);
}
