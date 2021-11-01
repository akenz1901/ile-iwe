package com.ileiwe.data.repository;

import com.ileiwe.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = ("/db/insert.sql"))
@Slf4j
class InstructorRepositoryTest {
    @Autowired
    InstructorRepository instructorRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveInstructorAsLearningPartyTest(){
        //create a learning party
        LearningParty user = new LearningParty("trainer@ileiwe.com", "pass123", new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor
        Instructor instructor = Instructor.builder()
                .firstname("John")
                .lastname("Alao")
                .learningParty(user).build();
        log.info("Instructor Before saved --> {}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("Instructor After saved --> {}", instructor);

    }
    @Test
    void updateInstructorTableAfterCreate(){
        LearningParty learningPartyUser = new LearningParty("trainer@ile.com", "1234", new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor = Instructor.builder()
                .firstname("Michael")
                .lastname("Akenz")
                .learningParty(learningPartyUser).build();

        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();

        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("instructor after saving --> {}", instructor);

        Instructor savedInstructor = instructorRepository.findById(instructor.getId()).orElse(null);

        log.info("Save instructor --> {}", instructor);

        //assert that savedInstructor is not null
        assertThat(savedInstructor).isNotNull();

        //confirm that instructor's bio and gender is null
        assertThat(savedInstructor.getBio()).isNull();

        //save bio and gender
        savedInstructor.setBio("I am a java instructor");
        savedInstructor.setGender(Gender.MALE);

        //save instructor into repo
        instructorRepository.save(savedInstructor);

        //assertThat instructor now has bio and gender
        assertThat(savedInstructor.getBio()).isNotNull();

        assertThat(savedInstructor.getGender()).isNotNull();


    }

    @Test
    void createInstructorWithNullValuesTest(){
        LearningParty user =
                new LearningParty("trainer@ile.com",
                        "1234",
                        new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor and map with learning party
        Instructor instructor = Instructor.builder()
                .firstname(null)
                .lastname(null)
                .learningParty(user).build();

        assertThrows(ConstraintViolationException.class,
                ()->instructorRepository.save(instructor));

    }
    @Test
    void createInstructorWithEmptyStringValuesTest(){
        LearningParty user =
                new LearningParty("trainer@ile.com",
                        "1234",
                        new Authority(Role.ROLE_INSTRUCTOR));
        //create instructor and map with learning party
        Instructor instructor = Instructor.builder()
                .firstname(" ")
                .lastname(" ")
                .learningParty(user).build();

        assertThrows(ConstraintViolationException.class,
                ()->instructorRepository.save(instructor));

    }

    @AfterEach
    void tearDown() {

    }
}