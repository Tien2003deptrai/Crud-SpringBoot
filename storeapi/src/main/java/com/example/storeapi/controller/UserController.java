package com.example.storeapi.controller;

import com.example.storeapi.model.User;
import com.example.storeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/v1/users")
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    User newUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @PutMapping("/{id}")
    User updateUser(@RequestBody User updateUser, @PathVariable Long id) {
        return userService.updateUserById(updateUser, id);
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User has been deleted";
    }
}
