package com.fdmgroup.projectOOD3.controller;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.projectOOD3.controller.RegistrationController;
import com.fdmgroup.projectOOD3.exceptions.CustomException;
import com.fdmgroup.projectOOD3.model.FileReadCommand;
import com.fdmgroup.projectOOD3.model.UserFactory;
import com.fdmgroup.projectOOD3.model.WriteCommand;
import com.fdmgroup.projectOOD3.model.FileWriteCommand;
import com.fdmgroup.projectOOD3.model.ReadCommand;
import com.fdmgroup.projectOOD3.model.User;

public class RegistrationControllerTest {

	private ReadCommand mockReadCom;
	private WriteCommand mockWriteCom;
	private UserFactory mockUserFac;
	private User mockUser;
	private RegistrationController regCon;
	
	@Before
	public void init() {
		mockReadCom = mock(FileReadCommand.class);
		mockWriteCom = mock(FileWriteCommand.class);
		mockUserFac = mock(UserFactory.class);
		mockUser = mock(User.class);
		regCon = new RegistrationController(mockReadCom,mockWriteCom,mockUserFac);
	}
	@Test
	public void test_RegisterNewUserCallsCreateUser() throws CustomException {
		regCon.registerNewUser("Victor", "Pass", "Student");
		verify(mockUserFac).createUser("Victor", "Pass", "Student");
	}
	@Test
	public void test_RegisterNewUserCallsWriteUser() throws CustomException {
		regCon.registerNewUser("Victor", "Pass", "Student");
		try {
			verify(mockWriteCom).writeUser(mockUserFac.createUser("Victor", "Pass", "Student"));
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_ReadAllUsersCallsReadCommandReadAllUsers() {
		when(mockReadCom.readAllUsers()).thenReturn(new String());
	
		String x = regCon.readAllUsers();
		
		assertEquals(new String(), x);
	}
	
	@Test
	public void test_ReadUser() throws CustomException {
			mockUser = new User();
			when(mockReadCom.readUser("Victor")).thenReturn(mockUser);
			User user = regCon.readUser("Victor");
			assertEquals(mockUser, user);
	
	}
	
	
}
