package barbershop.model;

import barbershop.model.abstratas.ItemComercializavel;

public class Produto extends ItemComercializavel{
    private int qtdEstoque;
    private String marca;

    // Construtor
    public Produto(String nome, double valorBase, int qtdEstoque, String marca){
        super(nome, valorBase);
        this.qtdEstoque = qtdEstoque;
        this.marca = marca;
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


    //Setters
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
}