package com.internet.shop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, V> {
    T create(T item);

    T update(T item);

    Optional<T> get(V id);

    boolean delete(V id);

    List<T> getAll();
}
