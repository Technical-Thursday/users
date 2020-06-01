package com.pracore.user.controllers;

import com.pracore.user.models.Hobby;
import com.pracore.user.services.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class HobbyController {

    @Autowired
    HobbyService hobbyService;

    @GetMapping("/hobbies")
    public List<Hobby> index() {
        return  hobbyService.all();
    }
}
