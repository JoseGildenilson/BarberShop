package barbershop.model;

import barbershop.model.abstratas.ItemComercializavel;
import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private Cliente cliente;
    private List<ItemComercializavel> itens;
    private StatusPagamento statusPagamento;

    //Construtor
    public Comanda(Cliente cliente){
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    public void adicionarItem(ItemComercializavel item){
        this.itens.add(item);
    }

    public void removerItem(ItemComercializavel item){
        this.itens.remove(item);
    }

    public double calcularValorTotal() {
        double total = 0;
        for(ItemComercializavel item : itens){
            total += item.getPreco();
        }
        return total;
    }

    public double calcularValorComDesconto(Promocao promocao) {
        double total = calcularValorTotal();
        if(promocao != null && promocao.estaAtiva()){
            return total - (total *(promocao.getDescontoPercentual() / 100));
        }
        return total;
    }

    //Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemComercializavel> getItens() {
        return itens;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}