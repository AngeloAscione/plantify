package it.unisa.model.categoria;

import it.unisa.model.DAOInterface;
import it.unisa.utils.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CategoriaDAO implements DAOInterface<CategoriaBean> {
    @Override
    public CategoriaBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM Categoria WHERE CategoriaID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractCategoriaFromResultSet(resultSet);
                }
            }
        }
        return null;
    }
    
    @Override
    public Collection<CategoriaBean> doRetrieveAll() throws SQLException {
        Collection<CategoriaBean> categorie = new ArrayList<>();
        String query = "SELECT * FROM Categoria";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    categorie.add(extractCategoriaFromResultSet(resultSet));
                }
            }
        }
        return categorie;
    }
    
    @Override
    public boolean doSave(CategoriaBean categoriaBean) throws SQLException {
        String query = "INSERT INTO Categoria(Nome, Descrizione) VALUES (?, ?)";
        try (Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, categoriaBean.getNome());
            stm.setString(2, categoriaBean.getDescrizione());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(CategoriaBean categoriaBean) throws SQLException {
        String query = "UPDATE Categoria SET Nome = ?, Descrizione = ? WHERE CategoriaID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, categoriaBean.getNome());
            stm.setString(2, categoriaBean.getDescrizione());
            stm.setInt(3, categoriaBean.getCategoriaId());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM Categoria WHERE CategoriaID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }
    
    private CategoriaBean extractCategoriaFromResultSet(ResultSet resultSet) throws SQLException {
        CategoriaBean categoria = new CategoriaBean();
        categoria.setCategoriaId(resultSet.getInt("CategoriaID"));
        categoria.setNome(resultSet.getString("Nome"));
        categoria.setDescrizione(resultSet.getString("Descrizione"));
        return categoria;
    }
}


