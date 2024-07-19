package it.unisa.model.WishList;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WishListDAO implements DAOInterface<WishListBean, Long>  {
    @Override
    public WishListBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM WishList WHERE wishListId = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractWishListFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    private WishListBean extractWishListFromResultSet(ResultSet resultSet) throws SQLException {
        WishListBean wish = new WishListBean();
        wish.setWishListId(resultSet.getInt("wishListId"));
        wish.setUtenteId(resultSet.getInt("utendeId"));
        return wish;
    }

    @Override
    public Collection<WishListBean> doRetrieveAll() throws SQLException {
        List<WishListBean> wishLists = new ArrayList<>();
        String query = "SELECT * FROM WishList";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                WishListBean wish = extractWishListFromResultSet(resultSet);
                wishLists.add(wish);
            }
        }
        return wishLists;
    }

    @Override
    public long doSave(WishListBean product) throws SQLException {
        return 0;
    }

    @Override
    public void doUpdate(WishListBean product) throws SQLException {
        String query = "UPDATE WishList SET utenteId = ? WHERE wishListId = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, product.getWishListId());
            statement.setLong(2, product.getUtenteId());
            statement.executeUpdate();
        }
    }

    @Override
    public boolean doDelete(Long id) throws SQLException {
        String query = "DELETE FROM WishList WHERE wishListId = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}
