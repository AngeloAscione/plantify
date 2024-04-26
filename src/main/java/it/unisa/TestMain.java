package it.unisa;

import it.unisa.model.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class TestMain {


    public static void main(String ... args) {
        DBConnector connector = DBConnector.getInstance();
        connector.setParameter("jdbc:mysql://localhost:3306/Plantify", "Plantify", "progetto123");

        try (Connection conn = connector.getConnection();){
            System.out.println("OK");
        }catch (SQLException ex){
            ; //doNothing
        }

        DBConnector connector2 = DBConnector.getInstance();
        try (Connection conn = connector2.getConnection();){
            System.out.println("OK");
        }catch (SQLException ex){
            ; //doNothing
        }

    }
}
