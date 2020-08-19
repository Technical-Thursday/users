package com.pracore.user.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.pracore.stubs.RoleStub;
import com.pracore.stubs.UserStub;
import com.pracore.user.models.Role;
import com.pracore.user.models.User;
import com.pracore.user.models.UserRequestBody;
import com.pracore.user.repositories.RoleRepository;
import com.pracore.user.repositories.UserRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
	
	@Spy
	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;

	@Mock
	private HobbyService hobbyService;

	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Test
	public void test_getAllUser() {
		User dummyUser1 = new User();
		dummyUser1.setId(1);
		dummyUser1.setFirstName("user1");
		dummyUser1.setLastName("last1");
		
		User dummyUser2 = new User();
		dummyUser2.setId(2);
		dummyUser2.setFirstName("user2");
		dummyUser2.setLastName("last2");

		List<User> dummyUsers =  new ArrayList<>();
		dummyUsers.add(dummyUser1);
		dummyUsers.add(dummyUser2);
		
		when(userRepository.findAll()).thenReturn(dummyUsers);

		List<User> users = userService.all();
		
		assertEquals(users, dummyUsers);

	}

	@Test
	public void testSaveUser() {

		User dummyUser = new User();
		dummyUser.setFirstName("Test User");
		dummyUser.setLastName("1");

		User savedUser = new User();
		savedUser.setFirstName(dummyUser.getFirstName());
		savedUser.setLastName(dummyUser.getLastName());
		savedUser.setId(1);

		when(userRepository.save(dummyUser)).thenReturn(savedUser);

		assertEquals(userService.create(dummyUser), savedUser);
	}

	@Test
	public void testUpdateUser() {
		// Prepare data
		UserRequestBody rUserRequestBody = new UserRequestBody();
		rUserRequestBody.setId(1);
		rUserRequestBody.setFirstName("Test");
		rUserRequestBody.setLastName("Update");
		rUserRequestBody.setRoleId(1);

		User dbExistingUser = new UserStub();

		Role uRole = new RoleStub();

		User resultUser =  new User();
		resultUser.setId(rUserRequestBody.getId());
		resultUser.setFirstName(rUserRequestBody.getFirstName());
		resultUser.setLastName(rUserRequestBody.getLastName());
		resultUser.setRoleId(uRole);

		// Mock Dependancies
		when(roleRepository.getOne(Integer.valueOf(1))).thenReturn(uRole);
		when(userRepository.getOne(Integer.valueOf(1))).thenReturn(dbExistingUser);
		when(userRepository.save(resultUser)).thenReturn(resultUser);

		// Run it and test it
		assertEquals(userService.update(rUserRequestBody, 1), resultUser);
	}
}