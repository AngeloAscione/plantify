package it.unisa.model.utente;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;
import it.unisa.utils.PasswordTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UtenteDAO implements DAOInterface<UtenteBean> {

    @Override
    public UtenteBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM Utente WHERE UtenteID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractUtenteFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Collection<UtenteBean> doRetrieveAll() throws SQLException {
        List<UtenteBean> utenti = new ArrayList<>();
        String query = "SELECT * FROM Utente";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                utenti.add(extractUtenteFromResultSet(resultSet));
            }
        }
        return utenti;
    }

    @Override
    public boolean doSave(UtenteBean utente) throws SQLException {
        String query = "INSERT INTO Utente (Nome, Cognome, Email, Password, Via, Civico, CAP, Telefono, IsAdmin, Loggato) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, utente.getNome());
            stm.setString(2, utente.getCognome());
            stm.setString(3, utente.getEmail());
            stm.setString(4, utente.getPassword());
            stm.setString(5, utente.getVia());
            stm.setInt(6, utente.getCivico());
            stm.setInt(7, utente.getCap());
            stm.setString(8, utente.getTelefono());
            stm.setBoolean(9, utente.isAdmin());
            stm.setBoolean(10, utente.isLogged());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(UtenteBean utente) throws SQLException {
        String query = "UPDATE Utente SET Nome = ?, Cognome = ?, Email = ?, Password = ?, Via = ?, Civico = ?, CAP = ?, Telefono = ?, IsAdmin = ?, Loggato = ? WHERE UtenteID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, utente.getNome());
            stm.setString(2, utente.getCognome());
            stm.setString(3, utente.getEmail());
            stm.setString(4, utente.getPassword());
            stm.setString(5, utente.getVia());
            stm.setInt(6, utente.getCivico());
            stm.setInt(7, utente.getCap());
            stm.setString(8, utente.getTelefono());
            stm.setBoolean(9, utente.isAdmin());
            stm.setBoolean(10, utente.isLogged());
            stm.setInt(11, utente.getUtenteId());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM Utente WHERE UtenteID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    public UtenteBean doRetrieveByEmailAndPassword(String email, String password) throws SQLException {
        String query = "SELECT * FROM Utente WHERE Email = ? AND Password = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, email);
            stm.setString(2, PasswordTool.cipherPassword(password));
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractUtenteFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    private UtenteBean extractUtenteFromResultSet(ResultSet resultSet) throws SQLException {
        UtenteBean utente = new UtenteBean();
        utente.setUtenteId(resultSet.getInt("UtenteID"));
        utente.setNome(resultSet.getString("Nome"));
        utente.setCognome(resultSet.getString("Cognome"));
        utente.setEmail(resultSet.getString("Email"));
        utente.setPassword(resultSet.getString("Password"));
        utente.setVia(resultSet.getString("Via"));
        utente.setCivico(resultSet.getInt("Civico"));
        utente.setCap(resultSet.getInt("CAP"));
        utente.setTelefono(resultSet.getString("Telefono"));
        utente.setAdmin(resultSet.getBoolean("IsAdmin"));
        utente.setLogged(resultSet.getBoolean("Loggato"));
        return utente;
    }

}
