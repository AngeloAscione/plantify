package it.unisa.model.WishListItem;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WishListItemDAO implements DAOInterface<WishListItemBean> {
    @Override
    public WishListItemBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM WishList WHERE whishListItemId = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractWishListItemFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    private WishListItemBean extractWishListItemFromResultSet(ResultSet resultSet) throws SQLException {
        WishListItemBean wishListItemBean = new WishListItemBean();
        wishListItemBean.setWhishListItemId(resultSet.getInt("whishListItemId"));
        wishListItemBean.setProdottoId(resultSet.getInt("prodottoId"));
        return wishListItemBean;
    }

    @Override
    public Collection<WishListItemBean> doRetrieveAll() throws SQLException {
        List<WishListItemBean> wishLists = new ArrayList<>();
        String query = "SELECT * FROM WishListItem";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                WishListItemBean wish = extractWishListItemFromResultSet(resultSet);
                wishLists.add(wish);
            }
        }
        return wishLists;
    }

    @Override
    public boolean doSave(WishListItemBean obj) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(WishListItemBean obj) throws SQLException {
        String query = "UPDATE WishList SET utenteId = ? WHERE wishListItemId = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, obj.getWhishListItemId());
            statement.setLong(2, obj.getProdottoId());
            statement.executeUpdate();

        }
        return false;
    }
    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM WishListItem WHERE wishLisItemId = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}