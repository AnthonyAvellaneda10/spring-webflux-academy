package com.api.rest.service.impl;

import com.api.rest.model.Enrollment;
import com.api.rest.repo.ICourseRepo;
import com.api.rest.repo.IEnrollmentRepo;
import com.api.rest.repo.IGenericRepo;
import com.api.rest.repo.IStudentRepo;
import com.api.rest.service.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl extends CRUDImpl<Enrollment, String> implements IEnrollmentService {

    private final IEnrollmentRepo enrollmentRepo;
    private final IStudentRepo studentRepo;
    private final ICourseRepo courseRepo;

    @Override
    protected IGenericRepo<Enrollment, String> getRepo() {
        return enrollmentRepo;
    }
}
