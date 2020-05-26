package com.pracore.user.services;


import com.pracore.user.models.Role;
import com.pracore.user.models.User;
import com.pracore.user.models.UserRequestBody;
import com.pracore.user.repositories.RoleRepository;
import com.pracore.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public List<User> all() {
        List<User> users;
        users = userRepository.findAll();
        return users;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(UserRequestBody user, int id) {
        User user1 = userRepository.getOne(id);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        Role role = roleRepository.getOne(user.getRoleId());
        user1.setRoleId(role);
        userRepository.save(user1);
        return null;
    }

    public boolean delete(int id) {
        User user = userRepository.getOne(id);
        userRepository.delete(user);
        return true;
    }

}
