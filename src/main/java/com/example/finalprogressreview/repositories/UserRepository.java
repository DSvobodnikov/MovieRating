package com.example.finalprogressreview.repositories;

import com.example.finalprogressreview.models.User;

import java.util.List;

public interface UserRepository {

    List<User> get();

    User get (int id);

    User getByUsername(String username);

    void create (User user);

    void  update (User user);

}
