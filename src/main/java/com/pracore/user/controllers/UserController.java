package com.pracore.user.controllers;

import com.pracore.user.models.User;
import com.pracore.user.models.UserRequestBody;
import com.pracore.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> index() {
        List<User> users = userService.all();
        return users;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user, BindingResult result) {
        if(!result.hasErrors()) {
            User createdUser = userService.create(user);
            return ResponseEntity.ok(createdUser);
        }
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody UserRequestBody user) {
        User updatedUser = userService.update(user, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int userId) {
        Boolean isDeleted = userService.delete(userId);
        return ResponseEntity.ok(isDeleted);
    }
}
