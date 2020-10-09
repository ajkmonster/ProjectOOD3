package com.fdmgroup.projectOOD3.model;

import com.fdmgroup.projectOOD3.exceptions.CustomException;

public interface ReadCommand {

	User readUser(String username) throws CustomException;

	String readAllUsers();
}
