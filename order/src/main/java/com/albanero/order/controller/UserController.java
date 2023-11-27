package com.albanero.order.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.albanero.order.entity.User;
import com.albanero.order.service.UserService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {



    // creating a logger
    Logger logger = LoggerFactory.getLogger(UserController.class);

    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
       return userService.saveUser(user);
    }


    @PostMapping("/addUsers")
    public  List<User> addUsers(@RequestBody List<User> users){
        return userService.saveUsers(users);
    }

        @GetMapping("/userName")
    public User getUserByName(@RequestParam("name") String userName){

        return userService.getUser(userName);
    }

    @GetMapping("/userByEmail")
    public User getUserByEmail(@RequestParam("email") String email){
        return userService.getUserByEmail(email);
    }


    @GetMapping("/userByEmailAndName")
    public User getUserByEmail(@RequestParam("email") String email,@RequestParam("name") String name){
        return userService.getUserByEmailAndName(email,name);
    }

}
