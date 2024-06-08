package com.tutor.repository;

import com.tutor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByName(String name);
    void deleteByEmail(String email);
}
