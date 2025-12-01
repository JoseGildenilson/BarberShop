package barbershop.model;

import java.time.LocalDateTime;


public class Agendamento{
    private Cliente cliente;
    private Barbeiro barbeiro;
    private Servico servico;
    private LocalDateTime dataHora;
    private StatusAgendamento status;

    //Construtor
    public Agendamento(Cliente cliente, Barbeiro barbeiro, Servico servico, LocalDateTime dataHora){
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.servico = servico;
        this.dataHora = dataHora;
        this.status = StatusAgendamento.PENDENTE;
    }

    //Getters
    public Cliente getCliente(){
        return cliente;
    }

    public Barbeiro getBarbeiro(){
        return barbeiro;
    }

    public Servico getServico(){
        return servico;
    }

    public LocalDateTime getDataHora(){
        return dataHora;
    }

    public StatusAgendamento getStatus(){
        return status;
    }

    //Setters
    public void setStatus(StatusAgendamento status){
        this.status = status;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}