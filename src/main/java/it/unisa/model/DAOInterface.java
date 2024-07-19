package it.unisa.model;


import java.sql.SQLException;
import java.util.Collection;

public interface DAOInterface<T, K> {

    /**
     * Metodo per selezionare un record da una tabella, passata la sua chiave
     * @param id
     * @return
     * @throws SQLException
     */
    public T doRetrieveByKey(int id) throws SQLException;

    /**
     * Netodo per ottenere tutti i record di una tabella
     * @return
     * @throws SQLException
     */
    public Collection<T> doRetrieveAll() throws SQLException;

    /**
     * Metodo per salvare nella tabella un nuovo record
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public long doSave(T obj) throws SQLException;

    /**
     * Metodo per aggioranre un record presente in una tabella
     *
     * @param obj
     * @throws SQLException
     */
    void doUpdate(T obj) throws SQLException;

    /**
     * Metodo per eliminare un record da una tabella, passata la chiave
     * @param id
     * @return
     * @throws SQLException
     */
    boolean doDelete(int id) throws SQLException;
}