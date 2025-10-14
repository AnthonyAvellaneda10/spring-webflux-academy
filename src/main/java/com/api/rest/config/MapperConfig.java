package com.api.rest.config;

import com.api.rest.dto.EnrollmentDTO;
import com.api.rest.dto.StudentDTO;
import com.api.rest.model.Enrollment;
import com.api.rest.model.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper defaultMapper(){
        return new ModelMapper();
    }

    @Bean("studentMapper")
    public ModelMapper studentMapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //LECTURA
        mapper.createTypeMap(Student.class, StudentDTO.class)
                .addMapping(Student::getFirstName, (dest, v) -> dest.setName((String) v))
                .addMapping(Student::getLastName, (dest, v) -> dest.setSurname((String) v))
                .addMapping(Student::getDni, (dest, v) -> dest.setDniStudent((String) v))
                .addMapping(Student::getAge, (dest, v) -> dest.setAgeStudent((Integer) v));

        //ESCRITURA
        mapper.createTypeMap(StudentDTO.class, Student.class)
                .addMapping(StudentDTO::getName, (dest, v) -> dest.setFirstName((String) v))
                .addMapping(StudentDTO::getSurname, (dest, v) -> dest.setLastName((String) v))
                .addMapping(StudentDTO::getDniStudent, (dest, v) -> dest.setDni((String) v))
                .addMapping(StudentDTO::getAgeStudent, (dest, v) -> dest.setAge((Integer) v));

        return mapper;
    }

    @Bean("enrollmentMapper")
    public ModelMapper enrollmentMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //Lectura
        mapper.createTypeMap(Enrollment.class, EnrollmentDTO.class)
                .addMapping(e -> e.getStudent().getFirstName(), (dest, v) -> dest.getStudent().setName((String) v))
                .addMapping(e -> e.getStudent().getLastName(), (dest, v) -> dest.getStudent().setSurname((String) v));

        //Escritura
        mapper.createTypeMap(EnrollmentDTO.class, Enrollment.class)
                .addMapping(e -> e.getStudent().getName(), (dest, v) -> dest.getStudent().setFirstName((String) v))
                .addMapping(e -> e.getStudent().getSurname(), (dest, v) -> dest.getStudent().setLastName((String) v));

        return mapper;
    }
}
