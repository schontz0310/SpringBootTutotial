package com.develcode.GoFinancies.service;

import com.develcode.GoFinancies.model.entity.User;

public interface UserService {

    User authenticate(String email, String password);

    User saveUser(User user);

    void validateEmail(String Email);
}