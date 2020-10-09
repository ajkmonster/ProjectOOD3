package com.fdmgroup.projectOOD3.view;

import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.fdmgroup.projectOOD3.controller.RegistrationController;
import com.fdmgroup.projectOOD3.exceptions.CustomException;
import com.fdmgroup.projectOOD3.model.FileReadCommand;
import com.fdmgroup.projectOOD3.model.FileWriteCommand;
import com.fdmgroup.projectOOD3.model.ReadCommand;
import com.fdmgroup.projectOOD3.model.User;
import com.fdmgroup.projectOOD3.model.UserFactory;
import com.fdmgroup.projectOOD3.model.WriteCommand;

public class Client {
	
	static Logger gLogger = Logger.getLogger("generalLogger");
	private static Scanner sc = new Scanner(System.in);
	private static ReadCommand readc = new FileReadCommand();
	private static WriteCommand writec = new FileWriteCommand();
	private static UserFactory userfac = new UserFactory();
	private static RegistrationController rc = new RegistrationController(readc,writec,userfac);
	
	public static void main(String[] args) {
		
		prompt();
	
	}
	
	private static void prompt() {
		PropertyConfigurator.configure("src/main/resources/mylog4j.properties");
		while (true) {
			System.out.print("Do you want to \n"
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
		User user;
		try {
			user = rc.readUser(username);
			System.out.println("Username: " + user.getUsername());
			System.out.println("Password: " + user.getPassword());
			System.out.println("Role: " + user.getRole());
		} catch (CustomException e) {
			System.out.println("Error: "+e.getMessage() );
			System.out.println();
		}
		
		
	}

	private static void option1() {
		System.out.println("Please enter a username: ");
		String username = sc.nextLine();
		System.out.println("Please enter a password (8 or more characters): ");
		String password = sc.nextLine();
		System.out.println("Please enter your role (Student or Teacher): ");
		String role = sc.nextLine();
		//Check for blanks
		try {
			rc.registerNewUser(username, password, role);
			gLogger.trace(LocalDateTime.now() +" : Succesfully Created a user.");
		} catch (CustomException e) {
			System.out.println("Error: " + e.getMessage());
			System.out.println();
		}
	}
}
