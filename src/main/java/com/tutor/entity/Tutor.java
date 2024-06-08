package com.tutor.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=3,max = 40)
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Subjects subjects;

    @Size(min = 4, max = 60, message = "size of address should be not less than 4 chars and not more then 60 charactes")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    @Size(min = 4, max = 30, message = "length of city should not be less then 4 characters and not lenss then 30 characters")
    private String city;

    @Size(min = 4, max = 30, message = "Size of state should be up to 30 characters only and not less than 4 characters")
    @Column(name = "state", nullable = false)
    private String state;


    @Size(min = 6, max = 6, message = "pincode should of 6 digits only")
    @Column(name = "pincode", nullable = false)
    private String pincode;

}
