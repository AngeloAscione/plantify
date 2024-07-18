package it.unisa.model.orderItem;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class OrderItemDAO implements DAOInterface<OrderItemBean, Long> {
    @Override
    public OrderItemBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM OrderItem WHERE idItem = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return   extractOrderItemFromResultSet(resultSet);
                }
            }
        }
        return null;
    }


    @Override
    public Collection doRetrieveAll(String order) throws SQLException {
        return null;
    }

    @Override
    public long doSave(OrderItemBean product) throws SQLException {
        return 0;
    }

    @Override
    public void doUpdate(OrderItemBean orderItem) throws SQLException {
        String query = "UPDATE OrderItem SET idProdotto = ?, IdOrdine = ?, Prezzo = ?, Quantita = ?, iva = ?, Nome = ? WHERE id = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, orderItem.getIdProdotto());
            statement.setLong(2, orderItem.getIdOrdine());
            statement.setDouble(3, orderItem.getPrezzo());
            statement.setLong(4, orderItem.getQuantita());
            statement.setString(6, orderItem.getName());
            statement.setLong(7, orderItem.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public boolean doDelete(Long id) throws SQLException {
        String query = "DELETE FROM OrderItem WHERE id = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    private OrderItemBean extractOrderItemFromResultSet(ResultSet resultSet) throws SQLException {
        OrderItemBean orderItem = new OrderItemBean();
        orderItem.setId(resultSet.getLong("id"));
        orderItem.setIdProdotto(resultSet.getLong("idProdotto"));
        orderItem.setIdOrdine(resultSet.getLong("IdOrdine"));
        orderItem.setPrezzo(resultSet.getDouble("Prezzo"));
        orderItem.setQuantita(resultSet.getInt("Quantita"));
        orderItem.setName(resultSet.getString("Nome"));
        return orderItem;
    }

}
