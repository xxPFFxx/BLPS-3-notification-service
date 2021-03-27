package com.example.uploadingfiles;

import com.example.uploadingfiles.model.User;
import com.example.uploadingfiles.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public Map<String, String> addUser(@RequestParam String login){
        userService.saveUser(login);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Пользователь с ником " + login + " зарегистрирован");
        return map;
    }

//    @GetMapping("/getUser")
//    public String g
}