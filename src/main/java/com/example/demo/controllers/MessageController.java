package com.example.demo.controllers;

import com.example.demo.model.Event;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repositories.EventRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600L)
@RestController
public class MessageController {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/events/{eventId}/add-message/{userId}")
    public ResponseEntity addMessage(@PathVariable int eventId, @PathVariable int userId,@RequestBody Message message) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("no event"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("no user"));
        List<Message> messages = event.getMessages();
        messages.add(message);
        event.setMessages(messages);
        message.setEvent(event);
        message.setUser(user);
        messageRepository.save(message);
        eventRepository.save(event);
        return ResponseEntity.ok().body(message);
    }
}
