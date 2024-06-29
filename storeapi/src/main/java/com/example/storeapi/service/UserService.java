package com.example.storeapi.service;

import com.example.storeapi.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUserById(User user, Long id);
    void deleteUserById(Long id);
}
