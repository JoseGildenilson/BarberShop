package barbershop.service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import barbershop.model.*;
import barbershop.exception.*;
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
    private List<Categoria> categorias;

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
        this.categorias = new ArrayList<>();

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

    public List<Categoria> getCategorias() {
        return categorias;
    }

    // ? --- BUSCA ---

    // ! Verificar se o CPF cliente existe no arquivo 
    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    // ! Verificar se o CPF do barbeiro existe no arquivo 
    public Barbeiro buscarBarbeiroPorCpf(String cpf) {
        for (Barbeiro b : barbeiros) {
            if (b.getCpf().equals(cpf)) {
                return b;
            }
        }
        return null;
    }
 
    // ! Vericiar se o nome do serviço existe no arquivo 
    public Servico buscarServicoPorNome(String nome) {
        String nomeBusca = normalizaTexto(nome);
        
        for (Servico s : servicos) {
            String nomeExistente = s.getNome().toLowerCase().replace(" ", "");
            
            if (nomeExistente.equals(nomeBusca)) {
                return s;
            }
        }
        return null;
    }

    // ! Verificar se o nome e marca do produto existe no arquivo
    public Produto buscaProdutoPorNomeEMarca(String nome, String marca) {
        String nomeBusca = normalizaTexto(nome);
        String marcaBusca = normalizaTexto(marca);

        for(Produto p : produtos){
            String nomeExistente = normalizaTexto(p.getNome());
            String marcaExistente = normalizaTexto(p.getMarca());
            
            if(nomeExistente.equals(nomeBusca) && marcaExistente.equals(marcaBusca)) {
                return p;
            }
        }
        return null;
    }

    // ! verifica se o insumo existe no arquivo
    public Insumo buscarInsumoPorNome(String nome) {
        String nomeBusca = normalizaTexto(nome);

        for(Insumo i : insumos) {
            if(normalizaTexto(i.getNome()).equals(nomeBusca));
            return i;
        }
        return null;
    }

    // ! verifica se o promoção existe no arquivo
    public Promocao buscarPromocaoPorNome(String nome) {
        String nomeBusca = normalizaTexto(nome);
        
        for (Promocao p : promocoes) {
            if (normalizaTexto(p.getNome()).equals(nomeBusca)) {
                return p;
            }
        }
        return null;
    }

    // ! Busca o agendamento de um cliente
    public List<Agendamento> buscarAgendamentosPorCliente(Cliente cliente) {
        List<Agendamento> lista = new ArrayList<>();
        for (Agendamento a : agendamentos) {
            if (a.getCliente().getCpf().equals(cliente.getCpf())) {
                lista.add(a);
            }
        }
        return lista;
    }

    public void atualizarStatusAgendamento() {
        gerenciadorArquivos.reescreverAgendamentos(this.agendamentos);
    }

    // --- MÉTODOS PARA SALVAR ALTERAÇÕES GERAIS (UPDATE) ---

    public void atualizarEstoqueProdutos() {
        gerenciadorArquivos.reescreverProdutos(this.produtos);
    }

    public void atualizarPontosClientes() {
        gerenciadorArquivos.reescreverClientes(this.clientes);
    }

    // ? --- MÉTODOS DE REMOÇÃO E EDIÇÃO ---

    // ! Função para remover cliente 
    public boolean removerCliente(String cpf) {
        Cliente clienteParaRemover = buscarClientePorCpf(cpf);
        if (clienteParaRemover != null) {
            clientes.remove(clienteParaRemover); // Remove da RAM
            gerenciadorArquivos.reescreverClientes(this.clientes); // Atualiza o arquivo
            return true;
        }
        return false;
    }

    // ! Função para remover barbeiro 
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
    
    // ! Função para remover agendamento 
    public void removerAgendamento(Agendamento agendamento) {
        agendamentos.remove(agendamento); // Remove da memória
        gerenciadorArquivos.reescreverAgendamentos(this.agendamentos); // Atualiza arquivo
    }

    // ! Função para remover Produto
    public boolean removerProduto(String nome) {
        for (Produto p : produtos) {
            if (normalizaTexto(p.getNome()).equals(normalizaTexto(nome))) {
                produtos.remove(p);
                gerenciadorArquivos.reescreverProdutos(this.produtos);
                return true;
            }
        }
        return false;
    }

    public void salvarAlteracoesProdutos() {
        gerenciadorArquivos.reescreverProdutos(this.produtos);
    }

    // ! Função para remover serviço
    public boolean removerServico(String nome) {
        Servico s = buscarServicoPorNome(nome);
        if (s != null) {
            servicos.remove(s);
            gerenciadorArquivos.reescreverServicos(this.servicos);
            return true;
        }
        return false;
    }

    public void salvarAlteracoesServicos() {
        gerenciadorArquivos.reescreverServicos(this.servicos);
    }

    // ! Função para remover Insumo 
    public boolean removerInsumo(String nome) {
        Insumo i = buscarInsumoPorNome(nome);
        if (i != null) {
            insumos.remove(i);
            gerenciadorArquivos.reescreverInsumos(this.insumos);
            return true;
        }
        return false;
    }

    
    public void salvarAlteracoesInsumos() {
        gerenciadorArquivos.reescreverInsumos(this.insumos);
    }

    // ! Função para remover Promoção
    public boolean removerPromocao(String nome) {
        Promocao p = buscarPromocaoPorNome(nome);
        if (p != null) {
            promocoes.remove(p);
            gerenciadorArquivos.reescreverPromocoes(this.promocoes);
            return true;
        }
        return false;
    }

    public void salvarAlteracoesPromocoes() {
        gerenciadorArquivos.reescreverPromocoes(this.promocoes);
    }

    // ! Função para salvar as alterações no agendamento
    public void salvarAlteracoesAgendamentos() {
        gerenciadorArquivos.reescreverAgendamentos(this.agendamentos);
    }
    

    // ! Função para salvar qualquer alteração feita nos objetos de cliente 
    public void salvarAlteracoesClientes() {
        gerenciadorArquivos.reescreverClientes(this.clientes);
    }

    // ! Método para salvar alterações nos objetos de barbeiro 
    public void salvarAlteracoesBarbeiros() {
        gerenciadorArquivos.reescreverBarbeiros(this.barbeiros);
    }

    // ? Métodos auxiliares

    // ! Normalizar texto retirando espaços, acentos e letras maiúsculas para comparação no arquivo
    private String normalizaTexto(String texto) {
        if(texto == null) {
            return "";
        }
        String nfdNormalizedString = Normalizer.normalize(texto, Normalizer.Form.NFD);

        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String semAcento = pattern.matcher(nfdNormalizedString).replaceAll("");

        return semAcento.toLowerCase().replace(" ", "");
    }

    // ! Função para verificar a diposnibilidade 

    public void verificarDisponibilidade(Barbeiro barbeiro, LocalDateTime inicioNovo, int tempoServico) throws HorarioIndisponivelException {
        LocalDateTime fimNovo = inicioNovo.plusMinutes(tempoServico);

        for (Agendamento agendamento : agendamentos) {
            if (!agendamento.getBarbeiro().getCpf().equals(barbeiro.getCpf())) {
                continue;
            }

            if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
                continue;
            }

            LocalDateTime inicioExistente = agendamento.getDataHora();
            int duracaoExistente = agendamento.getServico().getTempoEstimado();
            LocalDateTime fimExistente = inicioExistente.plusMinutes(duracaoExistente);

            if (inicioNovo.isBefore(fimExistente) && fimNovo.isAfter(inicioExistente)) {
                throw new HorarioIndisponivelException(
                    "Conflito de horário! O barbeiro " + barbeiro.getNome() + 
                    " já tem agendamento das " + inicioExistente.toLocalTime() + 
                    " às " + fimExistente.toLocalTime()
                );
            }
        }
    }


    // --- MÉTODOS CRUD CATEGORIA ---

    public void cadastrarCategoria(Categoria categoria) {
        categorias.add(categoria);
        gerenciadorArquivos.salvarCategoria(categoria);
    }

    public Categoria buscarCategoriaPorNome(String nome) {
        String nomeBusca = normalizaTexto(nome);
        for (Categoria c : categorias) {
            if (normalizaTexto(c.getNome()).equals(nomeBusca)) {
                return c;
            }
        }
        return null;
    }

    public boolean removerCategoria(String nome) {
        Categoria c = buscarCategoriaPorNome(nome);
        if (c != null) {
            categorias.remove(c);
            gerenciadorArquivos.reescreverCategorias(this.categorias);
            return true;
        }
        return false;
    }

    public void salvarAlteracoesCategorias() {
        gerenciadorArquivos.reescreverCategorias(this.categorias);
    }
    
}