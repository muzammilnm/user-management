package com.usermanagement.user_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.user_management.entity.User;

public interface UserRepository extends  JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
