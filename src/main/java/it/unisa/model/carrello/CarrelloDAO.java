package it.unisa.model.carrello;

import it.unisa.model.DAOInterface;
import it.unisa.model.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CarrelloDAO implements DAOInterface<CarrelloBean> {

    @Override
    public CarrelloBean doRetrieveByKey(int id) throws SQLException {
        String query = "SELECT * FROM Carrello WHERE CarrelloID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return extractCarrelloFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Collection<CarrelloBean> doRetrieveAll() throws SQLException {
        List<CarrelloBean> carrelli = new ArrayList<>();
        String query = "SELECT * FROM Carrello";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                carrelli.add(extractCarrelloFromResultSet(resultSet));
            }
        }
        return carrelli;
    }

    @Override
    public boolean doSave(CarrelloBean carrello) throws SQLException {
        String query = "INSERT INTO Carrello (UtenteID) VALUES (?)";
        try(Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, carrello.getUtenteid());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doUpdate(CarrelloBean carrello) throws SQLException {
        String query = "UPDATE Carrello SET UtenteID = ? WHERE CarrelloID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, carrello.getUtenteid());
            stm.setInt(2, carrello.getCarrelloId());
            return stm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        String query = "DELETE FROM Carrello WHERE CarrelloID = ?";
        try (Connection connection = DBConnector.getInstance().getConnection();
                PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    private CarrelloBean extractCarrelloFromResultSet(ResultSet resultSet) throws SQLException {
        CarrelloBean carrello = new CarrelloBean();
        carrello.setCarrelloId(resultSet.getInt("CarrelloID"));
        carrello.setUtenteid(resultSet.getInt("UtenteID"));
        return carrello;
    }


    //TEST DELLA DAO
    public static void main(String ... args) throws SQLException {

        DBConnector.getInstance().setParameter("jdbc:mysql://bfpoyklzrtfcmj1mapbm-mysql.services.clever-cloud.com:3306/bfpoyklzrtfcmj1mapbm", "uy80mmnvr1uxpplr", "9vlC7RSQinwr6XkkMMdi");
        CarrelloDAO cd = new CarrelloDAO();


        //TEST RETRIEVEBYKEY
        System.out.println("Testing retrieve by key\n" +
                            "CarrelloID: " + cd.doRetrieveByKey(1).getCarrelloId());

        //TEST SAVE
        CarrelloBean cdb = new CarrelloBean();
        cdb.setUtenteid(3);
        cd.doSave(cdb);


        //TEST UPDATE
        CarrelloBean update = new CarrelloBean();
        update.setUtenteid(3);
        update.setCarrelloId(1);
        cd.doUpdate(update);

        //TEST DELETE
        cd.doDelete(4);

        //TEST RETRIEVEALL
        System.out.println("Testing retrieve all");
        for (CarrelloBean cb : cd.doRetrieveAll()){
            System.out.println("CarrelloID: " + cb.getCarrelloId() + " UtenteID: " + cb.getUtenteid());
        }

    }

}
