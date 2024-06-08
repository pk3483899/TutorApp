package com.tutor.controller;

import com.tutor.entity.Video;
import com.tutor.service.serviceImpl.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        Optional<Video> video = videoService.getVideoById(id);
        if (video.isPresent()) {
            return ResponseEntity.ok(video.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Video createVideo(@RequestBody Video video) {
        return videoService.saveVideo(video);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody Video videoDetails) {
        Optional<Video> video = videoService.getVideoById(id);
        if (video.isPresent()) {
            Video videoToUpdate = video.get();
            videoToUpdate.setTitle(videoDetails.getTitle());
            videoToUpdate.setDescription(videoDetails.getDescription());
            videoToUpdate.setUrl(videoDetails.getUrl());
            return ResponseEntity.ok(videoService.saveVideo(videoToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            String url = videoService.storeFile(file);
            return new ResponseEntity<>(url, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

