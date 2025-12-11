package com.example.finalprogressreview.services;

import java.util.List;
import com.example.finalprogressreview.models.User;
import com.example.finalprogressreview.models.dtos.UserDto;

public interface UserService {

    List<User> get(User user);

    User get(int id);

    User getByUsername (String username);

    void create (User user);

    List<UserDto> getUsers();

}
