package com.company;

public interface Repository<T> {
    void create(T object);

    void update(T object);

    void delete(T object);

    T getById(Class tclass, long id);
}
