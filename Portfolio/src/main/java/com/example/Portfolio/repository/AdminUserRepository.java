package com.example.Portfolio.repository;

import com.example.Portfolio.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {
    Optional<AdminUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
