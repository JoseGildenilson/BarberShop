package barbershop.model;

import java.time.LocalDateTime;

import barbershop.model.abstratas.ItemComercializavel;
import barbershop.model.interfaces.Agendavel;

public class Servico extends ItemComercializavel implements Agendavel{
    private int tempoEstimado; // Em minutos
    
    //Construtor
    public Servico(String nome, double valorBase, int tempoEstimado){
        super(nome, valorBase);
        this.tempoEstimado = tempoEstimado;
    }

    //Métodos implementado de Agendavel
    public boolean verificarDisponibilidade(LocalDateTime dataHora){
        return true;
    }
    public void agendar(LocalDateTime dataHora){
        System.out.println("Agendamento concluido"); 
    }


    //Método herdado de ItemComercializavel
    public double getPreco(){
        return valorBase;
    }


    //Método da classe
    public int getTempoEstimado(){
        return tempoEstimado;
    } 
}