package it.unisa.model.recensione;

import it.unisa.model.DAOInterface;

import java.sql.SQLException;
import java.util.Collection;

public class RecensioneDAO implements DAOInterface {

    @Override
    public Object doRetrieveByKey(int id) throws SQLException {
        return null;
    }

    @Override
    public Collection doRetrieveAll() throws SQLException {
        return null;
    }

    @Override
    public long doSave(Object obj) throws SQLException {
        return 0;
    }

    @Override
    public void doUpdate(Object obj) throws SQLException {

    }

    @Override
    public boolean doDelete(int code) throws SQLException {
        return false;
    }
}