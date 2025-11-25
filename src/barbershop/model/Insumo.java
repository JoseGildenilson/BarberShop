package barbershop.model;

import barbershop.exception.EstoqueInsuficienteException;

public class Insumo{
    private String nome;
    private int qtdEstoque;
    private double custoUnitario;

    //Construtor 
    public Insumo(String nome, int qtdEstoque, double custoUnitario){
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.custoUnitario = custoUnitario;
    }

    //Métodos
    public void consumir(int quantidade) throws EstoqueInsuficienteException {
        if(quantidade > this.qtdEstoque){
            throw new EstoqueInsuficienteException("Estoque de: " + nome + ". insuficiente!");
        }
        this.qtdEstoque -= quantidade;
    }

    public void reporEstoque(int quantidade){
        this.qtdEstoque += quantidade;
    }

    //Getters
    public String getNome(){
        return nome;
    }

    public int getQtdEstoque(){
        return qtdEstoque;
    }

    public double getCustoUnitario(){
        return custoUnitario;
    }
}