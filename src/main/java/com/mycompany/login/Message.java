/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.login;

import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status;

    // Constructor
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generates a random 10-digit message ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // 1. Checks if the message ID is not more than 10 characters
    public boolean checkMessageID(String id) {
        return id != null && id.length() <= 10;
    }

    // 2. Checks if the recipient cell number is valid (starts with international code and <= 10 characters after code, or standard format validation matching Part 1)
    public boolean checkRecipientCell(String cellNumber) {
        // Reusing/adapting validation criteria from Part 1 (+27 or international prefix, length checks)
        return cellNumber != null && cellNumber.startsWith("+27") && cellNumber.length() == 12;
    }

    // Checks if the message text is less than or equal to 250 characters
    public boolean checkMessageLength(String text) {
        return text != null && text.length() <= 250;
    }

    // 3. Creates and returns the Message Hash
    // Format: First 2 numbers of Message ID : Message Number : First and Last words of the message (ALL CAPS)
    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || messageText == null || messageText.trim().isEmpty()) {
            return "";
        }
        String prefix = messageID.substring(0, 2);
        
        // Clean text and extract first and last words
        String cleanedText = messageText.replaceAll("[^a-zA-Z ]", "").trim();
        String[] words = cleanedText.split("\\s+");
        
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        
        String combinedWords = (firstWord + lastWord).toUpperCase();
        
        return prefix + ":" + messageNumber + ":" + combinedWords;
    }

    // 4. Handles sending, storing, or discarding the message based on user choice (1, 2, or 3)
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                this.status = "Sent";
                return "Message successfully sent.";
            case 2:
                this.status = "Discarded";
                return "Press 0 to delete the message.";
            case 3:
                this.status = "Stored";
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    // 5. Returns all message details formatted
    public String printMessageDetails() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText + "\n" +
               "Status: " + (status != null ? status : "Pending");
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getStatus() { return status; }
}

