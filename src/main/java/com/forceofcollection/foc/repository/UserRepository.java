package com.forceofcollection.foc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forceofcollection.foc.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
