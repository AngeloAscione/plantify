package it.unisa.utils;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe che si occupa della connessione con il database
 */
public class DBConnector {
    private static DBConnector instance = null;
    private String uri = null;
    private String username = null;
    private String passwd = null;
    private DataSource datasource = null;

    /**
     * Metodo utilizzato per ottenere l'istanza del DBConnector.
     * Ho utilizzato il pattern Singleton in modo da poter tenere un'istanza singola
     * @return Instanza DBConnector
     */
    public static DBConnector getInstance(){
        if (instance == null){
            instance = new DBConnector();
        }
        return instance;
    }

    private DBConnector(){
        this.uri = "jdbc:mysql://bfpoyklzrtfcmj1mapbm-mysql.services.clever-cloud.com:3306/bfpoyklzrtfcmj1mapbm";
        this.username = "uy80mmnvr1uxpplr";
        this.passwd = "9vlC7RSQinwr6XkkMMdi";
    }

    /**
     * Metodo per ottenere la connessione con il database
     * @return Oggetto connection col database
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {

        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl(uri);
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername(username);
            p.setPassword(passwd);
            p.setMaxActive(1);
            p.setMaxIdle(1);
            p.setInitialSize(1);
            p.setMinIdle(4);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
        return datasource.getConnection();
    }
}
