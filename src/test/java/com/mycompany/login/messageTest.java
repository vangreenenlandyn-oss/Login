/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login;

/**
 *
 * @author Student
 */



import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.partone.Message;
import org.junit.jupiter.api.Test;

public class messageTest {

    @Test
    public void testLoadTestData() {
        Message msg = new Message();
        msg.loadTestData();
        
        
        assertNotNull(msg.getStoredMessages());
        assertNotNull(msg.getSentMessages());
    }
}


