package it.unisa.model.beans;

public class UserBean {

    private enum TipoUtente{
        CLIENTE, AMMINISTRATORE
    }

    private int id;
    private String nome;
    private String cognome;
    private String via;
    private int cap;
    private String email;
    private TipoUtente tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUtente getTipo() {
        return tipo;
    }

    public void setTipo(TipoUtente tipo) {
        this.tipo = tipo;
    }
}
