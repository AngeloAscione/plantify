package it.unisa.model;


import java.sql.SQLException;
import java.util.Collection;

public interface DAOInterface<T> {

    /**
     * Metodo per selezionare un record da una tabella, passata la sua chiave
     * @param id Chiave del record
     * @return Il record richiesto
     * @throws SQLException
     */
    public T doRetrieveByKey(int id) throws SQLException;

    /**
     * Netodo per ottenere tutti i record di una tabella
     * @return Lista di record
     * @throws SQLException
     */
    public Collection<T> doRetrieveAll() throws SQLException;

    /**
     * Metodo per salvare nella tabella un nuovo record
     *
     * @param obj Oggetto da salvare
     * @return True se l'oggetto è stato salvato, False altrimenti
     * @throws SQLException
     */
    public boolean doSave(T obj) throws SQLException;

    /**
     * Metodo per aggioranre un record presente in una tabella
     *
     * @param obj Oggetto da aggiornare
     * @return True se l'oggetto è stato aggiornato, False altrimenti
     * @throws SQLException
     */
    public boolean doUpdate(T obj) throws SQLException;

    /**
     * Metodo per eliminare un record da una tabella, passata la chiave
     * @param id
     * @return True se l'oggetto è stato modificato, False altrimenti
     * @throws SQLException
     */
    public boolean doDelete(int id) throws SQLException;
}