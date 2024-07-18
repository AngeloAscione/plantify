package it.unisa.model.cartItem;

import com.mysql.cj.jdbc.ConnectionImpl;
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
        cartItem.setId(resultSet.getLong("id"));
        cartItem.setIdProdotto(resultSet.getLong("idProdotto"));
        cartItem.setIdCarrello(resultSet.getLong("idCarrello"));
        cartItem.setQuantita(resultSet.getInt("quantita"));
        return cartItem;
    }
    @Override
    public CartItemBean doRetrieveByKey(long id) throws SQLException {
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
    public Collection<CartItemBean> doRetrieveAll(String order) throws SQLException {
        return null;
    }

    @Override
    public long doSave(CartItemBean product) throws SQLException {
        return 0;
    }

    @Override
    public void doUpdate(CartItemBean cartItem) throws SQLException {
        String query = "UPDATE cartItem SET idProdotto = ?, idCarrello = ?, quantita = ? WHERE id = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, cartItem.getIdProdotto());
            statement.setLong(2, cartItem.getIdCarrello());
            statement.setInt(3, cartItem.getQuantita());
            statement.setLong(4, cartItem.getId());
            statement.executeUpdate();
        }

    }

    @Override
    public boolean doDelete(Long id) throws SQLException {
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
