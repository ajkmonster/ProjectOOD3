package com.fdmgroup.projectOOD3;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.fdmgroup.projectOOD3.exceptions.CustomException;
import com.fdmgroup.projectOOD3.model.FileWriteCommand;
import com.fdmgroup.projectOOD3.model.User;

public class FileWriteCommandTest {

	FileWriteCommand fwc;
	User mockUser;
	
	@Before
	public void init() {
		mockUser = new User();
		fwc = new FileWriteCommand();
	}
	
	@Test (expected = CustomException.class)
	public void test_Duplicate() throws CustomException {
		mockUser.setUsername("victor");
		fwc.writeUser(mockUser);
	}

	@Test (expected = CustomException.class)
	public void test_EmptyCheck() throws CustomException {
		mockUser.setUsername("victor1");
		mockUser.setPassword("password1234");
		mockUser.setRole("");
		fwc.writeUser(mockUser);
	}
	
	@Test (expected = CustomException.class)
	public void test_PasswordTooShort() throws CustomException {
		mockUser.setUsername("victor1");
		mockUser.setPassword("passwor");
		mockUser.setRole("Student");
		fwc.writeUser(mockUser);
	}
	
	@Test (expected = CustomException.class)
	public void test_RoleIsIncorrect() throws CustomException {
		mockUser.setUsername("victor1");
		mockUser.setPassword("password");
		mockUser.setRole("stud");
		fwc.writeUser(mockUser);
	}
	
	@Rule
	public TemporaryFolder temporaryFolder= new TemporaryFolder();
	
	@Test
	public void test_WriteUser() throws Exception {
	    // 1 - create temporary file using Rule
	    File inFile = temporaryFolder.newFile("testIn.txt");

	    List<String> lines = new ArrayList<String>() {{
	        add("Victor,");
	        add("Password,");
	        add("Student");
	    }};

	    // 2 - write lines to the file
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(inFile))) {
	        for (String line : lines) {
	            bw.write(line);
	        }
	    }

	    // 3 - convert file to Path and pass it to the service call
	    Path p = inFile.toPath();
	    
	    FileReader fr = new FileReader(p.toFile());
	    BufferedReader br = new BufferedReader(fr);
	    
	    String[] extracted = br.readLine().split(",");

	    assertEquals(3, extracted.length);
	}
}
