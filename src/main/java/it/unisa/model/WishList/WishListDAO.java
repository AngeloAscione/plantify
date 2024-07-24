package it.unisa.model.WishList;

import it.unisa.model.DAOInterface;
import it.unisa.utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WishListDAO implements DAOInterface<WishListBean>  {

    @Override
    public WishListBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM Wishlist WHERE WishlistID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractWishListFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Collection<WishListBean> doRetrieveAll() throws SQLException {
        List<WishListBean> wishlists = new ArrayList<>();
        String query = "SELECT * FROM Wishlist";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                wishlists.add(extractWishListFromResultSet(resultSet));
            }
        }
        return wishlists;
    }

    @Override
    public boolean doSave(WishListBean wishList) throws SQLException {
        String query = "INSERT INTO Wishlist (UtenteID) VALUES (?)";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, wishList.getUtenteId());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(WishListBean wishList) throws SQLException {
        String query = "UPDATE Wishlist SET UtenteID = ? WHERE WishlistID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, wishList.getUtenteId());
            stm.setInt(2, wishList.getWishListId());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM Wishlist WHERE WishlistID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    private WishListBean extractWishListFromResultSet(ResultSet resultSet) throws SQLException {
        WishListBean wishList = new WishListBean();
        wishList.setWishListId(resultSet.getInt("WishlistID"));
        wishList.setUtenteId(resultSet.getInt("UtenteID"));
        return wishList;
    }

    public WishListBean doRetrieveByUserId(int utenteId) throws SQLException {
        String query = "SELECT * FROM Wishlist WHERE UtenteID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, utenteId);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractWishListFromResultSet(resultSet);
                }
            }
        }
        return null;
    }
}
