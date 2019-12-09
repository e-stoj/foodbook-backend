package com.example.demo.controllers;

import com.example.demo.model.Local;
import com.example.demo.model.enums.Motives;
import com.example.demo.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600L)
@RestController
public class LocalController {

    @Autowired
    LocalRepository localRepository;

    @GetMapping("/locals")
    public List<Local> getAllLocals() {
        Iterable<Local> locals = localRepository.findAll();
        return (List<Local>) locals;
    }

    @PostMapping("/locals")
    public ResponseEntity addLocal(@RequestBody Local local) {
        localRepository.save(local);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/locals/{id}")
    public ResponseEntity editLocal(@PathVariable Integer id, @RequestBody Local local) {
        Local oldLocal = localRepository.findById(id).orElseThrow(() -> new RuntimeException("no local"));
        local.setLocalId(id);
        localRepository.save(local);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/locals/{id}")
    public ResponseEntity deleteLocal(@PathVariable Integer id) {
        localRepository.deleteById(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/locals/motives")
    public List<Motives> getAllMotives() {
        return Arrays.asList(Motives.values());
    }

    @GetMapping("/locals/{motive}")
    public List<Local> getLocalsWithSelectedMotive(@PathVariable String motive) {
        Motives motive1 = Motives.valueOf(motive);
        Iterable<Local> localIterable = localRepository.findAll();
        List<Local> localList = (List<Local>) localIterable;
        return localList.stream()
                .filter(local -> local.getMotives().contains(motive1))
                .collect(Collectors.toList());
    }
}
