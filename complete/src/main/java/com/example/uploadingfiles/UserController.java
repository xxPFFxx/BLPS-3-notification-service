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
    public User addUser(@RequestBody User user){
          return userService.saveUser(user);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("message", "Пользователь с ником " + user.getLogin() + " зарегистрирован");
//        return map;
    }

    @PostMapping("/checkAuth")
    public Map<String, String> checkAuth(@RequestBody User user){
        HashMap<String, String> map = new HashMap<>();

        if (userService.checkAuth(user)) map.put("message", "Пользователь с ником " + user.getUsername() + " зарегистрирован");
        else map.put("message", "Пользователь с ником " + user.getUsername() + " не зарегистрирован или пароль неверный");
        return map;
    }
}