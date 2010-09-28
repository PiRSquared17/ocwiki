package oop.controller.rest.bean;

public interface Mapper<U,V> {    
    public U apply(V value);
    public V get(U value);
}
