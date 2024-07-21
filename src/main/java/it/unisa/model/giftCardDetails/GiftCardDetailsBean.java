package it.unisa.model.giftCardDetails;


public class GiftCardDetailsBean {
    private int giftCardId;
    private int prodottoId;
    private Stato stato;
    public enum Stato {
        VENDUTO,
        UTILIZZATO
    }

    public int getGiftCardId() {
        return giftCardId;
    }

    public void setGiftCardId(int giftCardId) {
        this.giftCardId = giftCardId;
    }

    public int getProdottoId() {
        return prodottoId;
    }

    public void setProdottoId(int prodottoId) {
        this.prodottoId = prodottoId;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}
