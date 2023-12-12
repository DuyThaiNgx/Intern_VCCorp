package com.example.instancechat.service;

import com.example.instancechat.entity.Message;
import com.example.instancechat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    public LinkedList<Message> getMessageQueue(String username){
        return messageRepository.getMessageQueue(username);
    }
    public void saveMessage(String username, LinkedList<Message>messageQueue){
        messageRepository.saveMessage(username, messageQueue);
    }
    public void removeMessageQueue(String username) {
        messageRepository.removeMessage(username);
    }
}
