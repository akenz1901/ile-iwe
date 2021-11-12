package com.ileiwe.service.intructor;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Instructor;
import com.ileiwe.service.exception.UserAlreadyExistException;

public interface InstructorService {
    Instructor save(InstructorPartyDto instructor) throws UserAlreadyExistException;
}
