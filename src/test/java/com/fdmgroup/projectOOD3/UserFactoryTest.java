package com.fdmgroup.projectOOD3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.fdmgroup.projectOOD3.model.User;
import com.fdmgroup.projectOOD3.model.UserFactory;

public class UserFactoryTest {
	
	private User mockUser;
	private UserFactory uf;
	
	@Before
	public void init() {
		mockUser = mock(User.class);
		uf = new UserFactory();
	}
	
	@Test
	public void test_CreateUser() {
		mockUser = new User("victor", "Hellohello", "Student");
		User user = uf.createUser("victor", "Hellohello", "Student");
		assertEquals(mockUser.getUsername(), user.getUsername());
		assertEquals(mockUser.getPassword(), user.getPassword());
		assertEquals(mockUser.getRole(), user.getRole());
	}

}
