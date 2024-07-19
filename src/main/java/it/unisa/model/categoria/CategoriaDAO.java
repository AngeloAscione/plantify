package it.unisa.model.categoria;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class CategoriaDAO implements DAOInterface {
    @Override
    public CategoriaBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM Categoria WHERE CategoriaId = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractCategoriaFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    private CategoriaBean extractCategoriaFromResultSet(ResultSet resultSet) throws SQLException {
        CategoriaBean categoria = new CategoriaBean();
        categoria.setCategoriaId(resultSet.getInt("CategoriaId"));
        categoria.setNome(resultSet.getString("Nome"));
        categoria.setDescrizione(resultSet.getString("Descrizione"));
        return categoria;
    }

    @Override
    public Collection doRetrieveAll() throws SQLException {
        String query = "SELECT * FROM Categoria";
        Collection<CategoriaBean> categorias = new ArrayList<>();
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query) ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("CategoriaId");
                    String nome = resultSet.getString("Nome");
                    String descrizione = resultSet.getString("Descrizione");
                    categorias.add(new CategoriaBean(id, nome, descrizione));
                }
            }
        }
    return categorias;
    }

    @Override
    public long doSave(Object categoria) throws SQLException {
        CategoriaBean catBean = (CategoriaBean) categoria;
        String query = "INSERT INTO Categoria(Nome, Descrizione) VALUES (?, ?)";
        try (Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, catBean.getNome());
            stm.setString(2, catBean.getDescrizione());
            try(ResultSet resultSet = stm.executeQuery()){
                if (!resultSet.next()){
                    return -1;
                } else {
                   return 0;
                }
            }
        }
    }

    @Override
    public void doUpdate(Object product) throws SQLException {

    }

    @Override
    public boolean doDelete(Object code) throws SQLException {
        return false;
    }
}


