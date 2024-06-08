package com.tutor.repository;

import com.tutor.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.io.File;
import java.util.Optional;

public interface FileRepository extends JpaRepository<Files, Long> {
    Optional<Files> findByName(String name);
}
