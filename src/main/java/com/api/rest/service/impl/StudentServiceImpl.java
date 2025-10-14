package com.api.rest.service.impl;

import com.api.rest.model.Student;
import com.api.rest.repo.IGenericRepo;
import com.api.rest.repo.IStudentRepo;
import com.api.rest.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, String> implements IStudentService {
    private final IStudentRepo repo;

    @Override
    protected IGenericRepo<Student, String> getRepo() {
        return repo;
    }

    @Override
    public Flux<Student> findAllOrderByAgeAsc() {
        return repo.findAllByOrderByAgeAsc();
    }

    @Override
    public Flux<Student> findAllOrderByAgeDesc() {
        return repo.findAllByOrderByAgeDesc();
    }
}
