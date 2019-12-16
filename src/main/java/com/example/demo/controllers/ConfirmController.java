package com.example.demo.controllers;

import com.example.demo.model.Confirm;
import com.example.demo.model.enums.Confirmation;
import com.example.demo.repositories.ConfirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600L)
@RestController
public class ConfirmController {

    @Autowired
    ConfirmRepository confirmRepository;

    @GetMapping("/events/{eventId}/confirm")
    public List<Confirm> getConfirmationsOfEvent(@PathVariable Integer eventId) {
        return confirmRepository.findAllByEventEventId(eventId);
    }

    @PutMapping("/events/{eventId}/confirm/{userId}")
    public Confirm changeConfirmation(@PathVariable Integer eventId, @PathVariable Integer userId, @RequestBody Confirmation confirmation) {
        Confirm confirm = confirmRepository.findByEventEventIdAndUserUserId(eventId, userId)
                .orElseThrow(() -> new RuntimeException("nie ma nie ma"));
        confirm.setConfirmation(confirmation);
        confirmRepository.save(confirm);
        return confirm;
    }

}
