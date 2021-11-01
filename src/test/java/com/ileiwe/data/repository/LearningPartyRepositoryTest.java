package com.ileiwe.data.repository;

import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
@Rollback(value = false)
@Sql("/db/insert.sql")
class LearningPartyRepositoryTest {

    @Autowired
    LearningPartyRepository learningPartyRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    void createLearningPartyTest(){
        LearningParty learningPartyUser = new LearningParty("akenz1901@gmail.com",
                "akenz12233", new Authority(Role.ROLE_STUDENT));
        log.info("Before saving --> {}", learningPartyUser);
        learningPartyRepository.save(learningPartyUser);
        assertThat(learningPartyUser.getId()).isNotNull();
        assertThat(learningPartyUser.getEmail()).isEqualTo("akenz1901@gmail.com");
        assertThat(learningPartyUser.getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);
        log.info("After saving --> {}", learningPartyUser);
    }
    @Test
    @Transactional
    void LearningPartyCannotHaveTwoEmailsTest(){
        LearningParty learningPartyUser = new LearningParty("akenz1901@gmail.com",
                "akenz12233", new Authority(Role.ROLE_STUDENT));
        log.info("Before saving --> {}", learningPartyUser);
        learningPartyRepository.save(learningPartyUser);
        assertThat(learningPartyUser.getId()).isNotNull();
        assertThat(learningPartyUser.getEmail()).isEqualTo("akenz1901@gmail.com");
        assertThat(learningPartyUser.getAuthorities().get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);
        log.info("After saving --> {}", learningPartyUser);

        LearningParty learningUser = new LearningParty("akenz1901@gmail.com",
                "akenz111", new Authority(Role.ROLE_STUDENT));
        learningPartyRepository.save(learningUser);


    }

    @Test
    void learningPartyWithNullValuesTest(){
        LearningParty learningUser = new LearningParty(null,
                null, new Authority(Role.ROLE_STUDENT));
        assertThrows(ConstraintViolationException.class, () -> learningPartyRepository.save(learningUser));
    }
    @Test
    void learningPartyWithEmptyStringTest(){
        LearningParty learningUser = new LearningParty(" ",
                " ", new Authority(Role.ROLE_STUDENT));
        assertThrows(ConstraintViolationException.class, () -> learningPartyRepository.save(learningUser));
    }
    @Test
    void findByUserNameTest(){
        LearningParty learnUser = learningPartyRepository.findByEmail("tobi@mail.com");
        assertThat(learnUser).isNotNull();
        assertThat(learnUser.getEmail()).isEqualTo("tobi@mail.com");
        log.info("Learning party object --> {}", learnUser);
    }
    @AfterEach
    void tearDown(){}

}