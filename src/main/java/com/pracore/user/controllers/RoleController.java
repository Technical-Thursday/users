package com.pracore.user.controllers;


import com.pracore.user.models.Role;
import com.pracore.user.services.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> index() {
        List<Role> roles = roleService.all();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role Role) {
        com.pracore.user.models.Role createdRole = roleService.create(Role);
        return ResponseEntity.ok(createdRole);
    }

    @PutMapping("/roles/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable int roleId, @RequestBody Role Role) {
        Role updatedRole = roleService.update(Role, roleId);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable int roleId) {
        Boolean isDeleted = roleService.delete(roleId);
        return ResponseEntity.ok(isDeleted);
    }
}
