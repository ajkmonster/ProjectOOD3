package com.fdmgroup.projectOOD3.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.fdmgroup.projectOOD3.exceptions.CustomException;


public class FileWriteCommand implements WriteCommand{

	private static Logger gLogger = Logger.getLogger("generalLogger");
	
	public void writeUser(User user) throws CustomException {
		FileReadCommand frc = new FileReadCommand();
		boolean dupUser = false;
		for (User dup : frc.readAllUsersArray()) {
			if (dup.getUsername().equals(user.getUsername())){
				dupUser = true;
				break;
			}
		}
		if (dupUser == false) {
			if (user.getUsername().isBlank() || user.getPassword().isBlank() || user.getRole().isBlank()) {
				gLogger.trace(LocalDateTime.now() +" : Empty answer(s) into either username/password/role");
				throw new CustomException("Empty answer(s) into either username/password/role");
			} else if (user.getPassword().length() <= 7) {
				gLogger.trace(LocalDateTime.now() +" : Password was not long enough");
				throw new CustomException("Password was not long enough");
			} else if (user.getRole().equalsIgnoreCase("Student") || user.getRole().equalsIgnoreCase("Teacher")) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter("Users.txt",true))) {
					bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole()+ "\n");
				} catch (IOException e) {
					gLogger.error(LocalDateTime.now() +" : IOException could not write to file: " + e);
				}
			}
			else {
				gLogger.trace(LocalDateTime.now() +" : Did not enter correct role.");
				throw new CustomException("Did not enter correct role");
			}
			
		} else {
			gLogger.trace(LocalDateTime.now() +" : Duplicate Username.");
			throw new CustomException("Duplicate Username");
		}
	}

}
