package com.springSecurity.repository;


import com.springSecurity.entities.Role;
import com.springSecurity.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
   @Transactional
    void deleteByEmail(String email);
   User findByRole(Role role);
}
