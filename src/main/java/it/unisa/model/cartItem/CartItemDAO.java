package it.unisa.model.cartItem;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CartItemDAO implements DAOInterface<CartItemBean, Long> {

    private CartItemBean extractCartItemFromResultSet(ResultSet resultSet) throws SQLException {
        CartItemBean cartItem = new CartItemBean();
        cartItem.setCartItemId(resultSet.getInt("cartItemId"));
        cartItem.setProdottoid(resultSet.getInt("prodottoid"));
        cartItem.setCarrelloid(resultSet.getInt("carrelloid"));
        cartItem.setQuantita(resultSet.getInt("quantita"));
        return cartItem;
    }
    @Override
    public CartItemBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT  * FROM cartItem WHERE id=?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractCartItemFromResultSet(resultSet);
                }
                return null;
            }
        }
    }

    @Override
    public Collection<CartItemBean> doRetrieveAll() throws SQLException {
        return null;
    }

    @Override
    public long doSave(CartItemBean obj) throws SQLException {
        return 0;
    }

    @Override
    public void doUpdate(CartItemBean cartItem) throws SQLException {
        String query = "UPDATE cartItem SET idProdotto = ?, idCarrello = ?, quantita = ? WHERE id = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, cartItem.getProdottoid());
            statement.setLong(2, cartItem.getCarrelloid());
            statement.setInt(3, cartItem.getQuantita());
            statement.setLong(4, cartItem.getCartItemId());
            statement.executeUpdate();
        }

    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM cartItem WHERE idProdotto =?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1,id);
            int rowDelete = statement.executeUpdate(); /*ritorna numero riga eliminate */
            return rowDelete >0 ;
            /* verifica se almeno una riga è stata effettivamente eliminata dalla tabella.*/
        }
    }
}
