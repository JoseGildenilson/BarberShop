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

    public double getPreco(){
        return valorBase;
    } 
}