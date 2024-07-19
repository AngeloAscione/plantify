package it.unisa;

import it.unisa.model.DBConnector;
import it.unisa.model.categoria.CategoriaBean;
import it.unisa.model.categoria.CategoriaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class TestMain {


    public static void main(String ... args) {
        DBConnector connector = DBConnector.getInstance();
        connector.setParameter("jdbc:mysql://bfpoyklzrtfcmj1mapbm-mysql.services.clever-cloud.com:3306/bfpoyklzrtfcmj1mapbm", "uy80mmnvr1uxpplr", "9vlC7RSQinwr6XkkMMdi");

        try (Connection conn = connector.getConnection();){
            System.out.println("OK");
        }catch (SQLException ex){
            ; //doNothing
        }


        CategoriaDAO catDao = new CategoriaDAO();
        try {
            Collection<CategoriaBean> categoriaBeanList = catDao.doRetrieveAll();
            for (CategoriaBean cat : categoriaBeanList){
                System.out.println(cat.getCategoriaId() + " " + cat.getDescrizione() + " " + cat.getNome());
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            System.out.println(catDao.doRetrieveByKey(9).getNome());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
