package it.unisa.model.ordine;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrdineDAO implements DAOInterface<OrdineBean> {
    @Override
    public OrdineBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM Ordine WHERE OrdineID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractOrdineFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Collection<OrdineBean> doRetrieveAll() throws SQLException {
        List<OrdineBean> ordini = new ArrayList<>();
        String query = "SELECT * FROM Carrello";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                ordini.add(extractOrdineFromResultSet(resultSet));
            }
        }
        return ordini;
    }

    @Override
    public boolean doSave(OrdineBean ordine) throws SQLException {
        String query = "INSERT INTO Ordine (UtenteID, DataOrdine, Totale, Stato) VALUES (?, ?, ?, ?)";
        try(Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, ordine.getUtenteId());
            stm.setDate(2, Date.valueOf(ordine.getDataOrdine().toLocalDate()));
            stm.setDouble(3, ordine.getTotale());
            stm.setString(4, ordine.getStatoOrdine().toString());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(OrdineBean ordine) throws SQLException {
        String query = "UPDATE Ordine SET UtenteID = ? , DataOrdine = ?, Totale = ?, Stato = ? WHERE OrdineID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, ordine.getUtenteId());
            stm.setDate(2, Date.valueOf(ordine.getDataOrdine().toLocalDate()));
            stm.setDouble(3, ordine.getTotale());
            stm.setString(4, ordine.getStatoOrdine().toString());
            stm.setInt(5, ordine.getOrdineId());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM Ordine WHERE OrdineID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    private OrdineBean extractOrdineFromResultSet(ResultSet resultSet) throws SQLException {
        OrdineBean ordine = new OrdineBean();
        ordine.setOrdineId(resultSet.getInt("OrdineID"));
        ordine.setUtenteId(resultSet.getInt("UtenteID"));
        ordine.setDataOrdine(resultSet.getDate("DataOrdine").toLocalDate().atStartOfDay());
        ordine.setTotale(resultSet.getDouble("Totale"));
        ordine.setStatoOrdine(OrdineBean.StatoOrdine.valueOf(resultSet.getString("Stato").toUpperCase()));
        return ordine;
    }
}
