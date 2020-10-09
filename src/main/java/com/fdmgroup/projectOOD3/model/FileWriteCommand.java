package com.fdmgroup.projectOOD3.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;


public class FileWriteCommand implements WriteCommand{

	private static Logger gLogger = Logger.getLogger("generalLogger");
	
	public void writeUser(User user) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Users.txt",true))) {
			bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole()+ "\n");
		} catch (IOException e) {
			gLogger.error(LocalDateTime.now() +" : IOException could not write to file: " + e);
		}
	}

}
