package com.example.demo.controllers;

import com.example.demo.model.Event;
import com.example.demo.model.Local;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repositories.EventRepository;
import com.example.demo.repositories.LocalRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600L)
@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocalRepository localRepository;

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        Iterable<Event> events = eventRepository.findAll();
        return (List<Event>) events;
    }

    @PostMapping("/locals/{id}/add-event")
    public ResponseEntity addEvent(@PathVariable int id, @RequestBody Event event, @RequestParam(value="ids") List<Integer> ids) {
        Local local = localRepository.findById(id).orElseThrow(() -> new RuntimeException("no local"));
        List<User> userList = ids.stream()
                .map(idd -> userRepository.findById(idd).orElseThrow(() -> new RuntimeException("no user")))
                .collect(Collectors.toList());
        event.setLocal(local);
        event.setParticipants(userList);
        eventRepository.save(event);
        for (User user : userList) {
            List<Event> events = user.getEvents();
            events.add(event);
            user.setEvents(events);
            userRepository.save(user);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id) {
        eventRepository.deleteById(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/events/{id}/participants")
    public List<User> getEventParticipants(@PathVariable int id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("no event"));
        return event.getParticipants();
    }

    @GetMapping("/events/{id}/messages")
    public List<Message> getEventMessages(@PathVariable int id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("no event"));
        return event.getMessages();
    }



}