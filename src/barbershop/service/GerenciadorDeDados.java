package barbershop.service;

import barbershop.model.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeDados {
    private GerenciadorArquivos gerenciadorArquivos;

    private List<Cliente> clientes;
    private List<Barbeiro> barbeiros;
    private List<Servico> servicos;
    private List<Produto> produtos ;
    private List<Insumo> insumos;
    private List<Promocao> promocoes;
    private List<Agendamento> agendamentos;

    //Construtor
    public GerenciadorDeDados() {
        this.gerenciadorArquivos = new  GerenciadorArquivos();
        this.clientes = new ArrayList<>();
        this.barbeiros = new ArrayList<>();
        this.servicos = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.insumos = new ArrayList<>();
        this.promocoes = new ArrayList<>();
        this.agendamentos = new ArrayList<>();

        carregarDadosInicias();
    }

    private void carregarDadosInicias() {
        System.out.println("Carregando dados do sistema...");
        
        this.clientes = gerenciadorArquivos.carregarClientes();
        this.barbeiros = gerenciadorArquivos.carregarBarbeiros();
        this.servicos =  gerenciadorArquivos.carregaServicos();
        this.produtos = gerenciadorArquivos.carregaProdutos();
        this.insumos = gerenciadorArquivos.carregarInsumos();
        this.promocoes = gerenciadorArquivos.carregarPromocoes();

        this.agendamentos = gerenciadorArquivos.carregarAgendamentos(clientes, barbeiros, servicos);

        System.out.println("Dados carregados com sucesso!");
    }

    public void cadastrarCliente(Cliente cliente){
        clientes.add(cliente);
        gerenciadorArquivos.salvarCliente(cliente);
    }

    public void cadastrarBarbeiro(Barbeiro barbeiro) {
        barbeiros.add(barbeiro);
        gerenciadorArquivos.salvarBarbeiro(barbeiro);
    }

    public void cadastrarServico(Servico servico) {
        servicos.add(servico);
        gerenciadorArquivos.salvarServico(servico);
    }

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
        gerenciadorArquivos.salvarProdutos(produto);
    }

    public void cadastrarInsumo(Insumo insumo) {
        insumos.add(insumo);
        gerenciadorArquivos.salvarInsumo(insumo);
    }

    public void cadastrarPromocao(Promocao promocao) {
        promocoes.add(promocao);
        gerenciadorArquivos.salvarPromocao(promocao);
    }

    public void agendar(Agendamento agendamento) {
        agendamentos.add(agendamento);
        gerenciadorArquivos.salvarAgendamento(agendamento);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Barbeiro> getBarbeiros() {
        return barbeiros;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public List<Promocao> getPromocoes() {
        return promocoes;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    // Método Para buscar cliente pelo CPF
    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
}