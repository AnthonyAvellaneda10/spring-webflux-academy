package com.api.rest.service.impl;

import com.api.rest.model.Course;
import com.api.rest.repo.ICourseRepo;
import com.api.rest.repo.IGenericRepo;
import com.api.rest.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, String> implements ICourseService {
    private final ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, String> getRepo() {
        return repo;
    }
}
