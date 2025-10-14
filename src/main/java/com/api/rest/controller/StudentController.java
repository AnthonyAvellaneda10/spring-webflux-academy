package com.api.rest.controller;

import com.api.rest.dto.StudentDTO;
import com.api.rest.model.Student;
import com.api.rest.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService service;
    @Qualifier("studentMapper")
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public Mono<ResponseEntity<Flux<StudentDTO>>> findAll() {
        Flux<StudentDTO> fx = service.findAll().map(this::convertToDto);

        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fx)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<StudentDTO>> findById(@PathVariable String id) {
        return service.findById(id)
                .map(this::convertToDto)
                .map(e ->
                        ResponseEntity
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(e)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Mono<ResponseEntity<StudentDTO>> save(@Valid @RequestBody StudentDTO dto, final ServerHttpRequest req) {
        return service.save(convertToDocument(dto))
                .map(this::convertToDto)
                .map(e ->
                        ResponseEntity
                                .created(URI.create(req.getURI()+ "/"+ e.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(e)
                );
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Mono<ResponseEntity<StudentDTO>> update(@Valid @PathVariable String id, @RequestBody StudentDTO dto) {
        return Mono.just(dto)
                .flatMap(e -> {
                    e.setId(id);
                    return service.update(id, convertToDocument(dto));
                })
                .map(this::convertToDto)
                .map(e ->
                        ResponseEntity
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(e)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Boolean>> delete(@PathVariable String id) {
        return service.delete(id)
                .flatMap(result -> {
                    if (result){
                        return Mono.just(ResponseEntity.noContent().build());
                    }else{
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                });
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/order/asc")
    public Mono<ResponseEntity<Flux<StudentDTO>>> findAllOrderByAgeAsc() {
        Flux<StudentDTO> fx = service.findAllOrderByAgeAsc().map(this::convertToDto);
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fx)
        );
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/order/desc")
    public Mono<ResponseEntity<Flux<StudentDTO>>> findAllOrderByAgeDesc() {
        Flux<StudentDTO> fx = service.findAllOrderByAgeDesc().map(this::convertToDto);
        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fx)
        );
    }

    private StudentDTO convertToDto(Student client) {
        return modelMapper.map(client, StudentDTO.class);
    }

    private Student convertToDocument(StudentDTO dto) {
        return modelMapper.map(dto, Student.class);
    }
}
