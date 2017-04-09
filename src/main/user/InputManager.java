package main.user;

import java.util.Scanner;

public class InputManager {
	
	//Prints a prompt message, and returns a String response
	public String getResponse(String prompt){
		Scanner sc = new Scanner(System.in);
		
		System.out.println(prompt);
		System.out.print("==> ");
		String response = sc.nextLine();
		return response;
	}
}
