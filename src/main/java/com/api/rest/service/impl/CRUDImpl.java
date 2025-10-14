package com.api.rest.service.impl;

import com.api.rest.pagination.PageSupport;
import com.api.rest.repo.IGenericRepo;
import com.api.rest.service.ICRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<T> save(T t) {
        return getRepo().save(t);
    }

    @Override
    public Mono<T> update(ID id, T t) {
        return getRepo().findById(id).flatMap(e -> getRepo().save(t));
    }

    @Override
    public Flux<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public Mono<T> findById(ID id) {
        return getRepo().findById(id);
    }

    @Override
    public Mono<Boolean> delete(ID id) {
        return getRepo().findById(id)
                .hasElement()
                .flatMap(result ->{
                    if(result){
                        return getRepo().deleteById(id).thenReturn(true);
                    }else{
                        return Mono.just(false);
                    }
                });
    }

    @Override
    public Mono<PageSupport<T>> getPage(Class<T> entityClass, Pageable pageable) {

        //Procesamiento en base de datos MongoDB
        Query query = new Query()
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize());

        Mono<List<T>> pageData = mongoTemplate.find(query, entityClass).collectList();
        Mono<Long> total = mongoTemplate.count(new Query(), entityClass);

        return Mono.zip(pageData, total)
                .map(tuple -> new PageSupport<>(
                        tuple.getT1(),
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        tuple.getT2()
                ));
    }

}
