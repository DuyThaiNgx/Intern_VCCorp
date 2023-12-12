package com.example.instancechat.request;

import com.example.instancechat.entity.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageForm {
    private String username;
    private Message message;

}