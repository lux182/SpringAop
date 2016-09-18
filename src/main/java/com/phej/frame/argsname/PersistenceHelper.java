package com.phej.frame.argsname;


public class PersistenceHelper<T> implements PersistenceApi {


    @Override
    public void save(Object bean) {
        System.out.println("save " + bean);
    }
}