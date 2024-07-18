package it.unisa.model.carrello;

import com.mysql.cj.jdbc.ConnectionImpl;
import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CarrelloDAO implements DAOInterface<CarrelloBean, Long> {
    @Override
        public CarrelloBean doRetrieveByKey(long id) throws SQLException {
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

        @Override
        public Collection<CarrelloBean> doRetrieveAll(String order) throws SQLException {
            List<CarrelloBean> carrelli = new ArrayList<>();
            String query = "SELECT * FROM Carrello";
            try (Connection connection = DBConnector.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CarrelloBean carrello = extractCarrelloFromResultSet(resultSet);
                    carrelli.add(carrello);
                }
            }
            return carrelli;
        }

        @Override
        public long doSave(CarrelloBean carrello) throws SQLException {
            return 0;
        }

        @Override
        public void doUpdate(CarrelloBean carrello) throws SQLException {
            String query = "UPDATE Carrello SET idUtente = ? WHERE Id = ?";
            try (Connection connection = DBConnector.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, carrello.getIdUtente());
                statement.setLong(2, carrello.getId());
                statement.executeUpdate();
            }
        }

        @Override
        public boolean doDelete(Long id) throws SQLException {
            String query = "DELETE FROM Carrello WHERE id = ?";
            try (Connection connection = DBConnector.getInstance().getConnection();
                    PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                int rowsDeleted = statement.executeUpdate();
                return rowsDeleted > 0;
            }
        }

        private CarrelloBean extractCarrelloFromResultSet(ResultSet resultSet) throws SQLException {
            CarrelloBean carrello = new CarrelloBean();
            carrello.setId(resultSet.getLong("id"));
            carrello.setIdUtente(resultSet.getLong("IdUtente"));
            return carrello;
        }
    }

