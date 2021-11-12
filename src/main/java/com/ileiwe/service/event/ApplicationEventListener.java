package com.ileiwe.service.event;

import com.ileiwe.service.mail.EmailService;
import com.ileiwe.service.mail.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;

@Component
public class ApplicationEventListener {
    @Autowired
    EmailService emailService;

    @Autowired
    TemplateEngine templateEngine;
    @EventListener
    public void handleRegistrationCompleteEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent){
        this.confirmRegistration(onRegistrationCompleteEvent);
    }
    private void confirmRegistration(OnRegistrationCompleteEvent event){
        String verification = UUID.randomUUID().toString();

        Message message = Message.builder()
                .from("akenz1901@gmail.com")
                .to(event.getAppUser().getEmail())
                .subject("Confirmation Email")
                .body(templateEngine.process("confirmation", new Context()))
                .build();
        emailService.send(message);

    }
}
