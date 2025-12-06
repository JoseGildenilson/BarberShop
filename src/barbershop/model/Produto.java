package barbershop.model;

import barbershop.model.abstratas.ItemComercializavel;

public class Produto extends ItemComercializavel{
    private int qtdEstoque;
    private String marca; 
    private Categoria categoria;

    // Construtor
    public Produto(String nome, double valorBase, int qtdEstoque, String marca, Categoria categoria){
        super(nome, valorBase);
        this.qtdEstoque = qtdEstoque;
        this.marca = marca;
        this.categoria = categoria;
    }

    //Getters
    public double getPreco(){
        return valorBase;
    }

    public int getQtdEstoque(){
        return qtdEstoque;
    }

    public String getMarca(){
        return marca;
    }

    public Categoria getCategoria(){
        return categoria;
    }
    //Setters
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
}