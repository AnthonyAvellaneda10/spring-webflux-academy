package com.api.rest.service;

import com.api.rest.model.Student;
import reactor.core.publisher.Flux;

public interface IStudentService extends ICRUD<Student, String> {
    Flux<Student> findAllOrderByAgeAsc();
    Flux<Student> findAllOrderByAgeDesc();
}
