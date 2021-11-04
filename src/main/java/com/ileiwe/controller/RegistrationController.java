package com.ileiwe.controller;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.service.intructor.InstructorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private InstructorServiceImpl instructorService;

    @PostMapping("/instructor")
    public ResponseEntity<?> registerAsInstructor(@RequestBody InstructorPartyDto instructorPartyDto){
        return ResponseEntity.ok()
                .body(instructorService.save(instructorPartyDto));
    }
}
