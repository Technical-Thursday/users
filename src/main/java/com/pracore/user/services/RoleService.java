package com.pracore.user.services;


import com.pracore.user.models.Role;
import com.pracore.user.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public List<Role> all() {
        List<Role> roles;
        roles = roleRepository.findAll();
        return roles;
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role, int id) {
        return roleRepository.save(role);
    }

    public boolean delete(int id) {
        Role role = roleRepository.getOne(id);
        roleRepository.delete(role);
        return true;
    }
}
