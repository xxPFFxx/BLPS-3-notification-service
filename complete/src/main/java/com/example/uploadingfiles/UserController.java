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
    public Map<String, String> addUser(@RequestBody User user){
        userService.saveUser(user);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Пользователь с ником " + user.getLogin() + " зарегистрирован");
        return map;
        //TODO добавить в сущность User пароль и хранить его в зашифрованном виде
        //TODO Разобраться, как эффективнее сохранять видео на сервер (чтобы тратило O(1) оперативы)
        //TODO Убрать с gita пароли и конфиги
        //TODO (возможно) Убрать @Query аннотации и заменить средставми Spring, где возможно
    }

//    @GetMapping("/getUser")
//    public String g
}