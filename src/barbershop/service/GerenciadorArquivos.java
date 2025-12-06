package barbershop.service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import barbershop.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {
    private static final String DIRETORIO_DADOS = "dados";

    private static final String ARQUIVO_CATEGORIAS = DIRETORIO_DADOS + File.separator + "categorias.csv";
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

    // --- Métodos para Produtos ---
    public void salvarProdutos(Produto produto){
        String nomeCat = (produto.getCategoria() != null) ? produto.getCategoria().getNome() : "Sem Categoria";
        
        String linha = String.format("%s;%.2f;%d;%s;%s", 
            produto.getNome(), 
            produto.getValorBase(), 
            produto.getQtdEstoque(), 
            produto.getMarca(),
            nomeCat
        );
        escreverNoArquivo(ARQUIVO_PRODUTOS, linha);
    }

    public List<Produto> carregaProdutos(List<Categoria> categoriasDisponiveis){
        List<Produto> produtos = new ArrayList<>();
        List<String> linhas = lerArquivo(ARQUIVO_PRODUTOS);

        for(String linha : linhas){
            try {
                String[] dados = linha.split(";");
                if(dados.length >= 5) {
                    String nome = dados[0];
                    double valor = Double.parseDouble(dados[1].replace(",", "."));
                    int qtd = Integer.parseInt(dados[2]);
                    String marca = dados[3];
                    String nomeCategoria = dados[4];

                    Categoria catEncontrada = null;
                    for(Categoria c : categoriasDisponiveis){
                        if(c.getNome().equalsIgnoreCase(nomeCategoria)){
                            catEncontrada = c;
                            break;
                        }
                    }
                
                    if(catEncontrada == null) {
                         catEncontrada = new Categoria("Geral", "Categoria padrão");
                    }

                    produtos.add(new Produto(nome, valor, qtd, marca, catEncontrada));
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler produto: " + linha);
            }
        }
        return produtos;
    }

    // --- Métodos para Serviços ---
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

    // --- Métodos para Clientes ---
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

    // --- Métodos para Barbeiros ---
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

    // --- Métodos para Insumos ---
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

    // --- Métodos para Promoções ---
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

    // --- Métodos para Agendamentos ---
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
                        agendamento.setStatus(status); 
                        agendamentos.add(agendamento);
                    }
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler agendamento: " + linha);
            }
        }
        return agendamentos;
    }

    // ! Método para reescrever um agendamento
    public void reescreverAgendamentos(List<Agendamento> agendamentos) {
        List<String> linhas = new ArrayList<>();
        for (Agendamento a : agendamentos) {
            String linha = String.format("%s;%s;%s;%s;%s",
                    a.getCliente().getCpf(),
                    a.getBarbeiro().getCpf(),
                    a.getServico().getNome(),
                    a.getDataHora().toString(),
                    a.getStatus().name());
            linhas.add(linha);
        }
        sobrescreverArquivo(ARQUIVO_AGENDAMENTOS, linhas);
    }


    // --- Métodos para Categorias ---
public void salvarCategoria(Categoria categoria) {
    String linha = String.format("%s;%s", categoria.getNome(), categoria.getDescricao());
    escreverNoArquivo(ARQUIVO_CATEGORIAS, linha);
}

