classDiagram
    %% --- Pacote: barbershop.model.abstratas ---
    class Pessoa {
        <<abstract>>
        -String nome
        -String cpf
        -String telefone
        +Pessoa(String nome, String cpf, String telefone)
        +exibirDados()* void
        +getNome() String
        +setNome(String nome) void
        +getCpf() String
        +getTelefone() String
        +setTelefone(String telefone) void
    }

    class ItemComercializavel {
        <<abstract>>
        #String nome
        #double valorBase
        +ItemComercializavel(String nome, double valorBase)
        +getPreco()* double
        +getNome() String
        +getValorBase() double
        +setValorBase(double valorBase) void
    }

    %% --- Pacote: barbershop.model.interfaces ---
    class Agendavel {
        <<interface>>
        +verificarDisponibilidade(LocalDateTime dataHora) boolean
        +agendar(LocalDateTime dataHora) void
    }

    class Fidelizavel {
        <<interface>>
        +adicionarPontos(int pontos) void
        +resgatarPremios() void
        +getPontosFidelidade() int
    }

    %% --- Pacote: barbershop.model ---
    class Cliente {
        -int pontosFidelidade
        +Cliente(String nome, String cpf, String telefone)
        +getPontosFidelidade() int
        +adicionarPontos(int pontos) void
        +resgatarPremios() void
        +exibirDados() void
    }

    class Barbeiro {
        -String especialidade
        -double comissao
        +Barbeiro(String nome, String cpf, String telefone, String esp, double comissao)
        +exibirDados() void
        +getEspecialidade() String
        +setEspecialidade(String) void
        +getComissao() double
        +setComissao(double) void
    }

    class Servico {
        -int tempoEstimado
        +Servico(String nome, double valorBase, int tempoEstimado)
        +verificarDisponibilidade(LocalDateTime dataHora) boolean
        +agendar(LocalDateTime dataHora) void
        +getPreco() double
        +getTempoEstimado() int
        +setTempoEstimado(int) void
    }

    class Produto {
        -int qtdEstoque
        -String marca
        +Produto(String nome, double valorBase, int qtdEstoque, String marca)
        +getPreco() double
        +getQtdEstoque() int
        +setQtdEstoque(int) void
        +getMarca() String
    }

    class Insumo {
        -String nome
        -int qtdEstoque
        -double custoUnitario
        +Insumo(String nome, int qtdEstoque, double custo)
        +consumir(int quantidade) void
        +reporEstoque(int quantidade) void
        +getNome() String
        +getQtdEstoque() int
        +getCustoUnitario() double
    }

    class Promocao {
        -String nome
        -double descontoPercentual
        -LocalDate dataInicio
        -LocalDate dataFim
        +Promocao(String nome, double desconto, LocalDate inicio, LocalDate fim)
        +estaAtiva() boolean
        +getNome() String
        +getDescontoPercentual() double
        +setDescontoPercentual(double) void
    }
    
    class Categoria {
        -String nome
        -String descricao
        +Categoria(String nome, String descricao)
        +getNome() String
        +setNome(String) void
        +getDescricao() String
        +setDescricao(String) void
        +toString() String
    }

    class Agendamento {
        -Cliente cliente
        -Barbeiro barbeiro
        -Servico servico
        -LocalDateTime dataHora
        -StatusAgendamento status
        +Agendamento(Cliente c, Barbeiro b, Servico s, LocalDateTime data)
        +getStatus() StatusAgendamento
        +setStatus(StatusAgendamento) void
        +getDataHora() LocalDateTime
        +setDataHora(LocalDateTime) void
    }

    class Comanda {
        -Cliente cliente
        -List~ItemComercializavel~ itens
        -StatusPagamento statusPagamento
        +Comanda(Cliente cliente)
        +adicionarItem(ItemComercializavel item) void
        +removerItem(ItemComercializavel item) void
        +calcularValorTotal() double
        +calcularValorComDesconto(Promocao promocao) double
        +setStatusPagamento(StatusPagamento) void
    }

    class StatusAgendamento {
        <<enumeration>>
        PENDENTE
        CONCLUIDO
        CANCELADO
    }

    class StatusPagamento {
        <<enumeration>>
        PENDENTE
        PAGO
    }

    %% --- Pacote: barbershop.service ---
    class GerenciadorArquivos {
        -String DIRETORIO_DADOS
        -String ARQUIVO_CLIENTES
        -String ARQUIVO_BARBEIROS
        -String ARQUIVO_SERVICOS
        -String ARQUIVO_PRODUTOS
        -String ARQUIVO_INSUMOS
        -String ARQUIVO_PROMOCOES
        -String ARQUIVO_AGENDAMENTOS
        -String ARQUIVO_CATEGORIAS
        +GerenciadorArquivos()
        +verificarDiretorio() void
        +salvarCliente(Cliente) void
        +carregarClientes() List~Cliente~
        +salvarBarbeiro(Barbeiro) void
        +carregarBarbeiros() List~Barbeiro~
        +salvarServico(Servico) void
        +carregaServicos() List~Servico~
        +salvarProdutos(Produto) void
        +carregaProdutos() List~Produto~
        +salvarAgendamento(Agendamento) void
        +carregarAgendamentos(...) List~Agendamento~
        +reescreverClientes(List) void
        +reescreverBarbeiros(List) void
        +reescreverAgendamentos(List) void
        +reescreverProdutos(List) void
        %% (Outros métodos de reescrita omitidos para brevidade)
    }

    class GerenciadorDeDados {
        -GerenciadorArquivos gerenciadorArquivos
        -List~Cliente~ clientes
        -List~Barbeiro~ barbeiros
        -List~Servico~ servicos
        -List~Produto~ produtos
        -List~Insumo~ insumos
        -List~Promocao~ promocoes
        -List~Agendamento~ agendamentos
        -List~Categoria~ categorias
        +GerenciadorDeDados()
        -carregarDadosIniciais() void
        +cadastrarCliente(Cliente) void
        +buscarClientePorCpf(String) Cliente
        +cadastrarBarbeiro(Barbeiro) void
        +buscarBarbeiroPorCpf(String) Barbeiro
        +agendar(Agendamento) void
        +verificarDisponibilidade(Barbeiro, LocalDateTime, int) void
        +removerCliente(String) boolean
        +removerBarbeiro(String) boolean
        +removerAgendamento(Agendamento) void
        +atualizarEstoqueProdutos() void
        +atualizarPontosClientes() void
        %% (Outros métodos CRUD omitidos para brevidade)
    }

    class GerenciadorPreferencias {
        -String ARQUIVO_CONFIG
        -Properties props
        +GerenciadorPreferencias()
        -carregarPreferencias() void
        +salvarPreferencias() void
        +getNomeUsuario() String
        +setNomeUsuario(String) void
        +getTema() String
        +setTema(String) void
    }

    %% --- Pacote: barbershop.exception ---
    class CPFInvalidoException {
        +CPFInvalidoException(String message)
    }
    class EstoqueInsuficienteException {
        +EstoqueInsuficienteException(String message)
    }
    class HorarioIndisponivelException {
        +HorarioIndisponivelException(String message)
    }
    class PontosInsuficientesException {
        +PontosInsuficientesException(String message)
    }
    class Exception {
        <<Java Built-in>>
    }
    class RuntimeException {
        <<Java Built-in>>
    }

    %% --- Main ---
    class Main {
        -GerenciadorDeDados gerenciador
        -Scanner scanner
        -GerenciadorPreferencias prefs
        +main(String[] args) void
        -exibirMenuPrincipal() void
        -menuCadastros() void
        -menuAgendamentos() void
        -menuComanda() void
        -menuRelatorios() void
        -menuManutencao() void
        -menuConfiguracoes() void
    }

    %% --- Relações de Herança e Implementação ---
    Cliente --|> Pessoa
    Barbeiro --|> Pessoa
    Cliente ..|> Fidelizavel
    
    Servico --|> ItemComercializavel
    Produto --|> ItemComercializavel
    Servico ..|> Agendavel

    %% --- Relações de Associação e Dependência ---
    Agendamento --> Cliente
    Agendamento --> Barbeiro
    Agendamento --> Servico
    Agendamento --> StatusAgendamento

    Comanda --> Cliente
    Comanda --> ItemComercializavel : contem lista de
    Comanda --> StatusPagamento
    Comanda ..> Promocao : usa

    GerenciadorDeDados --> GerenciadorArquivos : usa
    GerenciadorDeDados o-- Cliente : gere lista
    GerenciadorDeDados o-- Barbeiro : gere lista
    GerenciadorDeDados o-- Servico : gere lista
    GerenciadorDeDados o-- Produto : gere lista
    GerenciadorDeDados o-- Insumo : gere lista
    GerenciadorDeDados o-- Promocao : gere lista
    GerenciadorDeDados o-- Agendamento : gere lista
    GerenciadorDeDados o-- Categoria : gere lista

    Main --> GerenciadorDeDados
    Main --> GerenciadorPreferencias

    %% --- Relações de Exceção ---
    CPFInvalidoException --|> Exception
    EstoqueInsuficienteException --|> Exception
    HorarioIndisponivelException --|> Exception
    PontosInsuficientesException --|> RuntimeException

    Insumo ..> EstoqueInsuficienteException : lança
    Cliente ..> PontosInsuficientesException : lança
    Main ..> CPFInvalidoException : trata
    GerenciadorDeDados ..> HorarioIndisponivelException : lança