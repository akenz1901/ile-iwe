package com.ileiwe.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(length = 1000)
    private String bio;
    private Gender gender;
    private String specialization;
    @OneToOne
    private LearningParty learningParty;
    @OneToMany
    private List<Course> courseList;
}
