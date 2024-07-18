package it.unisa.model.cartItem;

import java.io.Serializable;

public class CartItemBean implements Serializable {
    private int cartItemId;
    private int prodottoid;
    private int carrelloid;
    private int quantita;

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getProdottoid() {
        return prodottoid;
    }

    public void setProdottoid(int prodottoid) {
        this.prodottoid = prodottoid;
    }

    public int getCarrelloid() {
        return carrelloid;
    }

    public void setCarrelloid(int carrelloid) {
        this.carrelloid = carrelloid;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
