package it.unisa.model.beans;

public class GiftCardBean extends Vendibile {

    private enum StatoGiftCard{
        NON_VENDUTA,
        VENDUTA,
        UTILIZZATA
    }

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
}
