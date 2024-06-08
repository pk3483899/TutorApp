package com.tutor.service;

import com.tutor.entity.Tutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TutorService {

    public Tutor saveTutor(Tutor tutor);
    public List<Tutor> allTutors();
    public void deleteTutor(long id);
    public Optional<Tutor> findById(long id);
    public List<Tutor> findByName(String name);
    public Tutor updateTutor(long id, String address);
}
