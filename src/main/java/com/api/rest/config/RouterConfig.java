package com.api.rest.config;

import com.api.rest.handler.CourseHandler;
import com.api.rest.handler.EnrollmentHandler;
import com.api.rest.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> courseRoutes(CourseHandler handler){
        return route(GET("/api/v2/courses"), handler::findAll)
                .andRoute(GET("/api/v2/courses/{id}"), handler::findById)
                .andRoute(POST("/api/v2/courses"), handler::save)
                .andRoute(PUT("/api/v2/courses/{id}"), handler::update)
                .andRoute(DELETE("/api/v2/courses/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> studentRoutes(StudentHandler handler) {
        return route(GET("/api/v2/students"), handler::findAll)
                .andRoute(GET("/api/v2/students/asc"), handler::findAllAsc)
                .andRoute(GET("/api/v2/students/desc"), handler::findAllDesc)
                .andRoute(GET("/api/v2/students/{id}"), handler::findById)
                .andRoute(POST("/api/v2/students"), handler::save)
                .andRoute(PUT("/api/v2/students/{id}"), handler::update)
                .andRoute(DELETE("/api/v2/students/{id}"), handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> enrollmentRoutes(EnrollmentHandler handler){
        return route(GET("/api/v2/enrollments"), handler::findAll)
                .andRoute(GET("/api/v2/enrollments/{id}"), handler::findById)
                .andRoute(POST("/api/v2/enrollments"), handler::save)
                .andRoute(PUT("/api/v2/enrollments/{id}"), handler::update)
                .andRoute(DELETE("/api/v2/enrollments/{id}"), handler::delete);
    }
}
