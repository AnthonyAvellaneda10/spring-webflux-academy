package com.api.rest.controller;

import com.api.rest.dto.CourseDTO;
import com.api.rest.model.Course;
import com.api.rest.pagination.PageSupport;
import com.api.rest.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping
    public Mono<ResponseEntity<Flux<CourseDTO>>> findAll() {
        Flux<CourseDTO> fx = service.findAll().map(this::convertToDto);

        return Mono.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fx)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CourseDTO>> findById(@PathVariable String id) {
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
    public Mono<ResponseEntity<CourseDTO>> save(@Valid @RequestBody CourseDTO dto, final ServerHttpRequest req) {
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
    public Mono<ResponseEntity<CourseDTO>> update(@Valid @PathVariable String id, @RequestBody CourseDTO dto) {
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
    @GetMapping("/pageable")
    public Mono<ResponseEntity<PageSupport<CourseDTO>>> getPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        return service.getPage(Course.class, PageRequest.of(page, size))
                .map( ps -> new PageSupport<>(
                        ps.getContent().stream().map(this::convertToDto).toList(),
                        ps.getPageNumber(),
                        ps.getPageSize(),
                        ps.getTotalElements()
                ))
                .map(pageSupport -> ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(pageSupport)
                )
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    private CourseDTO convertToDto(Course dish) {
        return modelMapper.map(dish, CourseDTO.class);
    }

    private Course convertToDocument(CourseDTO dto) {
        return modelMapper.map(dto, Course.class);
    }

}
