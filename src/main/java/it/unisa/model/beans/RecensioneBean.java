package it.unisa.model.beans;

import java.util.HashMap;

public class RecensioneBean {
    /**
     * IDCLIENTE, DESCRIZIONE
     */

    private HashMap<Integer, Recensione> recensioni;
    private int idProdotto;

    public HashMap<Integer, Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(HashMap<Integer, Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }
}
