package barbershop.model;

import java.time.LocalDate;

public class Promocao {
    private String nome;
    private double descontoPercentual;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    //Construtor
    public Promocao(String nome, double descontoPercentual, LocalDate dataInicio, LocalDate dataFim){
        this.nome = nome;
        this.descontoPercentual = descontoPercentual;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public boolean estaAtiva(){
        LocalDate hoje = LocalDate.now();
        return (hoje.isEqual(dataInicio) || hoje.isAfter(dataInicio)) && (hoje.isEqual(dataFim) || hoje.isBefore(dataFim));
    }

    //Getters
    public String getNome(){
        return nome;
    }

    public double getDescontoPercentual(){
        return descontoPercentual;
    }

    public LocalDate getDataInicio(){
        return dataInicio;
    }

    public LocalDate getDataFim(){
        return dataFim; 
    }
}