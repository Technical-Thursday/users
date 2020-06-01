package com.pracore.user.services;

import com.pracore.user.models.Hobby;
import com.pracore.user.repositories.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyService {

    @Autowired
    HobbyRepository hobbyRepository;

    public List<Hobby> all() {
        return hobbyRepository.findAll();
    }
}
