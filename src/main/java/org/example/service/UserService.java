package org.example.service;

import org.example.entity.Result;
import org.example.entity.User;
import org.example.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Mapper mapper;

    @Autowired
    public UserService(Mapper mapper) {
        this.mapper = mapper;
    }
    public Result<User> getAllUsers(int page) {
        return mapper.getAllUsers(page);
    }

    public User getUserByEmail(String email) {
        return null;
    }

    public void addUser(User user) {
        return;
    }

    public void deleteUserByEmail(String email) {
        return;
    }

    public void updateUserByEmail(String email, User user) {
    return;
    }

    public User getUserByID(int id) {
    return null;
    }

    public boolean checkAdmin(User user) {
        return true;
    }
}
