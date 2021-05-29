package com.example.uploadingfiles.services;

import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.repositories.UserRepository;
import com.example.uploadingfiles.security.UserPrincipal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
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

    public User addUser(User user) throws IOException {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRoles("USER");
        user1.setActive(1);
        userRepository.save(user1);
        List<User> users = getUsers();
        users.add(user1);
        String xmlString = "";
        ObjectMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        for (User user2: users){
            xmlString += mapper.writeValueAsString(user2);
        }
        xmlString = "<users>\n" + xmlString.replaceAll("<permissionList/>", "")
                .replaceAll("<roleList>\\s", "")
                .replaceAll("\\s</roleList>", "")
                .replaceAll("<roleList>.*?</roleList>", "").replaceAll(">\\s{10,}<", ">\n<") + "</users>";

        FileWriter writer = new FileWriter("complete\\src\\main\\java\\com\\example\\uploadingfiles\\security\\security.xml", false);
        //FileWriter writer = new FileWriter("complete/src/main/resources/security.xml", false);
        writer.write(xmlString);
        writer.close();
        return user1;
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

    public List<User> getUsers() throws IOException {
        ObjectMapper mapper = new XmlMapper();
        InputStream inputStream = new FileInputStream("complete\\src\\main\\java\\com\\example\\uploadingfiles\\security\\security.xml");
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
        return mapper.readValue(inputStream, typeReference);
    }
}
