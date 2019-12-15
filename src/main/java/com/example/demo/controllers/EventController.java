package com.example.demo.controllers;

import com.example.demo.model.*;
import com.example.demo.repositories.ConfirmRepository;
import com.example.demo.repositories.EventRepository;
import com.example.demo.repositories.LocalRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    ConfirmRepository confirmRepository;

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
        List<Confirm> confirms = userList.stream()
                .map(user -> new Confirm(user, event))
                .collect(Collectors.toList());

        confirms.forEach(confirm -> confirmRepository.save(confirm));

        event.setConfirmList(confirms);
        eventRepository.save(event);
        for (User user : userList) {
            List<Event> events = user.getEvents();
            events.add(event);
            user.setEvents(events);
            userRepository.save(user);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity editEvent(@PathVariable Integer id, @RequestBody Event event) {
        Event oldEvent = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("no event"));
        event.setEventId(id);
        event.setParticipants(oldEvent.getParticipants());
        event.setConfirmList(oldEvent.getConfirmList());
        eventRepository.save(event);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("no event"));
        event.getParticipants().forEach(user -> user.getEvents().remove(event));
        event.getConfirmList().forEach(confirm -> confirm.setUser(null));
        event.setParticipants(new ArrayList<>());
        event.setLocal(null);
        event.setConfirmList(new ArrayList<>());
        eventRepository.save(event);
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
