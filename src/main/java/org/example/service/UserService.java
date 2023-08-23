package org.example.service;

import org.example.entity.AdminAccount;
import org.example.entity.Result;
import org.example.entity.User;
import org.example.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final Mapper mapper;

    @Autowired
    public UserService(Mapper mapper) {
        this.mapper = mapper;
    }

    public List<User> getAllUsers(int page) {
        return mapper.getAllUsers(page);
    }

    public User getUserByEmail(String email) {
        return mapper.findByEmail(email);
    }

    public void addUser(User user) {
        mapper.save(user);

    }

    public void deleteUserByEmail(String email) {
        mapper.deleteByEmail(email);
    }


    public User getUserByID(int id) {
        return mapper.getUserById(id);
    }

    public boolean checkAdmin(AdminAccount admin) {
        AdminAccount storeAdmin = mapper.findByAccountAndPassword(admin.getAccount(),admin.getPassword());
        return storeAdmin !=null;
    }


    public void updateUserById(int id, User user) {
        mapper.updateUser(user,id);
    }
}
