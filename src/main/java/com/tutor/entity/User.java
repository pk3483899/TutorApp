package com.tutor.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 60, message = "name should be greater then 3 and less then 60 characters")
    private String name;

    @Column(name = "email", nullable = false)
    @Email(message = "email should have @, ., characters and numbers only")
    private String email;

    @Size(min = 7, max = 15, message = "password should be from 7 to 15 characters and should have numbers, characters and special characters also ")
    @Column(name = "password", nullable = false)
    private String password;

    @Size(min = 10, max = 10, message = "mobile number should be only of 10 digits")
    private String mobile;

    @Size(min = 3, max = 40, message = "Address should be between 3 to 40 characters only")
    private String address;

    private boolean emailVerified;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
