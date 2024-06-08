package com.tutor.service.serviceImpl;

import com.tutor.entity.Tutor;
import com.tutor.repository.TutorRepository;
import com.tutor.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public Tutor saveTutor(Tutor tutor){
        Tutor save = tutorRepository.save(tutor);
        return save;
    }

    public List<Tutor> allTutors(){
        List<Tutor> all = tutorRepository.findAll();
        return all;
    }

    public void deleteTutor(long id){
        tutorRepository.deleteById(id);
    }

    public Optional<Tutor> findById(long id){
        Optional<Tutor> byId = tutorRepository.findById(id);
        return byId;
    }

    public List<Tutor> findByName(String name){
    List<Tutor> byName = tutorRepository.findByName(name);
    return byName;
    }

    public Tutor updateTutor(long id, String address){
        Optional<Tutor> byId = tutorRepository.findById(id);
        Tutor tutor = byId.get();
        tutor.setAddress(address);
        Tutor save = tutorRepository.save(tutor);
        return save;
    }
}
