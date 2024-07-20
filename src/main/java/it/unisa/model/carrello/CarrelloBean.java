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

    public int getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }
}
