package com.api.rest.config;

import com.api.rest.handler.CourseHandler;
import com.api.rest.handler.EnrollmentHandler;
import com.api.rest.handler.StudentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v2/courses",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.GET,
                    beanClass = CourseHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(summary = "Listar todos los cursos")
            ),
            @RouterOperation(
                    path = "/api/v2/courses/{id}",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.GET,
                    beanClass = CourseHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(summary = "Obtener curso por ID")
            ),
            @RouterOperation(
                    path = "/api/v2/courses",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.POST,
                    beanClass = CourseHandler.class,
                    beanMethod = "save",
                    operation = @Operation(summary = "Registrar nuevo curso")
            ),
            @RouterOperation(
                    path = "/api/v2/courses/{id}",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.PUT,
                    beanClass = CourseHandler.class,
                    beanMethod = "update",
                    operation = @Operation(summary = "Actualizar curso existente")
            ),
            @RouterOperation(
                    path = "/api/v2/courses/{id}",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.DELETE,
                    beanClass = CourseHandler.class,
                    beanMethod = "delete",
                    operation = @Operation(summary = "Eliminar curso")
            )
    })
    @Tag(name = "Courses", description = "Operaciones CRUD sobre los cursos (enrutamiento funcional)")
    public RouterFunction<ServerResponse> courseRoutes(CourseHandler handler) {
        return route(GET("/api/v2/courses"), handler::findAll)
                .andRoute(GET("/api/v2/courses/{id}"), handler::findById)
                .andRoute(POST("/api/v2/courses"), handler::save)
                .andRoute(PUT("/api/v2/courses/{id}"), handler::update)
                .andRoute(DELETE("/api/v2/courses/{id}"), handler::delete);
    }

    // ============================================================
    // ===============       STUDENTS ROUTES       ================
    // ============================================================
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v2/students",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.GET,
                    beanClass = StudentHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(summary = "Listar todos los estudiantes")
            ),
            @RouterOperation(
                    path = "/api/v2/students/asc",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.GET,
                    beanClass = StudentHandler.class,
                    beanMethod = "findAllAsc",
                    operation = @Operation(summary = "Listar estudiantes en orden ascendente")
            ),
            @RouterOperation(
                    path = "/api/v2/students/desc",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.GET,
                    beanClass = StudentHandler.class,
                    beanMethod = "findAllDesc",
                    operation = @Operation(summary = "Listar estudiantes en orden descendente")
            ),
            @RouterOperation(
                    path = "/api/v2/students/{id}",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.GET,
                    beanClass = StudentHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(summary = "Buscar estudiante por ID")
            ),
            @RouterOperation(
                    path = "/api/v2/students",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.POST,
                    beanClass = StudentHandler.class,
                    beanMethod = "save",
                    operation = @Operation(summary = "Registrar nuevo estudiante")
            ),
            @RouterOperation(
                    path = "/api/v2/students/{id}",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.PUT,
                    beanClass = StudentHandler.class,
                    beanMethod = "update",
                    operation = @Operation(summary = "Actualizar estudiante existente")
            ),
            @RouterOperation(
                    path = "/api/v2/students/{id}",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.DELETE,
                    beanClass = StudentHandler.class,
                    beanMethod = "delete",
                    operation = @Operation(summary = "Eliminar estudiante")
            )
    })
    @Tag(name = "Students", description = "Operaciones CRUD sobre los estudiantes (enrutamiento funcional)")
    public RouterFunction<ServerResponse> studentRoutes(StudentHandler handler) {
        return route(GET("/api/v2/students"), handler::findAll)
                .andRoute(GET("/api/v2/students/asc"), handler::findAllAsc)
                .andRoute(GET("/api/v2/students/desc"), handler::findAllDesc)
                .andRoute(GET("/api/v2/students/{id}"), handler::findById)
                .andRoute(POST("/api/v2/students"), handler::save)
                .andRoute(PUT("/api/v2/students/{id}"), handler::update)
                .andRoute(DELETE("/api/v2/students/{id}"), handler::delete);
    }

    // ============================================================
    // ===============      ENROLLMENTS ROUTES     ================
    // ============================================================
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v2/enrollments",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.GET,
                    beanClass = EnrollmentHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(summary = "Listar todas las matrículas")
            ),
            @RouterOperation(
                    path = "/api/v2/enrollments/{id}",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.GET,
                    beanClass = EnrollmentHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(summary = "Obtener matrícula por ID")
            ),
            @RouterOperation(
                    path = "/api/v2/enrollments",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.POST,
                    beanClass = EnrollmentHandler.class,
                    beanMethod = "save",
                    operation = @Operation(summary = "Registrar nueva matrícula")
            ),
            @RouterOperation(
                    path = "/api/v2/enrollments/{id}",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.PUT,
                    beanClass = EnrollmentHandler.class,
                    beanMethod = "update",
                    operation = @Operation(summary = "Actualizar matrícula existente")
            ),
            @RouterOperation(
                    path = "/api/v2/enrollments/{id}",
                    produces = {"application/json"},
                    method = org.springframework.web.bind.annotation.RequestMethod.DELETE,
                    beanClass = EnrollmentHandler.class,
                    beanMethod = "delete",
                    operation = @Operation(summary = "Eliminar matrícula")
            )
    })
    @Tag(name = "Enrollments", description = "Operaciones CRUD sobre las matrículas (enrutamiento funcional)")
    public RouterFunction<ServerResponse> enrollmentRoutes(EnrollmentHandler handler) {
        return route(GET("/api/v2/enrollments"), handler::findAll)
                .andRoute(GET("/api/v2/enrollments/{id}"), handler::findById)
                .andRoute(POST("/api/v2/enrollments"), handler::save)
                .andRoute(PUT("/api/v2/enrollments/{id}"), handler::update)
                .andRoute(DELETE("/api/v2/enrollments/{id}"), handler::delete);
    }
}
