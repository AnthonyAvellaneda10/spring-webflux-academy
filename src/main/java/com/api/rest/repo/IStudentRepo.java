package com.api.rest.repo;

import com.api.rest.model.Student;
import reactor.core.publisher.Flux;

public interface IStudentRepo extends IGenericRepo<Student, String> {

    Flux<Student> findAllByOrderByAgeAsc();

    Flux<Student> findAllByOrderByAgeDesc();
}
