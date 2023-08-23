package org.example.controller;

import org.apache.coyote.Response;
import org.example.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.entity.Result;
import org.example.entity.User;
import org.example.service.UserService;

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{page}")//check users
    public ResponseEntity<Result<User>> getAllUsers(int page) {
        Result<User> result = userService.getAllUsers(page);
        return ResponseEntity.ok(result);
    }
    @PostMapping//add users
    public  ResponseEntity<Result<User>> addUser(@RequestBody User user){
        System.out.println("have access postMapping");
        User queryResult = userService.getUserByEmail(user.getEmail());
        System.out.println("is there has account " + queryResult);
        if (queryResult != null) {
            System.out.println("User already exists");
            Result<User> result = new Result<>(HttpStatus.CONFLICT.value(), "User already exists", null);
            result.setMessage("User already exists");
            System.out.println(result.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);

        } else {
            userService.addUser(user);
            System.out.println("User added");
            Result<User> result = new Result<>(200, "success", null);
            return ResponseEntity.ok(result);
        }
    }
    @DeleteMapping("/{email}") // delete users
    public ResponseEntity<Result<User>> deleteUser(@PathVariable String email) {
        User queryResult = userService.getUserByEmail(email);
        System.out.println("have access deleteMapping");
        if (queryResult != null) {
            userService.deleteUserByEmail(email);
            Result<User> result = new Result<>(200, "success", null);
            return ResponseEntity.ok(result);
        } else {
            Result<User> result = new Result<>(HttpStatus.NOT_FOUND.value(), "User not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }
    @PutMapping("/{id}") // update users
    ResponseEntity<Result<User>> updateUser(@PathVariable int id, @RequestBody User user) {
        User queryResult = userService.getUserByID(id);
        System.out.println("have access putMapping");
        System.out.println("is there has account " + queryResult);
        return null;
    }
    @PostMapping("/login")
    public ResponseEntity<Result<User>> userLogin(@RequestBody User user){
boolean isAdmin = userService.checkAdmin(user);
if (isAdmin){
    Result<User> result = new Result<>(200, "success", null);
    return ResponseEntity.ok((result));
}
else{
    Result<User> result = new Result<>(HttpStatus.NOT_FOUND.value(), "have no access", null);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
}

    }
}
