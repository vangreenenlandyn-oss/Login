/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.partone;

import java.util.regex.Pattern;
import java.util.Scanner;

public class PartONE {
    
    
  
    private String registeredUsername;
    private String registeredPassword;
    private String registeredCellNumber;
    private String firstName;
    private String lastName;
    
public PartONE() {}
   public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PartONE app = new PartONE();

        System.out.println("=== USER REGISTRATION ===");
        
        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter Cell Phone Number: ");
        String cellNumber = input.nextLine();

        
        String registrationMessage = app.registerUser(username, password, cellNumber);
        System.out.println(registrationMessage);
    } 

    

    public PartONE(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    
    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;

        boolean hasMinLength = password.length() >= 8;
        boolean hasUppercase = !password.equals(password.toLowerCase()) && password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");

        return hasMinLength && hasUppercase && hasDigit && hasSpecialChar;
    }

    /**
     * Checks if cell phone number contains international country code (+27) 
     * followed by the remaining 9 digits (total 10 digits after country code).
     */
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) return false;
        
        String regex = "^\\+27[0-9]{9}$";
        return Pattern.matches(regex, cellNumber);
    }

   
    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredCellNumber = cellNumber;

        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
    }

    
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (registeredUsername == null || registeredPassword == null) {
            return false;
        }
        return registeredUsername.equals(enteredUsername) && registeredPassword.equals(enteredPassword);
    }

    /**
     * Returns login status response message.
     */
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}