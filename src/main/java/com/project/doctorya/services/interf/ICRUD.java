package com.project.doctorya.services.interf;

import java.util.List;

public interface ICRUD<T> {
    List<T> getAll();
    T getById(String id);
    T create(T obj);
    T update(T obj);
    void delete(String id);
}
