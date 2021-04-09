package com.example.uploadingfiles.services;

import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUser(String login){
        return userRepository.countUsersByLogin(login) > 0;
    }

    public User saveUser(User user){
        User user1 = new User();
        user1.setLogin(user.getLogin());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user1);
    }

    public boolean checkAuth(User user){
        System.out.println(passwordEncoder.encode(user.getPassword()));
        User userFromDB = userRepository.findUserByLogin(user.getLogin());
        if (userFromDB == null) return false;
        return userFromDB.getLogin().equals(user.getLogin()) && passwordEncoder.matches(user.getPassword(), userFromDB.getPassword());
    }
}
