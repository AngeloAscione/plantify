package it.unisa.model.categoria;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class CategoriaDAO implements DAOInterface {
    @Override
    public Object doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM Carrello WHERE id = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractCarrelloFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    private Object extractCategoriaFromResultSet(ResultSet resultSet) {

    }
}

    @Override
    public Collection doRetrieveAll(String order) throws SQLException {
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
