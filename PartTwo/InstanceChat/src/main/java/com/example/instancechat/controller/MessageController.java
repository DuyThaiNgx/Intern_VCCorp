package com.example.instancechat.controller;

import com.example.instancechat.entity.AccessToken;
import com.example.instancechat.entity.Message;
import com.example.instancechat.request.MessageForm;
import com.example.instancechat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class MessageController {
//    @Autowired
//    private AccessTokenService accessTokenService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private MessageService messageService;
//    @Autowired
//    private FileService fileService;
//    private static final Map<String, CompletableFuture<Message>> completableFutureMap = new HashMap<>();
//    //phương thức chỉ chấp nhận dữ liệu đầu vào được gửi theo định dạng multipart/form-data
//    @PostMapping(path = "/send", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<?> sendMessage(@RequestHeader("AccessToken") String accessTokenHeader, @ModelAttribute MessageForm request) {
//        AccessToken accessToken = accessTokenService.checkAccessToken(accessTokenHeader);
//        if (accessToken != null && accessTokenService.isValidAccessToken(accessToken.getToken())) {
//            if (userService.isFriends(accessToken.getUsername(), request.getUsername())) {
//                Message message = request.getMessage();
//                if (message.getContent() instanceof MultipartFile file) {
//                    String originalFilename = file.getOriginalFilename();
//                    String storageDirectory = "D:\\STUDY\\Intern_VCCorp\\PartTwo\\InstanceChat\\src\\main\\resources\\storage";
//                    try {
//                        File destFile = new File(storageDirectory + File.separator + originalFilename);
//                        file.transferTo(destFile);
//                        String urlDownload = "http://localhost:8080/file?filename=" + originalFilename;
//                        message.setContent(urlDownload);
//                        fileService.addPermission(accessToken.getUsername(), request.getUsername(), originalFilename);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                message.setSender(accessToken.getUsername());
//                message.setTimeSend(System.currentTimeMillis());
//                if (completableFutureMap.containsKey(request.getUsername())) {
//                    completableFutureMap.get(request.getUsername()).complete(request.getMessage());
//                    return ResponseEntity.ok(1 + " - Message sent");
//                } else {
//                    LinkedList<Message> messageQueue = messageService.getMessageQueue(request.getUsername());
//                    messageQueue.add(message);
//                    messageService.saveMessage(request.getUsername(), messageQueue);
//                    return ResponseEntity.ok(2 + " - User is not online");
//                }
//            } else {
//                return ResponseEntity.ok(3 + " - User is not your friend");
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//
//    @GetMapping("/get")
//    public ResponseEntity<?> getNewMessage(@RequestHeader("AccessToken") String accessTokenHeader) {
//        AccessToken accessToken = accessTokenService.checkAccessToken(accessTokenHeader);
//        if (accessToken != null && accessTokenService.isValidAccessToken(accessToken.getToken())) {
//            LinkedList<Message> messageQueue = messageService.getMessageQueue(accessToken.getUsername());
//            if (!messageQueue.isEmpty()) {
//                messageService.removeMessageQueue(accessToken.getUsername());
//                return ResponseEntity.ok(messageQueue);
//            } else {
//                CompletableFuture<Message> completableFuture = completableFutureMap.computeIfAbsent(
//                        accessToken.getUsername(), k -> new CompletableFuture<>()
//                );
//                try {
//                    Message message = completableFuture.get(10, TimeUnit.SECONDS);
//                    completableFutureMap.remove(accessToken.getUsername());
//                    return ResponseEntity.ok(message);
//                } catch (TimeoutException e) {
//                    return ResponseEntity.ok(List.of());
//                } catch (Exception e) {
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//                }
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//    @GetMapping("/file")
//    public ResponseEntity<?> getFile(@RequestHeader("AccessToken") String accessTokenHeader, @RequestParam String filename) {
//        AccessToken accessToken = accessTokenService.checkAccessToken(accessTokenHeader);
//        if (accessToken != null && accessTokenService.isValidAccessToken(accessToken.getToken())) {
//            if (fileService.isExistFile(filename)) {
//                if(fileService.hasPermission(accessToken.getUsername(), filename)){
//                    File file = new File("src/main/resources/static/storage/" + filename);
//                    Resource resource = new FileSystemResource(file);
//                    return ResponseEntity.ok(resource);
//                }
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }




    /*
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
     */
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private FileService fileService;

    private static final Map<String, CompletableFuture<Message>> completableFutureMap = new HashMap<>();

    @PostMapping(path = "/sendMessage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> sendMessage(@RequestHeader("AccessToken") String accessTokenHeader, @ModelAttribute MessageForm messageDto) {
        AccessToken accessToken = accessTokenService.checkAccessToken(accessTokenHeader);
        if (accessToken != null) {
            if (friendService.isFriend(accessToken.getUsername(), messageDto.getUsername())) {
                Message message = messageDto.getMessage();
                if (message.getContent() instanceof MultipartFile file) {
                    String originalFilename = file.getOriginalFilename();
                    String storageDirectory = "D:\\STUDY\\Intern_VCCorp\\PartTwo\\InstanceChat\\src\\main\\resources\\storage";
                    try {
                        File destFile = new File(storageDirectory + File.separator + originalFilename);
                        file.transferTo(destFile);
                        String urlDownload = "http://localhost:8080/file?filename=" + originalFilename;
                        message.setContent(urlDownload);
                        fileService.addPermission(accessToken.getUsername(), messageDto.getUsername(), originalFilename);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                message.setSender(accessToken.getUsername());
                message.setTimeSend(System.currentTimeMillis());
                if (completableFutureMap.containsKey(messageDto.getUsername())) {
                    completableFutureMap.get(messageDto.getUsername()).complete(messageDto.getMessage());
                    return ResponseEntity.ok(1);
                } else {
                    LinkedList<Message> messageQueue = messageService.getMessageQueue(messageDto.getUsername());
                    messageQueue.add(message);
                    messageService.saveMessage(messageDto.getUsername(), messageQueue);
                    return ResponseEntity.ok(2);
                }
            } else {
                return ResponseEntity.ok(3);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/getNewMessage")
    public ResponseEntity<?> getNewMessage(@RequestHeader("AccessToken") String accessTokenHeader) {
        AccessToken accessToken = accessTokenService.checkAccessToken(accessTokenHeader);
        if (accessToken != null) {
            LinkedList<Message> messageQueue = messageService.getMessageQueue(accessToken.getUsername());
            if (!messageQueue.isEmpty()) {
                messageService.removeMessageQueue(accessToken.getUsername());
                return ResponseEntity.ok(messageQueue);
            } else {
                CompletableFuture<Message> completableFuture = completableFutureMap.computeIfAbsent(
                        accessToken.getUsername(), k -> new CompletableFuture<>()
                );
                try {
                    Message message = completableFuture.get(10, TimeUnit.SECONDS);
                    completableFutureMap.remove(accessToken.getUsername());
                    return ResponseEntity.ok(message);
                } catch (TimeoutException e) {
                    return ResponseEntity.ok(List.of());
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/file")
    public ResponseEntity<?> getFile(@RequestHeader("AccessToken") String accessTokenHeader, @RequestParam String filename) {
        AccessToken accessToken = accessTokenService.checkAccessToken(accessTokenHeader);
        if (accessToken != null) {
            if (fileService.isExistFile(filename)) {
                if(fileService.hasPermission(accessToken.getUsername(), filename)){
                    File file = new File("src/main/resources/static/storage/" + filename);
                    Resource resource = new FileSystemResource(file);
                    return ResponseEntity.ok(resource);
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
