package barbershop.model;

import barbershop.model.abstratas.Pessoa;

public class Barbeiro extends Pessoa{
    private String especialidade;
    private double comissao;

    //Construtor
    public Barbeiro(String nome, String cpf, String telefone, String especialidade, double comissao){
        super(nome, cpf, telefone);
        this.especialidade = especialidade;
        this.comissao = comissao;
    }

    public void exibirDados(){
        System.out.println("Nome: " + getNome() + "/CPF: " + getCpf() + "/Telefone: " + getTelefone() + "/Especialidade: " + especialidade + "/ Comissão: " + comissao);
    }

    // getters
    public String getEspecialidade(){
        return especialidade;
    }

    public double getComissao(){
        return comissao;
    }

    // setters
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }
}   