package it.unisa.model.carrello;

import java.io.Serializable;

public class CarrelloBean implements Serializable {
    private int carrelloId;
    private int utenteId;

    public int getCarrelloId() {
        return carrelloId;
    }

    public void setCarrelloId(int carrelloId) {
        this.carrelloId = carrelloId;
    }

    public int getUtenteid() {
        return utenteId;
    }

    public void setUtenteid(int utenteid) {
        utenteid = utenteid;
    }
}
