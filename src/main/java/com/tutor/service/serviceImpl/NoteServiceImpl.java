package com.tutor.service.serviceImpl;


import com.tutor.entity.Note;
import com.tutor.repository.NoteRepository;
import com.tutor.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note findById(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        return optionalNote.orElse(null);
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}

