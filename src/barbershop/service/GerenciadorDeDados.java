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

        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {
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

    // --- GETTERS ---
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

    // --- BUSCA ---
    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    // --- MÉTODOS PARA SALVAR ALTERAÇÕES GERAIS (UPDATE) ---

    public void atualizarEstoqueProdutos() {
        gerenciadorArquivos.reescreverProdutos(this.produtos);
    }

    public void atualizarPontosClientes() {
        gerenciadorArquivos.reescreverClientes(this.clientes);
    }

    // --- MÉTODOS DE REMOÇÃO E EDIÇÃO ---

    public boolean removerCliente(String cpf) {
        Cliente clienteParaRemover = buscarClientePorCpf(cpf);
        if (clienteParaRemover != null) {
            clientes.remove(clienteParaRemover); // Remove da RAM
            gerenciadorArquivos.reescreverClientes(this.clientes); // Atualiza o arquivo
            return true;
        }
        return false;
    }

    public boolean removerBarbeiro(String cpf) {
        Barbeiro barbeiroParaRemover = null;
        for (Barbeiro b : barbeiros) {
            if (b.getCpf().equals(cpf)) {
                barbeiroParaRemover = b;
                break;
            }
        }
        
        if (barbeiroParaRemover != null) {
            barbeiros.remove(barbeiroParaRemover);
            gerenciadorArquivos.reescreverBarbeiros(this.barbeiros);
            return true;
        }
        return false;
    }
    
    // Método para salvar qualquer alteração feita nos objetos de cliente (ex: edição de telefone)
    public void salvarAlteracoesClientes() {
        gerenciadorArquivos.reescreverClientes(this.clientes);
    }

    // Método para salvar alterações nos objetos de barbeiro
    public void salvarAlteracoesBarbeiros() {
        gerenciadorArquivos.reescreverBarbeiros(this.barbeiros);
    }
}