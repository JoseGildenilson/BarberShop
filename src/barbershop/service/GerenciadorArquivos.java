package barbershop.service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import barbershop.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GerenciadorArquivos{
    private static final String DIRETORIO_DADOS = "dados";

    private static final String ARQUIVO_PRODUTOS = DIRETORIO_DADOS + File.separator + "produtos.csv";
    private static final String ARQUIVO_SERVICOS = DIRETORIO_DADOS + File.separator + "servicos.csv";
    private static final String ARQUIVO_CLIENTES = DIRETORIO_DADOS + File.separator + "clientes.csv";
    private static final String ARQUIVO_BARBEIROS = DIRETORIO_DADOS + File.separator + "barbeiros.csv";
    private static final String ARQUIVO_INSUMOS = DIRETORIO_DADOS + File.separator + "insumos.csv";
    private static final String ARQUIVO_PROMOCOES = DIRETORIO_DADOS + File.separator + "promocoes.csv";
    private static final String ARQUIVO_AGENDAMENTOS = DIRETORIO_DADOS + File.separator + "agendamentos.csv";

    public GerenciadorArquivos(){
        verificarDiretorio();
    }

    public void verificarDiretorio(){
        File diretorio = new File(DIRETORIO_DADOS);
        if(!diretorio.exists()){
            diretorio.mkdir();
        } 
    }

    // Métodos para Produtos
    public void salvarProdutos(Produto produto){
        String linha = String.format("%s;%.2f;%d;%s",produto.getNome(), produto.getValorBase(), produto.getQtdEstoque(), produto.getMarca());
        escreverNoArquivo(ARQUIVO_PRODUTOS, linha);
    }

    public List<Produto> carregaProdutos(){
        List<Produto> produtos = new ArrayList<>();
        List<String> linhas = lerArquivo(ARQUIVO_PRODUTOS);

        for(String linha : linhas){
            try {
                String[] dados = linha.split(";");
                if(dados.length >= 4) {
                    String nome = dados[0];
                    double valor = Double.parseDouble(dados[1].replace(",", "."));
                    int qtd = Integer.parseInt(dados[2]);
                    String marca = dados[3];

                    produtos.add(new Produto(nome, valor, qtd, marca));
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao ler linha de produto: " + linha);
            }
        }
        return produtos;
    }


    //Métodos para Serviços
    public void salvarServico(Servico servico) {
        String linha = String.format("%s;%.2f;%d", servico.getNome(), servico.getValorBase(), servico.getTempoEstimado());
        escreverNoArquivo(ARQUIVO_SERVICOS, linha);
    }

    public List<Servico> carregaServicos() {
        List<Servico> servicos = new ArrayList<>();
        List<String> linhas = lerArquivo(ARQUIVO_SERVICOS);

        for(String linha : linhas){
            try {
                String[] dados = linha.split(";");
                if(dados.length >= 3) {
                    String nome = dados[0];
                    double valor = Double.parseDouble(dados[1].replace(",", "."));
                    int tempo = Integer.parseInt(dados[2]);
                    
                    servicos.add(new Servico(nome, valor, tempo));
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao ler a linha de serviço " + linha);
            }
        }
        return servicos;
    }

    //Métodos para cliente
    public void salvarCliente(Cliente cliente){
        String linha = String.format("%s;%s;%s;%d", cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getPontosFidelidade());
        escreverNoArquivo(ARQUIVO_CLIENTES, linha);
    }

    public List<Cliente> carregarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        List<String> linhas = lerArquivo(ARQUIVO_CLIENTES);

        for(String linha : linhas){
            try{
                String[] dados = linha.split(";");
                if(dados.length >= 4) {
                    String nome = dados[0];
                    String cpf = dados[1];
                    String telefone = dados[2];
                    int pontosArquivo = Integer.parseInt(dados[3]);

                    Cliente c = new Cliente(nome, cpf, telefone);
                    if (pontosArquivo > 0) {
                        c.adicionarPontos(pontosArquivo);
                    }
                    clientes.add(c);
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao ler cliente: " + linha);
            }
        }
        return clientes;
    }


    //Métodos para barbeiro
    public void salvarBarbeiro(Barbeiro barbeiro){
        String linha = String.format("%s;%s;%s;%s;%.2f", barbeiro.getNome(), barbeiro.getCpf(), barbeiro.getTelefone(), barbeiro.getEspecialidade(), barbeiro.getComissao());
        escreverNoArquivo(ARQUIVO_BARBEIROS, linha);
    }

    public List<Barbeiro> carregarBarbeiros() {
        List<Barbeiro> barbeiros = new ArrayList<>();
        List<String> linhas = lerArquivo(ARQUIVO_BARBEIROS);

        for (String linha : linhas) {
            try {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    String nome = dados[0];
                    String cpf = dados[1];
                    String telefone = dados[2];
                    String especialidade = dados[3];
                    double comissao = Double.parseDouble(dados[4].replace(",", "."));

                    barbeiros.add(new Barbeiro(nome, cpf, telefone, especialidade, comissao));
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter número na linha de barbeiro: " + linha);
            } catch (Exception e) {
                System.err.println("Erro genérico ao ler barbeiro: " + linha);
            }
        }
        return barbeiros;
    }
    //Métodos para Insumos
    public void salvarInsumo(Insumo insumo){
        String linha = String.format("%s;%d;%.2f", insumo.getNome(), insumo.getQtdEstoque(), insumo.getCustoUnitario());
        escreverNoArquivo(ARQUIVO_INSUMOS, linha);
    }

    public List<Insumo> carregarInsumos() {
        List<Insumo> insumos = new ArrayList<>();
        List<String> linhas = lerArquivo(ARQUIVO_INSUMOS);

        for(String linha : linhas){
            try{
                String[] dados = linha.split(";");
                if(dados.length >= 3) {
                    String nome = dados[0];
                    int qtd = Integer.parseInt(dados[1]);
                    double custo = Double.parseDouble(dados[2].replace(",", "."));

                    insumos.add(new Insumo(nome, qtd, custo));
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler insumo: " + linha);
            }
         }
         return insumos;
    }

    //Métodos para promoções
    public void salvarPromocao(Promocao promocao){
        String linha = String.format("%s;%.2f;%s;%s", promocao.getNome(), promocao.getDescontoPercentual(), promocao.getDataInicio().toString(), promocao.getDataFim().toString());
        escreverNoArquivo(ARQUIVO_PROMOCOES, linha);
    }

    public List<Promocao> carregarPromocoes() {
        List<Promocao> promocoes = new ArrayList<>();
        List<String> linhas = lerArquivo(ARQUIVO_PROMOCOES);

        for (String linha : linhas) {
            try {
                String[] dados = linha.split(";");
                if (dados.length >= 4) {
                    String nome = dados[0];
                    double desconto = Double.parseDouble(dados[1].replace(",", "."));
                    LocalDate dataInicio = LocalDate.parse(dados[2]);
                    LocalDate dataFim = LocalDate.parse(dados[3]);

                    promocoes.add(new Promocao(nome, desconto, dataInicio, dataFim));
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler promoção: " + linha);
            }
        }
        return promocoes;
    }

    // Métodos para agendamentos
    public void salvarAgendamento(Agendamento agendamento) {
        String linha = String.format("%s;%s;%s;%s;%s", agendamento.getCliente().getCpf(), agendamento.getBarbeiro().getCpf(), agendamento.getServico().getNome(), agendamento.getDataHora().toString(), agendamento.getStatus().name());
        escreverNoArquivo(ARQUIVO_AGENDAMENTOS, linha);
    }

    public List<Agendamento> carregarAgendamentos(List<Cliente> clientes, List<Barbeiro> barbeiros, List<Servico> servicos){
        List<Agendamento> agendamentos = new ArrayList<>();
        List<String> linhas = lerArquivo(ARQUIVO_AGENDAMENTOS);

        for (String linha : linhas) {
            try {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    String cpfCliente = dados[0];
                    String cpfBarbeiro = dados[1];
                    String nomeServico = dados[2];
                    LocalDateTime dataHora = LocalDateTime.parse(dados[3]);
                    StatusAgendamento status = StatusAgendamento.valueOf(dados[4]);

                    Cliente clienteEncontrado = buscarClientePorCpf(clientes, cpfCliente);
                    Barbeiro barbeiroEncontrado = buscarBarbeiroPorCpf(barbeiros, cpfBarbeiro);
                    Servico servicoEncontrado = buscarServicoPorNome(servicos, nomeServico);

                    if (clienteEncontrado != null && barbeiroEncontrado != null && servicoEncontrado != null) {
                        Agendamento agendamento = new Agendamento(clienteEncontrado, barbeiroEncontrado, servicoEncontrado, dataHora);
                        agendamento.setStatus(status); // Restaura o status (ex: se já foi pago/concluido)
                        agendamentos.add(agendamento);
                    }
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler agendamento: " + linha);
            }
        }
        return agendamentos;
    }

    // Métodos auxiliares para busca
    private Cliente buscarClientePorCpf(List<Cliente> clientes, String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) return c;
        }
        return null;
    }

    private Barbeiro buscarBarbeiroPorCpf(List<Barbeiro> barbeiros, String cpf) {
        for (Barbeiro b : barbeiros) {
            if (b.getCpf().equals(cpf)) return b;
        }
        return null;
    }

    private Servico buscarServicoPorNome(List<Servico> servicos, String nome) {
        for (Servico s : servicos) {
            if (s.getNome().equalsIgnoreCase(nome)) return s;
        }
        return null;
    }

    //Métodos auxiliares da classe
    private void escreverNoArquivo(String caminho, String linha) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados em: " + caminho);
            e.printStackTrace();
        }
    }

    private List<String> lerArquivo(String caminho) {
        List<String> linhas = new ArrayList<>();
        File arquivo = new File(caminho);

        if(!arquivo.exists()) {
            return linhas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if(!linha.trim().isEmpty()) {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler dados de: " + caminho);
        }
        return linhas;
    }
}