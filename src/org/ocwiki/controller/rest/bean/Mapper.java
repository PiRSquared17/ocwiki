package org.ocwiki.controller.rest.bean;

public interface Mapper<U,V> {    
    public U toBean(V value);
    public V toEntity(U value);
}
