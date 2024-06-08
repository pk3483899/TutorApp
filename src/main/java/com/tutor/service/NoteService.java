package com.tutor.service;

import com.tutor.entity.Note;

import java.util.List;

public interface NoteService {
    Note save(Note note);
    List<Note> findAll();
    Note findById(Long id);
    void delete(Long id);
}