public List<Categoria> carregarCategorias() {
    List<Categoria> categorias = new ArrayList<>();
    List<String> linhas = lerArquivo(ARQUIVO_CATEGORIAS);

    for (String linha : linhas) {
        try {
            String[] dados = linha.split(";");
            if (dados.length >= 2) {
                categorias.add(new Categoria(dados[0], dados[1]));
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler categoria: " + linha);
        }
    }
    return categorias;
}

public void reescreverCategorias(List<Categoria> categorias) {
    List<String> linhas = new ArrayList<>();
    for (Categoria c : categorias) {
        String linha = String.format("%s;%s", c.getNome(), c.getDescricao());
        linhas.add(linha);
    }
    sobrescreverArquivo(ARQUIVO_CATEGORIAS, linhas);
}

    // --- Métodos Auxiliares de Busca ---
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

    // --- Métodos de Leitura e Escrita Básica ---
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

    // --- MÉTODOS DE ATUALIZAÇÃO (REESCREVER ARQUIVOS COMPLETOS) ---

    // Sobrescreve (update) produtos (ex: estoque)
    public void reescreverProdutos(List<Produto> produtos) {
        List<String> linhas = new ArrayList<>();
        for (Produto p : produtos) {
            // Verifica se tem categoria, senão define um padrão para não quebrar o CSV
            String nomeCat = (p.getCategoria() != null) ? p.getCategoria().getNome() : "Sem Categoria";
            
            // Formato: nome;preço;estoque;marca;categoria
            String linha = String.format("%s;%.2f;%d;%s;%s",
                    p.getNome(), 
                    p.getValorBase(), 
                    p.getQtdEstoque(), 
                    p.getMarca(),
                    nomeCat);
            linhas.add(linha);
        }
        sobrescreverArquivo(ARQUIVO_PRODUTOS, linhas);
    }

    // Sobrescreve (update) clientes (ex: pontos ou dados pessoais)
    public void reescreverClientes(List<Cliente> clientes) {
        List<String> linhas = new ArrayList<>();
        for (Cliente c : clientes) {
            String linha = String.format("%s;%s;%s;%d",
                    c.getNome(), c.getCpf(), c.getTelefone(), c.getPontosFidelidade());
            linhas.add(linha);
        }
        sobrescreverArquivo(ARQUIVO_CLIENTES, linhas);
    }

    // Sobrescreve (update) barbeiros (ex: exclusão)
    public void reescreverBarbeiros(List<Barbeiro> barbeiros) {
        List<String> linhas = new ArrayList<>();
        for (Barbeiro b : barbeiros) {
            String linha = String.format("%s;%s;%s;%s;%.2f",
                    b.getNome(), b.getCpf(), b.getTelefone(), b.getEspecialidade(), b.getComissao());
            linhas.add(linha);
        }
        sobrescreverArquivo(ARQUIVO_BARBEIROS, linhas);
    }

    // --- MÉTODOS DE ATUALIZAÇÃO (REESCREVER ARQUIVOS FALTANTES) ---

    // Sobrescreve (update) serviços
    public void reescreverServicos(List<Servico> servicos) {
        List<String> linhas = new ArrayList<>();
        for (Servico s : servicos) {
            // Formato: nome;valor;tempo
            String linha = String.format("%s;%.2f;%d",
                    s.getNome(), s.getValorBase(), s.getTempoEstimado());
            linhas.add(linha);
        }
        sobrescreverArquivo(ARQUIVO_SERVICOS, linhas);
    }

    // Sobrescreve (update) insumos
    public void reescreverInsumos(List<Insumo> insumos) {
        List<String> linhas = new ArrayList<>();
        for (Insumo i : insumos) {
            // Formato: nome;qtd;custo
            String linha = String.format("%s;%d;%.2f",
                    i.getNome(), i.getQtdEstoque(), i.getCustoUnitario());
            linhas.add(linha);
        }
        sobrescreverArquivo(ARQUIVO_INSUMOS, linhas);
    }

    // Sobrescreve (update) promoções
    public void reescreverPromocoes(List<Promocao> promocoes) {
        List<String> linhas = new ArrayList<>();
        for (Promocao p : promocoes) {
            // Formato: nome;desconto;inicio;fim
            String linha = String.format("%s;%.2f;%s;%s",
                    p.getNome(), p.getDescontoPercentual(),
                    p.getDataInicio().toString(), p.getDataFim().toString());
            linhas.add(linha);
        }
        sobrescreverArquivo(ARQUIVO_PROMOCOES, linhas);
    }

    // Método genérico para sobrescrever (append=false)
    private void sobrescreverArquivo(String caminho, List<String> linhas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, false))) { 
            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar arquivo: " + caminho);
            e.printStackTrace();
        }
    }
}