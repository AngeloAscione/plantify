package it.unisa.model.cartItem;

import it.unisa.model.DAOInterface;
import it.unisa.utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CartItemDAO implements DAOInterface<CartItemBean> {

    @Override
    public CartItemBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT  * FROM cartItem WHERE id=?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractCartItemFromResultSet(resultSet);
                }
            }
        }
        return null;
    }


    public Collection<CartItemBean> doRetrieveAllByCartId(int id) throws SQLException {
        List<CartItemBean> cartItemBeanList = new ArrayList<>();
        String query = "SELECT * FROM CartItem where CarrelloID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                cartItemBeanList.add(extractCartItemFromResultSet(resultSet));
            }
        }
        return cartItemBeanList;
    }

    @Override
    public Collection<CartItemBean> doRetrieveAll() throws SQLException {
        List<CartItemBean> cartItemBeanList = new ArrayList<>();
        String query = "SELECT * FROM CartItem";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                cartItemBeanList.add(extractCartItemFromResultSet(resultSet));
            }
        }
        return cartItemBeanList;
    }

    @Override
    public boolean doSave(CartItemBean cartItemBean) throws SQLException {
        String query = "INSERT INTO CartItem (CarrelloID, ProdottoID, Qta) VALUES (?, ?, ?)";
        boolean returnValue;
        try(Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, cartItemBean.getCarrelloId());
            stm.setInt(2, cartItemBean.getProdottoId());
            stm.setInt(3, cartItemBean.getQuantita());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(CartItemBean cartItem) throws SQLException {
        String query = "UPDATE CartItem SET ProdottoID = ?, CarrelloID = ?, Qta = ? WHERE CartItemID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartItem.getProdottoId());
            statement.setInt(2, cartItem.getCarrelloId());
            statement.setInt(3, cartItem.getQuantita());
            statement.setInt(4, cartItem.getCartItemId());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM CartItem WHERE ProdottoID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,id);
            int rowDelete = statement.executeUpdate(); /*ritorna numero riga eliminate */
            return rowDelete >0 ;
            /* verifica se almeno una riga è stata effettivamente eliminata dalla tabella.*/
        }
    }


    private CartItemBean extractCartItemFromResultSet(ResultSet resultSet) throws SQLException {
        CartItemBean cartItem = new CartItemBean();
        cartItem.setCartItemId(resultSet.getInt("CartItemID"));
        cartItem.setProdottoId(resultSet.getInt("ProdottoID"));
        cartItem.setCarrelloId(resultSet.getInt("CarrelloID"));
        cartItem.setQuantita(resultSet.getInt("Qta"));
        return cartItem;
    }
}
