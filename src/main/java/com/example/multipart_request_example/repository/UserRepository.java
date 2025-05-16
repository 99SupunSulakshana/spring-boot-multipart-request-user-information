package com.example.multipart_request_example.repository;

import com.example.multipart_request_example.entity.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
}
