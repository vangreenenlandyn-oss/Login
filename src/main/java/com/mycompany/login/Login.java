/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login;

import java.util.Scanner;
import java.util.ArrayList;

public class Login {

    public Login(String kyle, String test) {
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login user = new Login("Kyle", "Test");

        System.out.println("=== USER REGISTRATION ===");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Cell Phone Number (e.g., +27838968976): ");
        String cellPhone = scanner.nextLine();

        String regMsg = user.registerUser(username, password, cellPhone);
        System.out.println(regMsg);

        if (regMsg.contains("successfully captured")) {
            System.out.println("\n=== USER LOGIN ===");
            System.out.print("Enter Username: ");
            String loginUser = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPass = scanner.nextLine();

            boolean isSuccess = user.loginUser(loginUser, loginPass);
            System.out.println(user.returnLoginStatus(isSuccess));

            if (isSuccess) {
                // Part 2 Feature Implementation
                System.out.println("\nWelcome to QuickChat.");
                boolean running = true;
                ArrayList<Message> sentMessagesList = new ArrayList<>();

                while (running) {
                    System.out.println("\n--- MAIN MENU ---");
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Quit");
                    System.out.print("Choose an option (1-3): ");
                    
                    int choice = -1;
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                    }
                    scanner.nextLine(); // consume newline

                    switch (choice) {
                        case 1:
                            System.out.print("How many messages do you wish to enter? ");
                            int numMessages = scanner.nextInt();
                            scanner.nextLine(); // consume newline

                            for (int i = 0; i < numMessages; i++) {
                                System.out.println("\n--- Message " + (i + 1) + " ---");
                                System.out.print("Enter recipient cell number: ");
                                String recipient = scanner.nextLine();

                                System.out.print("Enter message text (max 250 characters): ");
                                String text = scanner.nextLine();

                                Message msg = new Message(i, recipient, text);

                                if (!msg.checkMessageLength(text)) {
                                    System.out.println("Please enter a message of less than 250 characters. (Exceeds by " + (text.length() - 250) + " characters)");
                                    i--; // retry this message slot
                                    continue;
                                } else {
                                    System.out.println("Message ready to send.");
                                }

                                if (!msg.checkRecipientCell(recipient)) {
                                    System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                                    i--;
                                    continue;
                                }

                                System.out.println("\nChoose an option:");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message to send later");
                                System.out.print("Enter choice (1-3): ");
                                int actionChoice = scanner.nextInt();
                                scanner.nextLine();

                                String actionResult = msg.SentMessage(actionChoice);
                                System.out.println(actionResult);

                                sentMessagesList.add(msg);
                                System.out.println("\n--- Summary ---");
                                System.out.println(msg.printMessageDetails());
                            }
                            System.out.println("\nTotal messages processed: " + sentMessagesList.size());
                            break;

                        case 2:
                            System.out.println("\nComing Soon.");
                            break;

                        case 3:
                            running = false;
                            System.out.println("Exiting application. Goodbye!");
                            break;

                        default:
                            System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    }
                }
            }
        }
        scanner.close();
    }

    private String registerUser(String username, String password, String cellPhone) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean loginUser(String loginUser, String loginPass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean returnLoginStatus(boolean success) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}