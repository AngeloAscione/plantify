package it.unisa.model.recensione;

import it.unisa.model.DAOInterface;
import it.unisa.utils.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecensioneDAO implements DAOInterface<RecensioneBean> {

    @Override
    public RecensioneBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM Recensione WHERE RecensioneID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractRecensioneFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Collection<RecensioneBean> doRetrieveAll() throws SQLException {
        List<RecensioneBean> recensioni = new ArrayList<>();
        String query = "SELECT * FROM Recensione";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                recensioni.add(extractRecensioneFromResultSet(resultSet));
            }
        }
        return recensioni;
    }

    @Override
    public boolean doSave(RecensioneBean recensioneBean) throws SQLException {
        String query = "INSERT INTO Recensione (UtenteID, ProdottoID, Descrizione, Titolo, Valutazione, Data) VALUES (?,?,?,?,?,?)";
        try(Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, recensioneBean.getUtenteId());
            stm.setInt(2, recensioneBean.getProdottoId());
            stm.setString(3, recensioneBean.getDescrizione());
            stm.setString(4, recensioneBean.getTitolo());
            stm.setInt(5, recensioneBean.getValutazione());
            stm.setDate(6, Date.valueOf(recensioneBean.getData().toLocalDate()));
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(RecensioneBean recensioneBean) throws SQLException {
        String query = "UPDATE Recensione SET UtenteID = ?, ProdottoID = ?, Descrizione = ?, Titolo = ?, Valutazione = ?, Data = ? WHERE Recensione.RecensioneID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, recensioneBean.getUtenteId());
            stm.setInt(2, recensioneBean.getProdottoId());
            stm.setString(3, recensioneBean.getDescrizione());
            stm.setString(4, recensioneBean.getTitolo());
            stm.setInt(5, recensioneBean.getValutazione());
            stm.setDate(6, Date.valueOf(recensioneBean.getData().toLocalDate()));
            return stm.executeUpdate() > 0;
        }

    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM Recensione WHERE RecensioneID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    private RecensioneBean extractRecensioneFromResultSet(ResultSet resultSet) throws SQLException {
        RecensioneBean recensioneBean = new RecensioneBean();
        recensioneBean.setRecensioneId(resultSet.getInt("RecensioneID"));
        recensioneBean.setUtenteId(resultSet.getInt("UtenteID"));
        recensioneBean.setProdottoId(resultSet.getInt("ProdottoID"));
        recensioneBean.setDescrizione(resultSet.getString("Descrizione"));
        recensioneBean.setTitolo(resultSet.getString("Titolo"));
        recensioneBean.setValutazione(resultSet.getInt("Valutazione"));
        recensioneBean.setData(resultSet.getDate("Data").toLocalDate().atStartOfDay());
        return recensioneBean;
    }

}