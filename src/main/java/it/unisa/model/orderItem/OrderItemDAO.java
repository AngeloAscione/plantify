package it.unisa.model.orderItem;

import it.unisa.model.DAOInterface;
import it.unisa.utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OrderItemDAO implements DAOInterface<OrderItemBean> {

    @Override
    public OrderItemBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM OrderItem WHERE OrderItemID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return  extractOrderItemFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Collection<OrderItemBean> doRetrieveAll() throws SQLException {
        List<OrderItemBean> orderItemList = new ArrayList<>();
        String query = "SELECT * FROM OrderItem";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                orderItemList.add(extractOrderItemFromResultSet(resultSet));
            }
        }
        return orderItemList;
    }

    @Override
    public boolean doSave(OrderItemBean orderItemBean) throws SQLException {
        String query = "INSERT INTO OrderItem (OrdineID, ProdottoID, Qta, Prezzo) VALUES (?, ?, ?, ?)";
        try(Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, orderItemBean.getOrdineId());
            stm.setInt(2, orderItemBean.getProdottoId());
            stm.setInt(3, orderItemBean.getQuantita());
            stm.setDouble(4, orderItemBean.getPrezzo());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(OrderItemBean orderItem) throws SQLException {
        String query = "UPDATE OrderItem SET OrdineID = ?, ProdottoID = ?, Qta = ?, Prezzo = ? WHERE OrderItemID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, orderItem.getOrdineId());
            statement.setInt(2, orderItem.getProdottoId());
            statement.setInt(3, orderItem.getQuantita());
            statement.setDouble(4, orderItem.getPrezzo());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM OrderItem WHERE OrderItemID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    private OrderItemBean extractOrderItemFromResultSet(ResultSet resultSet) throws SQLException {
        OrderItemBean orderItem = new OrderItemBean();
        orderItem.setOrderItemId(resultSet.getInt("OrderItemID"));
        orderItem.setOrdineId(resultSet.getInt("OrdineID"));
        orderItem.setProdottoId(resultSet.getInt("ProdottoID"));
        orderItem.setQuantita(resultSet.getInt("Qta"));
        orderItem.setPrezzo(resultSet.getDouble("Prezzo"));
        return orderItem;
    }

    public Set<OrderItemBean> doRetrieveByOrderId(int ordineId) throws SQLException {
        Set<OrderItemBean> orderItemList = new HashSet<>();
        String query = "SELECT * FROM OrderItem WHERE OrdineID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, ordineId);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                orderItemList.add(extractOrderItemFromResultSet(resultSet));
            }
        }
        return orderItemList;
    }
}
