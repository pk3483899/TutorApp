package com.tutor.controller;

import com.tutor.entity.ChatMessage;
import com.tutor.service.serviceImpl.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatMessage sendMessage(@RequestBody ChatMessage chatMessage) {
        return chatService.saveMessage(chatMessage);
    }

    @GetMapping
    public List<ChatMessage> getAllMessages() {
        return chatService.getAllMessages();
    }
}

