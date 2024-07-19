package it.unisa.model;


import java.sql.SQLException;
import java.util.Collection;

public interface DAOInterface<T, K> {
    public T doRetrieveByKey(int id) throws SQLException;
    public Collection<T> doRetrieveAll() throws SQLException;
    public long doSave(T product) throws SQLException;
    void doUpdate(T product) throws SQLException;
    boolean doDelete(K code) throws SQLException;
}