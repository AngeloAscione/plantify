package it.unisa.model;


import it.unisa.model.orderItem.OrderItemBean;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOInterface<T, K> {
    public T doRetrieveByKey(long code) throws SQLException;
    public Collection<T> doRetrieveAll(String order) throws SQLException;
    public long doSave(T product) throws SQLException;
    void doUpdate(T product) throws SQLException;
    boolean doDelete(K code) throws SQLException;
}