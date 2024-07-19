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
    public boolean doSave(Object obj) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Object obj) throws SQLException {

        return false;
    }

    @Override
    public boolean doDelete(int code) throws SQLException {
        return false;
    }
}