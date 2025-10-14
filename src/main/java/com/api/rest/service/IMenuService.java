package com.api.rest.service;

import com.api.rest.model.Menu;
import reactor.core.publisher.Flux;

public interface IMenuService extends ICRUD<Menu, String> {

    Flux<Menu> getMenus(String[] roles);
}
