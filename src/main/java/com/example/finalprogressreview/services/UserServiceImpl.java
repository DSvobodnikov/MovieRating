package com.example.finalprogressreview.services;

import com.example.finalprogressreview.exceptions.EntityDuplicateException;
import com.example.finalprogressreview.exceptions.EntityNotFoundException;
import com.example.finalprogressreview.models.User;
import com.example.finalprogressreview.models.dtos.UserDto;
import com.example.finalprogressreview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> get(User user){
        return userRepository.get();
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public void create(User user) {

        boolean userExists = true;

        try{
            userRepository.getByUsername(user.getUsername());
        } catch(EntityNotFoundException e){
            userExists = false;
        }

        if(userExists){
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }

        userRepository.create(user);
    }

    @Override
    public List<UserDto> getUsers() {
        return List.of();
    }
}
