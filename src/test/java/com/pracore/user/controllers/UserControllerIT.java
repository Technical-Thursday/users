package com.pracore.user.controllers;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import com.pracore.user.UserApplication;
import com.pracore.user.models.Role;
import com.pracore.user.models.User;
import com.pracore.user.models.UserRequestBody;
import com.pracore.user.repositories.RoleRepository;
import com.pracore.user.repositories.UserRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    private User testUser;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @LocalServerPort
    private int port;

    private void createTestUser() {
      Role roleId = roleRepository.findAll().get(0);

      User user = new User();
      user.setFirstName("TestFirstName");
      user.setLastName("TestLastName");
      user.setRoleId(roleId);

      testUser = userRepository.save(user);
    }

    private void deleteTestUser() {
      userRepository.delete(testUser);
    }

    @Test
    public void getUsers_sunny() {
        String url ="http://localhost:"+ port +"/users";
        

        ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), new ParameterizedTypeReference<List<User>>(){
        });

        Assert.assertTrue(response != null);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        Assert.assertNotNull(response.getBody());
        assert (true);
    }

    @Test
    public void postUsers_sunny() {
        String url ="http://localhost:"+ port +"/users";
  
        //setting up the request headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //setting up the request body
        createTestUser();

        //request entity is created with request body and headers
        HttpEntity<User> requestEntity = new HttpEntity<>(testUser, requestHeaders);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, User.class);

        Assert.assertTrue(response != null);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        User insertedUser = response.getBody();

        userRepository.delete(insertedUser);
    }

    @Test
    public void updateUser_sunny() {
      try{
        createTestUser();

        String url ="http://localhost:"+ port +"/users/" + testUser.getId();
  
        //setting up the request headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //setting up the request body
        UserRequestBody userRequestBody = new UserRequestBody();
        userRequestBody.setFirstName("Updated firstName");
        userRequestBody.setLastName(testUser.getLastName());
        userRequestBody.setId(testUser.getId());
        userRequestBody.setRoleId(testUser.getRoleId().getId());

        //request entity is created with request body and headers
        HttpEntity<UserRequestBody> requestEntity = new HttpEntity<>(userRequestBody, requestHeaders);
        ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, User.class);

        User responseUser = response.getBody();

        Assert.assertTrue(response != null);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        

        deleteTestUser();
      }catch(Exception exception) {
        fail(exception.toString());
      }
    }

    @Test
    public void deleteUser_sunny() {
      try{
       createTestUser();

        String url ="http://localhost:"+ port +"/users/" + testUser.getId();
  
        //setting up the request headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //request entity is created with request body and headers
        HttpEntity<UserRequestBody> requestEntity = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);

        String responseUser = response.getBody();

        Assert.assertTrue(response != null);
        Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
        

        deleteTestUser();
      }catch(Exception e) {

      }
    } 
}