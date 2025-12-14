package com.example.finalprogressreview.helpers;

import com.example.finalprogressreview.models.User;
import com.example.finalprogressreview.models.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAdmin(userDto.isAdmin());
        return user;
    }
}
