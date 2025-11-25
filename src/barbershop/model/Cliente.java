package barbershop.model;

import barbershop.exception.PontosInsuficientesException;
import barbershop.model.abstratas.Pessoa;
import barbershop.model.interfaces.Fidelizavel;

public class Cliente extends Pessoa implements Fidelizavel{
    private int pontosFidelidade;


    //Construtor
    public Cliente(String nome, String cpf, String telefone){
        super(nome, cpf, telefone);
        this.pontosFidelidade = 0;
    }

    public int getPontosFidelidade(){
        return pontosFidelidade;
    }

    public void adicionarPontos(int pontos){
        if(pontos <= 0){
            System.out.println("Quantidade de pontos inválida.");
        }
        pontosFidelidade +=pontos;
    }

    public void resgatarPremios(){
        if(pontosFidelidade < 50){
            throw new PontosInsuficientesException("Quantidade de pontos insuficiente.");
        }
        pontosFidelidade -= 50;
        System.out.println("Parabéns! Prêmio resgatado. Novo saldo: " + pontosFidelidade);
    }

    public void exibirDados(){
        System.out.println("Nome: " + getNome() + "/CPF: " + getCpf() + "/Telefone: " + getTelefone());
    }
}