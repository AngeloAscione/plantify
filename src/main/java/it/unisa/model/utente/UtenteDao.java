package it.unisa.model.utente;

import it.unisa.model.DBConnector;

public class UtenteDao {

    private DBConnector connector;

    public UtenteDao(){
        this.connector = DBConnector.getInstance();
    }
    //TODO GESTIONE CON IL DATABASE QUERY
}
