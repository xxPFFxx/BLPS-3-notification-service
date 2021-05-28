package com.example.uploadingfiles.services;

import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.repositories.UserRepository;
import com.example.uploadingfiles.security.UserPrincipal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUser(String login){
        return userRepository.countUsersByUsername(login) > 0;
    }

    public User saveUser(User user){
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user1);
    }

    public boolean checkAuth(User user){
        System.out.println(passwordEncoder.encode(user.getPassword()));
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB == null) return false;
        return userFromDB.getUsername().equals(user.getUsername()) && passwordEncoder.matches(user.getPassword(), userFromDB.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }

    public List<User> getUsers() throws JsonProcessingException {
        ObjectMapper mapper = new XmlMapper();
        return mapper.readValue("complete\\src\\main\\java\\com\\example\\uploadingfiles\\security\\security.xml", new TypeReference<List<User>>() {});
    }
}
