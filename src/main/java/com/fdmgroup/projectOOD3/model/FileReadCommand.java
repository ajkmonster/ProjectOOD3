package com.fdmgroup.projectOOD3.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.fdmgroup.projectOOD3.exceptions.CustomException;

public class FileReadCommand implements ReadCommand {
	
	private static Logger gLogger = Logger.getLogger("generalLogger");
	
	public User readUser(String username) throws CustomException {
		User user = null;

		try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.split(",")[0].equals(username)) {
					user = new User();
					user.setUsername(username);
					user.setPassword(line.split(",")[1]);
					user.setRole(line.split(",")[2]);
				}
			}
		}
		catch (FileNotFoundException e) {
			gLogger.error(LocalDateTime.now() +" : File Not Found Exception: " + e);
		} catch (IOException e) {
			gLogger.error(LocalDateTime.now() +" : IOException could not read line: " + e);
		}
		if(user == null) {
			gLogger.trace(LocalDateTime.now() +" : User was not found.");
			throw new CustomException("User was not found"); 
		}
		return user;
	}
	
	public String readAllUsers() {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
			String newline;
			while ((newline = br.readLine()) != null) {
				line += newline + "\n";
			}
		}
		catch (FileNotFoundException e) {
			gLogger.trace("File Not Found Exception: " + e);
		} catch (IOException e) {
			gLogger.trace("IOException could not read line: " + e);
		}
		return line;
		
	}
	public ArrayList<User> readAllUsersArray() {
		ArrayList<User> users = new ArrayList<User>();
		try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
			String newline = "";
			while ((newline = br.readLine()) != null) {
				User user = new User();
				user.setUsername(newline.split(",")[0]);
				user.setPassword(newline.split(",")[1]);
				user.setRole(newline.split(",")[2]);
				users.add(user);
			}
		}
		catch (FileNotFoundException e) {
			gLogger.trace("File Not Found Exception: " + e);
		} catch (IOException e) {
			gLogger.trace("IOException could not read line: " + e);
		}
		return users;
		
	}

}
