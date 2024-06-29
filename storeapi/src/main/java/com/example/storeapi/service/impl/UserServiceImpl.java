package com.example.storeapi.service.impl;

import com.example.storeapi.exception.UserNotFoundException;
import com.example.storeapi.model.User;
import com.example.storeapi.repository.UserRepository;
import com.example.storeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User updateUserById(User updateUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updateUser.getUsername());
                    user.setName(updateUser.getName());
                    user.setEmail(updateUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
