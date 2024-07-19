package it.unisa.model.categoria;

public class CategoriaBean {
    private  int categoriaId;
    private String nome;
    private String descrizione;

    public CategoriaBean(){

    }

    public CategoriaBean(int categoriaId, String nome, String descrizione) {
        this.categoriaId = categoriaId;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
