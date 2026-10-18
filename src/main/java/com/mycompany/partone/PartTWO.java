/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.partone;

import com.mycompany.login.Message;
import javax.swing.JOptionPane;

public class PartTWO {

    public static void main(String[] args) {
        // Simple authentication check step before entering menu
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat", "QuickChat", JOptionPane.INFORMATION_MESSAGE);

        boolean running = true;

        while (running) {
            String menu = "Select an option:\n"
                        + "1) Send Messages\n"
                        + "2) Show recently sent messages\n"
                        + "3) Quit";

            String input = JOptionPane.showInputDialog(null, menu, "QuickChat Menu", JOptionPane.QUESTION_MESSAGE);

            if (input == null) break; // User closed dialog

            switch (input.trim()) {
                case "1":
                    handleSendMessages();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.", "Recent Messages", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "3":
                    running = false;
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please select 1, 2, or 3.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    private static void handleSendMessages() {
        String numInput = JOptionPane.showInputDialog(null, "How many messages would you like to send?", "Message Count", JOptionPane.QUESTION_MESSAGE);
        if (numInput == null) return;

        int totalToEnter;
        try {
            totalToEnter = Integer.parseInt(numInput.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Message msgHandler = new Message();

        for (int i = 1; i <= totalToEnter; i++) {
            // Get Cell Number
            String recipient = JOptionPane.showInputDialog(null, "Enter recipient cell phone number (Message " + i + " of " + totalToEnter + "):", "+27...");
            if (recipient == null) break;

            boolean cellCheck = msgHandler.checkRecipientCell(recipient);
            if (!cellCheck.equals("Cell phone number successfully captured.")) {
                JOptionPane.showMessageDialog(null, cellCheck, "Recipient Error", JOptionPane.ERROR_MESSAGE);
                i--; // Retry current message iteration
                continue;
            }

            // Get Message Body
            String messageText = JOptionPane.showInputDialog(null, "Enter your message (max 250 characters):", "Message Text", JOptionPane.QUESTION_MESSAGE);
            if (messageText == null) break;

            boolean lengthCheck = msgHandler.checkMessageLength(messageText);
            if (!lengthCheck.equals("Message ready to send.")) {
                JOptionPane.showMessageDialog(null, lengthCheck, "Message Error", JOptionPane.ERROR_MESSAGE);
                i--; // Retry current message iteration
                continue;
            }

            
            Message currentMsg = new Message(i, recipient, messageText);

            // Ask user for action 
            String actionMenu = "Message Details:\n" + currentMsg.printMessages() + "\n\nChoose an action:\n"
                              + "1. Send Message\n"
                              + "2. Disregard Message\n"
                              + "3. Store Message";

            String choiceInput = JOptionPane.showInputDialog(null, actionMenu, "Send Message Option", JOptionPane.QUESTION_MESSAGE);
            if (choiceInput == null) continue;

            try {
                int choice = Integer.parseInt(choiceInput.trim());
                String statusResponse = currentMsg.SentMessage(choice);
                JOptionPane.showMessageDialog(null, statusResponse, "Status", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid action choice.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        
        JOptionPane.showMessageDialog(null, "Total messages sent so far: " + Message.returnTotalMessagess(), "Summary", JOptionPane.INFORMATION_MESSAGE);
    }
}
