package com.example.clothes_2nd.service.admin;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<E, T> {
    List<E> findAll();

    Optional<E> findById(T id);

    E save(E e);

    void delete(E e);

    void deleteById(T id);
}
