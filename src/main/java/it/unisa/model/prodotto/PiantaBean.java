package it.unisa.model.prodotto;

public class PiantaBean extends Prodotto{

    private String famiglia;
    private String genere;
    private String specie;
    private String nomeComune;

    PiantaBean() {
        this.setCat(Categoria.PIANTA);
    }


    public String getFamiglia() {
        return famiglia;
    }

    public void setFamiglia(String famiglia) {
        this.famiglia = famiglia;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }
}
