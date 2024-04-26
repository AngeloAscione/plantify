package it.unisa.model;

import it.unisa.exceptions.DBParameterNotInitializedException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

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


    /**
     * Metodo che permette di settare "globalmente" i parametri per effettuale la connessione al database
     * @param uri String di connessione
     * @param username Username per la connessione
     * @param passwd Password per la connessione
     */
    public void setParameter(String uri, String username, String passwd){
        this.uri = uri;
        this.username = username;
        this.passwd = passwd;
    }

    /**
     * Metodo per ottenere la connessione con il database
     * @return Oggetto connection col database
     * @throws SQLException
     * @throws DBParameterNotInitializedException
     */
    public Connection getConnection() throws SQLException, DBParameterNotInitializedException {

        if (uri == null || username == null || passwd == null )
            throw new DBParameterNotInitializedException("Parametri per la connessione non inizializzati. (uri, username, passwd): " + uri + " " + username + " " + passwd);

        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl(uri);
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername(username);
            p.setPassword(passwd);
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
        return datasource.getConnection();
    }
}
