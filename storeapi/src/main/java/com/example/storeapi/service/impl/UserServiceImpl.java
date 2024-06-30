package com.example.storeapi.service.impl;

import com.example.storeapi.entity.UserEntity;
import com.example.storeapi.exception.UserNotFoundException;
import com.example.storeapi.model.User;
import com.example.storeapi.repository.UserRepository;
import com.example.storeapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        user.setId(userEntity.getId());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        return userEntities
                .stream()
                .map(user -> new User(
                        user.getId(),
                        user.getUsername(),
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public User updateUserById(User updateUser, Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userEntity.setUsername(updateUser.getUsername());
        userEntity.setName(updateUser.getName());
        userEntity.setEmail(updateUser.getEmail());

        UserEntity updatedUserEntity = userRepository.save(userEntity);

        User updatedUser = new User();
        BeanUtils.copyProperties(updatedUserEntity, updatedUser);
        return updatedUser;
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(userEntity);
    }
}
