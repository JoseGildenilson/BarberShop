package barbershop.model;

import barbershop.model.abstratas.Pessoa;

public class Barbeiro extends Pessoa{
    private String especialiadade;
    private double comisao;

    //Construtor
    public Barbeiro(String nome, String cpf, String telefone){
        super(nome, cpf, telefone);
    }

    public void exibirDados(){
        System.out.println("Nome: " + getNome() + "/CPF: " + getCpf() + "/Telefone: " + getTelefone() + "/Especialidade: " + especialiadade);
    }
}