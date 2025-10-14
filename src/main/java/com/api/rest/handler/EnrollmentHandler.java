package com.api.rest.handler;

import com.api.rest.dto.EnrollmentDTO;
import com.api.rest.model.Enrollment;
import com.api.rest.service.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class EnrollmentHandler {
    private final IEnrollmentService service;
    @Qualifier("enrollmentMapper")
    private final ModelMapper modelMapper;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll().map(this::convertToDto), EnrollmentDTO.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");

        return service.findById(id)
                .map(this::convertToDto)
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<EnrollmentDTO> monoEnrollmentDTO = request.bodyToMono(EnrollmentDTO.class);

        return monoEnrollmentDTO
                .flatMap(e -> service.save(convertToDocument(e)))
                .map(this::convertToDto)
                .flatMap(dto -> ServerResponse
                        .created(URI.create(request.uri().toString().concat("/").concat(dto.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(dto)));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<EnrollmentDTO> monoEnrollmentDTO = request.bodyToMono(EnrollmentDTO.class);

        return monoEnrollmentDTO.flatMap(e -> {
                    e.setId(id);
                    return service.update(id, convertToDocument(e));
                })
                .map(this::convertToDto)
                .flatMap(e -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(e))
                );
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");

        return service.delete(id)
                .flatMap(result -> {
                    if (result) {
                        return ServerResponse.noContent().build();
                    } else {
                        return ServerResponse.notFound().build();
                    }
                });
    }

    private EnrollmentDTO convertToDto(Enrollment model) {
        return modelMapper.map(model, EnrollmentDTO.class);
    }

    private Enrollment convertToDocument(EnrollmentDTO dto) {
        return modelMapper.map(dto, Enrollment.class);
    }
}
