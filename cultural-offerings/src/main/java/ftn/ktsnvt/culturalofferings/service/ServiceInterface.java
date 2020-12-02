package ftn.ktsnvt.culturalofferings.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ServiceInterface<T> {
    
    Page<T> findAll(Pageable pageable);

    T findOne(Long id);

    T create(T entity) throws Exception;

    T update(T entity, Long id) throws Exception;

    void delete(Long id) throws Exception;
}