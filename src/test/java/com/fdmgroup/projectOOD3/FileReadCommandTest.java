package com.fdmgroup.projectOOD3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.projectOOD3.exceptions.CustomException;
import com.fdmgroup.projectOOD3.model.FileReadCommand;
import com.fdmgroup.projectOOD3.model.User;

public class FileReadCommandTest {
	
	FileReadCommand frc;
	User mockUser;
	
	@Before
	public void init() {
		mockUser = mock(User.class);
		frc = new FileReadCommand();
	}
	
	@Test
	public void test_ReadAllUsers() {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
			String newline;
			while ((newline = br.readLine()) != null) {
				line += newline + "\n";
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		assertEquals(line,frc.readAllUsers());
	}
	
	@Test
	public void test_ReadAllUsersArray() {
		ArrayList<User> array = new ArrayList<User>();
		try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
			String newline = "";
			while ((newline = br.readLine()) != null) {
				mockUser = new User();
				mockUser.setUsername(newline.split(",")[0]);
				mockUser.setPassword(newline.split(",")[1]);
				mockUser.setRole(newline.split(",")[2]);
				array.add(mockUser);
			}
		}
		catch (FileNotFoundException e) {
		} catch (IOException e) {
			
		}
		assertEquals(array.size(), frc.readAllUsersArray().size());
	}
	
	@Test
	public void test_ReadUser() {
		mockUser = new User();
		try {
			when(frc.readUser("fefefe")).thenReturn(mockUser);
			assertEquals(mockUser,frc.readUser("fefefe"));
		} catch (CustomException e) {
		}
	}
	
	@Test (expected = CustomException.class)
	public void test_ReadUserCustom() throws CustomException {
			frc.readUser("fefefe");
	}
	
}
