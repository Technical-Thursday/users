package com.pracore.stubs;

import com.pracore.user.models.User;



public class UserStub extends User{
	public UserStub() {
		this.setId(1);
		this.setFirstName("John");
		this.setLastName("Smith");
	}
}