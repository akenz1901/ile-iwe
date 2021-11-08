package com.ileiwe.service.mail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailGunServiceImplTest {
    
    @Autowired
    @Qualifier("mailgun")
    EmailService emailService;

    @Test
    void sendEmailWithMailGunTest(){
        Message mail = Message.builder()
                .from("akenz1901@gmail.com")
                .to("michael_aka1@icloud.com")
                .subject("Testing HTTP")
                .body("Hello Bro").build();

        MailResponse response = emailService.send(mail);
        assertTrue(response.isSuccessful());
    }
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }
}