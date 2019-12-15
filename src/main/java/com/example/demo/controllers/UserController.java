package com.example.demo.controllers;

import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.model.UserDetails;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600L)
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody User user) {
        if (user.getUserId() == null) {
            try {
                userRepository.save(user);
                return new ResponseEntity(HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                //empty intentionally
            }
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("User already exists");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDetails userDetails) {
        User user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow(() -> new RuntimeException("no user"));
        if(user.getPassword().equals(userDetails.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("such user doesn't exist");
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return (List<User>) users;
    }

    @PutMapping("/users/{id}")
    public User addFriend(@PathVariable Integer id, @RequestBody User friend) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("no user find"));
        Set<User> friendsList = user.getFriendsList();
        friendsList.add(friend);
        user.setFriendsList(friendsList);
        User user2 = userRepository.findById(friend.getUserId()).orElseThrow(() -> new RuntimeException("no user"));
        Set<User> friendsList2 = user2.getFriendsList();
        friendsList2.add(user);
        user2.setFriendsList(friendsList2);
        userRepository.save(user);
        userRepository.save(user2);
        return user;
    }

    @PutMapping("/users/{id}/friends")
    public ResponseEntity deleteFriend(@PathVariable Integer id, @RequestBody User friend) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("no user find"));
        User user2 = userRepository.findById(friend.getUserId()).orElseThrow(() -> new RuntimeException("no user"));
        Set<User> friendsList = user.getFriendsList();
        friendsList.remove(user2);
        user.setFriendsList(friendsList);
        Set<User> friendsList2 = user2.getFriendsList();
        friendsList2.remove(user);
        user2.setFriendsList(friendsList2);
        userRepository.save(user);
        userRepository.save(user2);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/users/{login}")
    public User findFriendByLogin(@PathVariable String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("no user find"));
    }

    @GetMapping("/users/{id}/friends")
    public Set<User> getUserFriends(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("no user "));
        Set<User> userList = user.getFriendsList();
        return userList;
    }

    @GetMapping("/users/{id}/events")
    public List<Event> getUserEvents(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("no user "));
        return user.getEvents();
    }







}
