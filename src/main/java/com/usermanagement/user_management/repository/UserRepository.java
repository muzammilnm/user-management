package com.usermanagement.user_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.user_management.entity.Users;

public interface UserRepository extends  JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
