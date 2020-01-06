package cz.upce.fei.inptp.databasedependency.dao;

import org.springframework.stereotype.Component;

/**
 * Database access object
 * @param <T> Database object type
 */
//@Component
public interface DAO<T> {

    public void save(T object);
    public T load(String parameters);
    
}
