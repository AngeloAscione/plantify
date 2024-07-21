package it.unisa.model.prodotto;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProdottoDAO implements DAOInterface<ProdottoBean> {

    @Override
    public ProdottoBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM Prodotto WHERE ProdottoID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractProdottoFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Collection<ProdottoBean> doRetrieveAll() throws SQLException {
        List<ProdottoBean> prodotti = new ArrayList<>();
        String query = "SELECT * FROM Prodotto";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                prodotti.add(extractProdottoFromResultSet(resultSet));
            }
        }
        return prodotti;
    }

    @Override
    public boolean doSave(ProdottoBean prodotto) throws SQLException {
        String query = "INSERT INTO Prodotto (CategoriaID, Nome, Descrizione, Prezzo, Qta, Foto) VALUES (?,?,?,?,?,?)";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, prodotto.getCategoriaId());
            stm.setString(2, prodotto.getNome());
            stm.setString(3, prodotto.getDescrizione());
            stm.setDouble(4, prodotto.getPrezzo());
            stm.setInt(5, prodotto.getQta());
            stm.setString(6, prodotto.getFoto());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(ProdottoBean prodotto) throws SQLException {
        String query = "UPDATE Prodotto SET CategoriaID = ?, Nome = ?, Descrizione = ?, Prezzo = ?, Qta = ?, Foto = ? WHERE ProdottoID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, prodotto.getCategoriaId());
            stm.setString(2, prodotto.getNome());
            stm.setString(3, prodotto.getDescrizione());
            stm.setDouble(4, prodotto.getPrezzo());
            stm.setInt(5, prodotto.getQta());
            stm.setString(6, prodotto.getFoto());
            stm.setInt(7, prodotto.getProdottoId());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM Prodotto WHERE ProdottoID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    private ProdottoBean extractProdottoFromResultSet(ResultSet resultSet) throws SQLException {
        ProdottoBean prodotto = new ProdottoBean();
        prodotto.setProdottoId(resultSet.getInt("ProdottoID"));
        prodotto.setCategoriaId(resultSet.getInt("CategoriaID"));
        prodotto.setNome(resultSet.getString("Nome"));
        prodotto.setDescrizione(resultSet.getString("Descrizione"));
        prodotto.setPrezzo(resultSet.getDouble("Prezzo"));
        prodotto.setQta(resultSet.getInt("Qta"));
        prodotto.setFoto(resultSet.getString("Foto"));
        return prodotto;
    }
}
