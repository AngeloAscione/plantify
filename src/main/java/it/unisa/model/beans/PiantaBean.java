package it.unisa.model.beans;

public class PiantaBean extends Vendibile{

    private String dominio;
    private String regno;
    private String phylum;
    private String classe;
    private String ordine;
    private String famiglia;
    private String genere;
    private String specie;
    private String nomeComune;

    PiantaBean() {
        this.setCat(Categoria.PIANTA);
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getPhylum() {
        return phylum;
    }

    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getOrdine() {
        return ordine;
    }

    public void setOrdine(String ordine) {
        this.ordine = ordine;
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
