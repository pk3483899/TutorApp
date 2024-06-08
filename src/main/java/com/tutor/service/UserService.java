package com.tutor.service;

import com.tutor.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public User createUser(User user);
    public void deleteUser(long id);
    public User findByEmail(String email);
    public List<User> findByName(String name);
    public void deleteByEmail(String email);
    public User getUserByEmail(String email);
    public void verifyEmail(User user);
    public boolean isEmailVerified(String email);
}
