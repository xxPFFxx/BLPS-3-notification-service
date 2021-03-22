package com.example.uploadingfiles.services;

import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.model.VideoInfo;
import com.example.uploadingfiles.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String login){
        User user = new User(login);
        userRepository.save(user);
    }
}
