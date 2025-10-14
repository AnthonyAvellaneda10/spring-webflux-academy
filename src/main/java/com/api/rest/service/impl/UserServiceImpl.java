package com.api.rest.service.impl;

import com.api.rest.model.Role;
import com.api.rest.model.User;
import com.api.rest.repo.IGenericRepo;
import com.api.rest.repo.IRoleRepo;
import com.api.rest.repo.IUserRepo;
import com.api.rest.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, String> implements IUserService {

    private final IUserRepo userRepo;
    private final IRoleRepo roleRepo;
    private final BCryptPasswordEncoder bcrypt;

    @Override
    protected IGenericRepo<User, String> getRepo() {
        return userRepo;
    }

    @Override
    public Mono<User> saveHash(User user) {
        user.setPassword(bcrypt.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Mono<com.api.rest.security.User> searchByUser(String username) {
        return userRepo.findOneByUsername(username)
                .zipWhen(user -> Flux.fromIterable(user.getRoles())
                        .flatMap(role -> roleRepo.findById(role.getId()).map(Role::getName))
                        .collectList()
                )
                .map(tuple -> {
                    User user = tuple.getT1();
                    List<String> rolesList = tuple.getT2();

                    return new com.api.rest.security.User(
                            user.getUsername(),
                            user.getPassword(),
                            user.isStatus(),
                            rolesList
                    );
                });

    }
}
