package it.unisa.model.utente;

import it.unisa.model.DAOInterface;

import java.sql.SQLException;
import java.util.Collection;

public class UtenteDao implements DAOInterface {

    @Override
    public Object doRetrieveByKey(int id) throws SQLException {
        return null;
    }

    @Override
    public Collection doRetrieveAll() throws SQLException {
        return null;
    }

    @Override
    public long doSave(Object product) throws SQLException {
        return 0;
    }

    @Override
    public void doUpdate(Object product) throws SQLException {

    }

    @Override
    public boolean doDelete(Object code) throws SQLException {
        return false;
    }
}
