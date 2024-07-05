package it.unisa.model.prodotto;

public abstract class Prodotto  {

    enum Categoria{
        GIFTCARD, PIANTA
    }
    private String nome;
    private String descrizione;
    private Categoria cat;
    private float prezzo;
    private int inStock;



    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCat() {
        return cat;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
}
