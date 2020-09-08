package com.internet.shop.service;

import java.util.List;

public interface GenericService<T,V> {
    T create(T item);

    T get(V id);

    List<T> getAll();

    T update(T item);

    boolean deleteById(V id);

    boolean delete(T item);
}
