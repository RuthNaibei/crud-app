package com.demosite.crudapp.controller;

import com.demosite.crudapp.UserUpdateDTO;
import com.demosite.crudapp.model.User;
import com.demosite.crudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUser(){
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }
   @DeleteMapping(path="{userid}")
    public void deleteUser(@PathVariable("userid") Long userid){
        userService.deleterUser(userid);
   }
   @PutMapping(path ="{userid}")
    public void updateUser(
           @PathVariable("userid") Long userid,
           @RequestBody UserUpdateDTO userUpdateDTO){
       userService.updateUser(userid,userUpdateDTO);
   }
}
