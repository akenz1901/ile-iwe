package com.ileiwe.service.intructor;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Authority;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.data.model.LearningParty;
import com.ileiwe.data.model.Role;
import com.ileiwe.data.repository.InstructorRepository;
import com.ileiwe.data.repository.LearningPartyRepository;
import com.ileiwe.service.event.OnRegistrationCompleteEvent;
import com.ileiwe.service.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    LearningPartyRepository repository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Instructor save(InstructorPartyDto instructorDto) throws UserAlreadyExistException {

        if (instructorDto == null){
            throw new IllegalArgumentException("Instructor can't be null");
        }
        if (repository.findByEmail(instructorDto.getEmail()) == null) {
            LearningParty learningPartyUser =
                    new LearningParty(instructorDto.getEmail(), bCryptPasswordEncoder.encode(instructorDto.getPassword()),
                            new Authority(Role.ROLE_INSTRUCTOR));

            Instructor instructor = Instructor.builder()
                    .lastname(instructorDto.getLastname())
                    .firstname(instructorDto.getFirstname())
                    .learningParty(learningPartyUser).build();

            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(learningPartyUser));
            return instructorRepository.save(instructor);
        }else
            throw new UserAlreadyExistException("User with email" + instructorDto.getEmail() + "already exists");
    }
}
