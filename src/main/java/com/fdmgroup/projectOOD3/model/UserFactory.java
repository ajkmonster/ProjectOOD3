package com.fdmgroup.projectOOD3.model;

public class UserFactory {
	
	public User createUser(String user,String pass,String role) {
		return new User(user,pass,role);
		
	}

}
