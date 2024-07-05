package it.unisa.model.utente;

public class UserBean {
    private enum TipoUtente{
        CLIENTE, AMMINISTRATORE
    }
    private int id;
    private String nome;
    private String cognome;
    private String via;
    private int cap;
    private int civico;
    private String email;
    private String password;
    private String number;
    private TipoUtente tipo;

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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
