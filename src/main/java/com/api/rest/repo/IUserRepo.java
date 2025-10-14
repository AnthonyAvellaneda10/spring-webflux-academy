package com.api.rest.repo;

import com.api.rest.model.User;
import reactor.core.publisher.Mono;

public interface IUserRepo extends IGenericRepo<User, String> {
    Mono<User> findOneByUsername(String username);
}
