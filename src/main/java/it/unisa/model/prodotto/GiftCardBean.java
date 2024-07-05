package it.unisa.model.prodotto;

public class GiftCardBean extends Prodotto {

    private enum StatoGiftCard{
        NON_VENDUTA,
        VENDUTA,
        UTILIZZATA
    }
    private String codice;

    private StatoGiftCard stato;

    GiftCardBean(){
        this.setCat(Categoria.GIFTCARD);
    }

    public StatoGiftCard getStato() {
        return stato;
    }

    public void setStato(StatoGiftCard stato) {
        this.stato = stato;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getCodice() {
        return codice;
    }
}
