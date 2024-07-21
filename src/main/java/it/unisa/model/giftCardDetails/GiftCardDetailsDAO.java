package it.unisa.model.giftCardDetails;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GiftCardDetailsDAO implements DAOInterface<GiftCardDetailsBean> {

    @Override
    public GiftCardDetailsBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM GiftCardDetails WHERE GiftCardID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractGiftCardDetailsFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Collection<GiftCardDetailsBean> doRetrieveAll() throws SQLException {
        List<GiftCardDetailsBean> giftCardDetailsList = new ArrayList<>();
        String query = "SELECT * FROM GiftCardDetails";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                giftCardDetailsList.add(extractGiftCardDetailsFromResultSet(resultSet));
            }
        }
        return giftCardDetailsList;
    }

    @Override
    public boolean doSave(GiftCardDetailsBean giftCardDetails) throws SQLException {
        String query = "INSERT INTO GiftCardDetails (ProdottoID, Stato) VALUES (?,?)";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, giftCardDetails.getProdottoId());
            stm.setString(2, giftCardDetails.getStato().name());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(GiftCardDetailsBean giftCardDetails) throws SQLException {
        String query = "UPDATE GiftCardDetails SET ProdottoID = ?, Stato = ? WHERE GiftCardID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, giftCardDetails.getProdottoId());
            stm.setString(2, giftCardDetails.getStato().name());
            stm.setInt(3, giftCardDetails.getGiftCardId());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM GiftCardDetails WHERE GiftCardID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    private GiftCardDetailsBean extractGiftCardDetailsFromResultSet(ResultSet resultSet) throws SQLException {
        GiftCardDetailsBean giftCardDetails = new GiftCardDetailsBean();
        giftCardDetails.setGiftCardId(resultSet.getInt("GiftCardId"));
        giftCardDetails.setProdottoId(resultSet.getInt("ProdottoId"));
        giftCardDetails.setStato(GiftCardDetailsBean.Stato.valueOf(resultSet.getString("Stato")));
        return giftCardDetails;
    }
}
