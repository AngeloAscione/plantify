package it.unisa.model.WishListItem;

import it.unisa.model.DAOInterface;
import it.unisa.utils.DBConnector;

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
        String query = "SELECT * FROM WishlistItem WHERE WishlistID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractWishListItemFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public List<WishListItemBean> doRetrieveByWishlistId(long wishlistId) throws SQLException {
        String query = "SELECT * FROM WishlistItem WHERE WishlistID = ?";
        ArrayList<WishListItemBean> items;
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setLong(1, wishlistId);

            items = new ArrayList<>();
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                WishListItemBean item = new WishListItemBean();
                item.setWishListItemId(rs.getInt("IdWishlist"));
                item.setProdottoId(rs.getInt("idProdotto"));
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public Collection<WishListItemBean> doRetrieveAll() throws SQLException {
        List<WishListItemBean> wishListItems = new ArrayList<>();
        String query = "SELECT * FROM WishlistItem";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                wishListItems.add(extractWishListItemFromResultSet(resultSet));
            }
        }
        return wishListItems;
    }

    @Override
    public boolean doSave(WishListItemBean wishListItem) throws SQLException {
        String query = "INSERT INTO WishlistItem (WishlistID, ProdottoID) VALUES (?, ?)";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, wishListItem.getWishListItemId());
            stm.setInt(2, wishListItem.getProdottoId());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(WishListItemBean wishListItem) throws SQLException {
        // La struttura della tabella non consente aggiornamenti diretti, quindi questa operazione non è supportata
        throw new UnsupportedOperationException("Update operation is not supported for WishlistItem.");
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM WishlistItem WHERE WishlistID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean doDelete(long wishlistId, long productId) throws SQLException {
        try (Connection connection = DBConnector.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM WishlistItem WHERE WishlistID = ? AND ProdottoID = ?")) {
            ps.setLong(1, wishlistId);
            ps.setLong(2, productId);
            return ps.executeUpdate() > 0;
        }
    }
    private WishListItemBean extractWishListItemFromResultSet(ResultSet resultSet) throws SQLException {
        WishListItemBean wishListItem = new WishListItemBean();
        wishListItem.setWishListItemId(resultSet.getInt("WishlistID"));
        wishListItem.setProdottoId(resultSet.getInt("ProdottoID"));
        return wishListItem;
    }
}