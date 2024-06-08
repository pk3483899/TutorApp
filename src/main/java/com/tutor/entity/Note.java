package com.tutor.entity;


import jakarta.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Subject;
    private String title;

    @Column(name="content",length = 255*1000)
    private String content;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return Subject;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

