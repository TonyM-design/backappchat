package com.tp.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.tp.chat.entity.Message;
@Controller
public class WebSocketChatController {
    @MessageMapping("/chat/{canalId}")
    @SendTo("/topic/{canalId}")
    public Message messageChat(@DestinationVariable Integer canalId,Message message){

        System.err.println( message);
        return new Message(message.getUser(),message.getCanal(),message.getContent(), message.getDate(), message.getResponseQuote());
    }
    
}
