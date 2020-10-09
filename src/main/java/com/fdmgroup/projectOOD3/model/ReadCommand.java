package com.fdmgroup.projectOOD3.model;

public interface ReadCommand {

	User readUser(String username);

	String readAllUsers();
}
