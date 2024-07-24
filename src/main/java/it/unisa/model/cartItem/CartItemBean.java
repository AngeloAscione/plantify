package it.unisa.model.cartItem;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CartItemBean implements Serializable {
    private int cartItemId;
    private int prodottoId;
    private int carrelloId;
    private int quantita;

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getProdottoId() {
        return prodottoId;
    }

    public void setProdottoId(int prodottoId) {
        this.prodottoId = prodottoId;
    }

    public int getCarrelloId() {
        return carrelloId;
    }

    public void setCarrelloId(int carrelloId) {
        this.carrelloId = carrelloId;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemBean that = (CartItemBean) o;
        return prodottoId == that.prodottoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodottoId);
    }
}
