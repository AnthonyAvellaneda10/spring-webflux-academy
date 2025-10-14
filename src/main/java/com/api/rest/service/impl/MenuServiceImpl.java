package com.api.rest.service.impl;

import com.api.rest.model.Menu;
import com.api.rest.repo.IGenericRepo;
import com.api.rest.repo.IMenuRepo;
import com.api.rest.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends CRUDImpl<Menu, String> implements IMenuService {

    private final IMenuRepo repo;

    @Override
    protected IGenericRepo<Menu, String> getRepo() {
        return repo;
    }

    @Override
    public Flux<Menu> getMenus(String[] roles) {
        return repo.getMenus(roles);
    }
}
