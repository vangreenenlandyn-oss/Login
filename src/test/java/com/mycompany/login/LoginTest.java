/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.partone;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        Message.resetTotalMessages();
        message = new Message();
        @BeforeEach

}
    }

    
    @Test
    public void testMessageLength_Success() {
        String validMessage = "Hi Mike, can you join us for dinner tonight?";
        String expected = "Message ready to send.";
        String actual = message.checkMessageLength(validMessage);
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageLength_Failure() {
        
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMsg.append("a");
        }
        
        String expected = "Message exceeds 250 characters by 10; please reduce the size.";
        String actual = message.checkMessageLength(longMsg.toString());
        assertEquals(expected, actual);
    }

    
    @Test
    public void testCheckRecipientCell_Success() {
        String expected = "Cell phone number successfully captured.";
        String actual = message.checkRecipientCell("+27718693002");
        assertEquals(expected, actual);
    }

    @Test
    public void testCheckRecipientCell_Failure() {
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        String actual = message.checkRecipientCell("08575975889");
        assertEquals(expected, actual);
    }

    
    @Test
    public void testCreateMessageHash_TestCase1() {
        // Test Data: Recipient: +27718693002, Message: "Hi Mike, can you join us for dinner tonight?"
        Message testMsg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        testMsg.setMessageId("0012345678"); // Override ID to start with 00 for test verification
        
        String expected = "00:0:HITONIGHT";
        String actual = testMsg.createMessageHash();
        assertEquals(expected, actual);
    }

    
    @Test
    public void testMessageID_Created() {
        String generatedId = message.generateRandomMessageId();
        assertNotNull(generatedId);
        assertTrue(message.checkMessageID(generatedId));
        assertEquals(10, generatedId.length());
    }

        @Test
    public void testSentMessage_SendMessage() {
        String expected = "Message successfully sent.";
        String actual = message.SentMessage(1);
        assertEquals(expected, actual);
        assertEquals(1, Message.returnTotalMessagess());
    }

    @Test
    public void testSentMessage_DisregardMessage() {
        String expected = "Press O to delete the message.";
        String actual = message.SentMessage(2);
        assertEquals(expected, actual);
        assertEquals(0, Message.returnTotalMessagess());
    }

    @Test
    public void testSentMessage_StoreMessage() {
        String expected = "Message successfully stored.";
        String actual = message.SentMessage(3);
        assertEquals(expected, actual);
        assertEquals(0, Message.returnTotalMessagess());
    }

    

