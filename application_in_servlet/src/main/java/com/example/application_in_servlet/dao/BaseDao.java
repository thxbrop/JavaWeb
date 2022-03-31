package com.example.application_in_servlet.dao;

import java.util.List;

@SuppressWarnings("unused")
public interface BaseDao<T> {
    void insert(T t);

    void delete(T t);

    T getById(int id);

    List<T> getAll();
}
