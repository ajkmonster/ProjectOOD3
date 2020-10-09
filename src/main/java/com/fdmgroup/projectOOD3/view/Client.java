package com.fdmgroup.projectOOD3.view;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.fdmgroup.projectOOD3.controller.RegistrationController;
import com.fdmgroup.projectOOD3.model.FileReadCommand;
import com.fdmgroup.projectOOD3.model.FileWriteCommand;
import com.fdmgroup.projectOOD3.model.ReadCommand;
import com.fdmgroup.projectOOD3.model.User;
import com.fdmgroup.projectOOD3.model.UserFactory;
import com.fdmgroup.projectOOD3.model.WriteCommand;

public class Client {
	
	private static Scanner sc = new Scanner(System.in);
	private static Logger gLogger = Logger.getLogger("generalLogger");
	private static ReadCommand readc = new FileReadCommand();
	private static WriteCommand writec = new FileWriteCommand();
	private static UserFactory userfac = new UserFactory();
	private static RegistrationController rc = new RegistrationController(readc,writec,userfac);
	
	public static void main(String[] args) {
		prompt();
	}
	
	private static void prompt() {
		while (true) {
			System.out.println("Do you want to \n"
					+ "1) Enter in a new user\n"
					+ "2) Read a user\n"
					+ "3) See the list of users\n"
					+ "4) Quit\n"
					+ "PLEASE ENTER A NUMBER BELOW\n"
					+ "> ");
			String ans = sc.nextLine();
			if (ans.equals("4")) {
				break;
			}
			switch (ans) {
				case "1":
					option1();
					break;
				case "2":
					option2();
					break;
				case "3":
					option3();
					break;
				default :
					gLogger.trace(LocalDateTime.now() +" : Wrong Input from User.");
					System.out.println("Wrong input. Try again.");
					break;
					
			}
		}
	}

	private static void option3() {
		System.out.println(rc.readAllUsers());
		
	}

	private static void option2() {
		System.out.println("Please enter a username: ");
		String username = sc.nextLine();
		User user = rc.readUser(username);
		System.out.println("Username: " + user.getUsername());
		System.out.println("Password: " + user.getPassword());
		System.out.println("Role: " + user.getRole());
		
	}

	private static void option1() {
		System.out.println("Please enter a username: ");
		String username = sc.nextLine();
		System.out.println("Please enter a password (more than 8 characters): ");
		String password = sc.nextLine();
		System.out.println("Please enter your role (Student or Teacher): ");
		String role = sc.nextLine();
		//Check for blanks
		if (username.isBlank() || password.isBlank() || role.isBlank()) {
			gLogger.trace(LocalDateTime.now() +" : Empty answer(s) into either username/password/role");
			System.out.println("Blanks were found. Re-try.");
			option1();
		} else if (password.length() <= 7) {
			gLogger.trace(LocalDateTime.now() +" : Password was not long enough");
			System.out.println("Password was not long enough. Re-try.");
			option1();
		} else if (role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("Teacher")) {
			gLogger.trace(LocalDateTime.now() +" : Succesfully Created a user.");
			rc.registerNewUser(username, password, role);
		}
		else {
			gLogger.trace(LocalDateTime.now() +" : Did not enter correct role.");
			System.out.println("Did not enter correct role.");
			option1();
		}
	}
}
