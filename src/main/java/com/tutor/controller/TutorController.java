package com.tutor.controller;

import com.tutor.entity.Tutor;
import com.tutor.service.serviceImpl.TutorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tutors")
public class TutorController {

    @Autowired
    private TutorServiceImpl tutorService;

    @PostMapping("/create")
    public ResponseEntity<?> createTutor(@Valid @RequestBody Tutor tutor, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Tutor tutor1 = tutorService.saveTutor(tutor);
        return new ResponseEntity<>(tutor1, HttpStatus.CREATED);
    }

    @GetMapping("/all-tutors")
    public ResponseEntity<List<Tutor>> allTutors(){
        List<Tutor> tutors = tutorService.allTutors();
        return new ResponseEntity<>(tutors, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTutor(@PathVariable long id){
        tutorService.deleteTutor(id);
        return new ResponseEntity<>("Tutor details deleted with id "+id, HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Optional<Tutor>> findById(@PathVariable long id){
        Optional<Tutor> byId = tutorService.findById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Tutor>> findByName(@PathVariable String name){
        List<Tutor> byName = tutorService.findByName(name);
        return new ResponseEntity<>(byName, HttpStatus.OK);
    }

    @PutMapping("/update-address/{id}/{address}")
    public ResponseEntity<Tutor> updateTutor(@PathVariable long id, @PathVariable String address){
        Tutor tutor = tutorService.updateTutor(id, address);
        return new ResponseEntity<>(tutor, HttpStatus.OK);

    }
}
