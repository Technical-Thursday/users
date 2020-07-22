package com.pracore.user.controllers;

import com.pracore.user.UserApplication;
import com.pracore.user.models.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void contextLoads() {
        List<User> response = (List<User>) restTemplate.getForEntity("http://localhost:8080", List.class);
        Assert.assertTrue(response != null);
        assert (true);
    }
}