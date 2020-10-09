package com.fdmgroup.projectOOD3.controller;

import org.apache.log4j.Logger;

import com.fdmgroup.projectOOD3.exceptions.CustomException;
import com.fdmgroup.projectOOD3.model.ReadCommand;
import com.fdmgroup.projectOOD3.model.User;
import com.fdmgroup.projectOOD3.model.UserFactory;
import com.fdmgroup.projectOOD3.model.WriteCommand;

public class RegistrationController implements ReadCommand, WriteCommand {

	private ReadCommand r;
	private WriteCommand w;
	private UserFactory uF;
	private static Logger gLogger = Logger.getLogger("generalLogger");

	public RegistrationController() {
		super();
	}

	public RegistrationController(ReadCommand r, WriteCommand w, UserFactory uF) {
		super();
		this.r = r;
		this.w = w;
		this.uF = uF;
	}

	public ReadCommand getR() {
		return r;
	}

	public void setR(ReadCommand r) {
		this.r = r;
	}

	public WriteCommand getW() {
		return w;
	}

	public void setW(WriteCommand w) {
		this.w = w;
	}

	public UserFactory getuF() {
		return uF;
	}

	public void setuF(UserFactory uF) {
		this.uF = uF;
	}
	
	public void registerNewUser(String username, String password,String role) throws CustomException {
		User newUser = uF.createUser(username, password, role);
		writeUser(newUser);
	}
	
	public String readAllUsers() {
		return r.readAllUsers();
	}

	@Override
	public void writeUser(User user) throws CustomException {
		w.writeUser(user);
	}

	@Override
	public User readUser(String username) throws CustomException {
		return r.readUser(username);
	}

}
