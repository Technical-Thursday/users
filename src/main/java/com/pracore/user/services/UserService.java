package com.pracore.user.services;


import com.pracore.user.models.Role;
import com.pracore.user.models.User;
import com.pracore.user.models.UserRequestBody;
import com.pracore.user.repositories.RoleRepository;
import com.pracore.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private HobbyService hobbyService;


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

    /**
     * /propagation/required
     */
    @Transactional
    public void transaction1() {
        this.createUser("1");
        hobbyService.transaction1();
    }

    /**
     * /propagation/supported
     */
    public void transaction2() {
        this.createUser("2");
        hobbyService.transaction2();
    }

    /**
     * /propagation/unsupported
     */
    @Transactional
    public void transaction3() {
        this.createUser("3");
        hobbyService.transaction3();
        throw new RuntimeException();
    }



    /**
     * /propagation/requiredNew
     */
    @Transactional
    public void transaction4() {
        this.createUser("4");
        hobbyService.transaction4();
        throw new RuntimeException();
    }

    /**
     * /propagation/mandatory
     */

    public void transaction5() {
        this.createUser("5");
        hobbyService.transaction5();
    }

    /**
     * /propagation/never
     */
    @Transactional(propagation = Propagation.NEVER)
    public void transaction6() {
        this.createUser("6");
        hobbyService.transaction6();
    }

    public void createUser(String lastName) {
        User user = new User();
        user.setFirstName("transaction");
        user.setLastName(lastName);
        userRepository.save(user);
    }

}
