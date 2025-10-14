package com.api.rest.service;

import com.api.rest.model.User;
import reactor.core.publisher.Mono;

public interface IUserService extends ICRUD<User, String> {
    Mono<User> saveHash(User user);
    Mono<com.api.rest.security.User> searchByUser(String username);
}
