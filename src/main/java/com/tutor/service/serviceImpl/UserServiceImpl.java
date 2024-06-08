package com.tutor.service.serviceImpl;

import com.tutor.entity.User;
import com.tutor.repository.UserRepo;
import com.tutor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        List<User> all = userRepo.findAll();
        return all;
    }

    public User createUser(User user) {
        User save = userRepo.save(user);
        return save;
    }

    public void deleteUser(long id){
         userRepo.deleteById(id);
    }

    public User findByEmail(String email){
        User byEmail = userRepo.findByEmail(email);
        return byEmail;
    }

    public List<User> findByName(String name){
        List<User> byName = userRepo.findByName(name);
        return byName;
    }

    public void deleteByEmail(String email){
        userRepo.deleteByEmail(email);
    }



    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepo.save(user);
    }

    public boolean isEmailVerified(String email) {
        User user = userRepo.findByEmail(email);
        return user != null && user.isEmailVerified();
    }
}
