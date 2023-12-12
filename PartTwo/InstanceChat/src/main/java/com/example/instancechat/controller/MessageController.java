package com.example.instancechat.controller;

import com.example.instancechat.request.MessageForm;
import com.example.instancechat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(
            @RequestHeader("AccessToken") String accessToken,
            @ModelAttribute MessageForm sendMessageRequest) {

        // Validate access token
        if (!validateAccessToken(accessToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token");
        }

        // Get the sender's username from the access token
        String senderUsername = getUsernameFromAccessToken(accessToken);

        // Get the receiver's username from the request body
        String receiverUsername = sendMessageRequest.getReceiverUsername();

        // Check if sender is a friend of the receiver
        if (!areFriends(senderUsername, receiverUsername)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sender is not a friend of the receiver");
        }

        // Create a message
        Message message = new Message(sendMessageRequest.getContent(), senderUsername, System.currentTimeMillis());

        // Send the message and get the status
        int status = messageService.sendMessage(receiverUsername, message);

        // Return the appropriate response based on the status
        switch (status) {
            case 1:
                return ResponseEntity.ok("Message sent. Receiver is online.");
            case 2:
                return ResponseEntity.ok("Message queued. Receiver is offline.");
            case 3:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sender is not a friend of the receiver.");
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error.");
        }
    }

    // Helper methods for validating access token, getting username, and checking friendship
    // (These methods should be implemented based on your application's logic)

    private boolean validateAccessToken(String accessToken) {
        // Implementation for validating the access token
        // Return true if the access token is valid, false otherwise
        // You may check the expiration time, signature, etc.
        return true;
    }

    private String getUsernameFromAccessToken(String accessToken) {
        // Implementation for extracting the username from the access token
        // Return the username associated with the access token
        return "username"; // Replace with actual implementation
    }

    private boolean areFriends(String senderUsername, String receiverUsername) {
        // Implementation for checking if sender is a friend of the receiver
        // Return true if they are friends, false otherwise
        // You may use the friends list from the User entity
        return true; // Replace with actual implementation
    }
}
