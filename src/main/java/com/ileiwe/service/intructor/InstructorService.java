package com.ileiwe.service.intructor;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Instructor;

public interface InstructorService {
    Instructor save(InstructorPartyDto instructor);
}
