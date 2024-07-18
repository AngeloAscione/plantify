package it.unisa.model.orderItem;

public class OrderItemBean  {
    private long id;
    private  String name;
    private long idProdotto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(long idProdotto) {
        this.idProdotto = idProdotto;
    }

    public long getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(long idOrdine) {
        this.idOrdine = idOrdine;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    private  long idOrdine;
    private  double prezzo;
    private  int quantita;

}
