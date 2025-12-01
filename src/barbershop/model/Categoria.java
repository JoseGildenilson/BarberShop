package barbershop.model;

public class Categoria {
    private String nome;
    private String descricao;

    // Construtor
    public Categoria(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    // getters
    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    // setters
    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return nome + " (" + descricao + ") ";
    }

}
