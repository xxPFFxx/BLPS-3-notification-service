package com.example.uploadingfiles.util;

import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.sun.xml.txw2.annotation.XmlNamespace;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @JacksonXmlProperty(localName = "user")
    @JacksonXmlElementWrapper(localName = "users")
    List<User> userList = new ArrayList<>();

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws IOException {
        // Delete all
        this.userRepository.deleteAll();

        // Create users
        User dan = new User("dan",passwordEncoder.encode("dan123"),"USER","");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User moderator = new User("moderator",passwordEncoder.encode("moderator123"),"MODERATOR","ACCESS_TEST1");

//        @JacksonXmlProperty(localName = "user")
//        @JacksonXmlElementWrapper(localName = "ArrayList")
        List<User> users = Arrays.asList(dan,admin,moderator);

        // Save to db
        this.userRepository.saveAll(users);
        userList.add(dan);
        userList.add(admin);
        userList.add(moderator);
        ObjectMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        JsonGenerator g = mapper.getFactory().createGenerator(new FileOutputStream("complete/src/main/java/com/example/uploadingfiles/security/security.xml"));
//        for(User user : users) {
//            mapper.writeValue(g, user);
//        }
        mapper.writeValue(g, userList);
        String xmlString = mapper.writeValueAsString(dan);
        System.out.println("<users>\n" + xmlString.replaceAll("<permissionList/>", "")
                .replaceAll("<roleList>\\s", "")
                .replaceAll("\\s</roleList>", "")
                .replaceAll("<roleList>.*?</roleList>", "").replaceAll(">\\s{10,}<", ">\n<") + "</users>");
        // Edit this code and read password and roles from data base or files
    }
}