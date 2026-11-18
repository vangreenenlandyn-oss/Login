/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.login;

import java.util.ArrayList;
import java.util.Random;

public class Message {
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status;

    private ArrayList<String> sentMessages = new ArrayList<>();
    private ArrayList<String> disregardedMessages = new ArrayList<>();
    private ArrayList<String> storedMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();
    private ArrayList<String> recipients = new ArrayList<>();

    // Constructors
    public Message() {}

    public Message(int messageNumber, String recipient, String messageText, String status) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.status = status;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    //Methods
    public String generateMessageID() {
        Random rand = new Random();
        int id = 100000 + rand.nextInt(900000);
        return String.valueOf(id);
    }

    public String createMessageHash() {
        if (messageText == null || messageText.isEmpty()) {
            return "";
        }
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return (messageID != null ? messageID.substring(0, Math.min(2, messageID.length())) : "") + 
               ":" + firstWord + ":" + lastWord;
    }

    public boolean checkMessageLength(String message) {
        return message != null && message.length() <= 250;
    }

    public boolean checkRecipientCell(String cellNumber) {
        return cellNumber != null && cellNumber.startsWith("+27") && cellNumber.length() == 12;
    }

    public void addMessage(String id, String recipient, String messageText, String flag, String hash) {
        messageIDs.add(id);
        recipients.add(recipient);
        messageHashes.add(hash);
        
        if (flag.equalsIgnoreCase("Sent")) {
            sentMessages.add(messageText);
        } else if (flag.equalsIgnoreCase("Stored")) {
            storedMessages.add(messageText);
        } else if (flag.equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(messageText);
        }
    }

    public String getLongestStoredMessage() {
        if (storedMessages.isEmpty()) return "No stored messages.";
        String longest = storedMessages.get(0);
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    public String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return "Recipient: " + recipients.get(i) + "\nMessage: " + storedMessages.get(i);
            }
        }
        return "Message ID not found.";
    }

    public String searchByRecipient(String recipientQuery) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(recipientQuery)) {
                sb.append(storedMessages.size() > i ? storedMessages.get(i) : "").append(" ");
            }
        }
        return sb.toString().trim();
    }

    public boolean deleteByHash(String hash) {
        int index = messageHashes.indexOf(hash);
        if (index != -1) {
            messageHashes.remove(index);
            messageIDs.remove(index);
            recipients.remove(index);
            return true;
        }
        return false;
    }

    // Getters
    public ArrayList<String> getSentMessages() { return sentMessages; }
    public ArrayList<String> getStoredMessages() { return storedMessages; }
    public ArrayList<String> getDisregardedMessages() { return disregardedMessages; }
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    
    public void loadTestData() {
    // Test 1(Sent)
    addMessage("1", "+27834557896", "Did you get the cake?", "Sent", "10:Did:cake?");
    
    // Test 2
    addMessage("2", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "10:Where:time.");
    
    // Test 3(Disregarded)
    addMessage("3", "+27834484567", "Yohoooo, I am at your gate.", "Disregard", "10:Yohoooo,:gate.");
    
    // Test 4(Sent)
    addMessage("4", "0838884567", "It is dinner time!", "Sent", "10:It:time!");
    
    // Test 5
    addMessage("5", "+27838884567", "Ok, I am leaving without you.", "Stored", "10:Ok,:you.");
}
}