package barbershop;

import barbershop.exception.*;
import barbershop.model.*; 
import barbershop.service.GerenciadorDeDados;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static GerenciadorDeDados gerenciador = new GerenciadorDeDados();
    private static Scanner scanner = new Scanner(System.in);
    private static barbershop.service.GerenciadorPreferencias prefs = new barbershop.service.GerenciadorPreferencias();

    public static void main(String[] args) {
        int opcao = -1;

        String nome = prefs.getNomeUsuario();
        String tema = prefs.getTema();

        limparTela();
        System.out.println("=== BEM-VINDO AO BARBERSHOP MANAGER ===");
        System.out.println("Olá, " + nome + "! (Tema atual: " + tema + ")");
        System.out.println("=======================================");

        while(opcao != 0) {
            exibirMenuPrincipal();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    menuCadastros();
                    break;
                case 2:
                    menuAgendamentos();
                    break;
                case 3:
                    menuComanda(); 
                    break;
                case 4:
                    menuRelatorios(); 
                    break;
                case 5:
                    menuManutencao();
                    break;
                case 6:
                    menuConfiguracoes();
                    break;
                case 0:
                    System.out.println("Saindo do sistema... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Cadastros");
        System.out.println("2. Agendamentos");
        System.out.println("3. Vendas e Comandas");
        System.out.println("4. Relatórios");
        System.out.println("5. Manutenção");
        System.out.println("6. Configurações (Preferências)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // ? --- SUB-MENUS ---

    private static void menuCadastros() {
        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            System.out.println("\n--- MENU DE CADASTROS ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Barbeiro");
            System.out.println("3. Cadastrar Serviço");
            System.out.println("4. Cadastrar Produto");
            System.out.println("5. Cadastrar Insumo");
            System.out.println("6. Cadastrar Promoção");
            System.out.println("7. Cadastrar Categoria");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarCliente(); break;
                case 2: cadastrarBarbeiro(); break;
                case 3: cadastrarServico(); break;
                case 4: cadastrarProduto(); break;
                case 5: cadastrarInsumo(); break;
                case 6: cadastrarPromocao(); break;
                case 7: cadastrarCategoria(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuAgendamentos() {
        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            System.out.println("\n--- MENU DE AGENDAMENTOS ---");
            System.out.println("1. Novo Agendamento");
            System.out.println("2. Listar Agendamentos");
            System.out.println("3. Editar Agendamento (Reagendar)");
            System.out.println("4. Excluir Agendamento");
            System.out.println("5. Concluir Agendamento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }

            switch (opcao) {
                case 1: realizandoAgendamento(); break;
                case 2: listarAgendamentos(); break;
                case 3: editarAgendamento(); break;
                case 4: excluirAgendamento(); break;
                case 5: concluirAgendamento(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuComanda() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE VENDAS E COMANDAS ---");
            System.out.println("1. Nova Venda / Abrir Comanda");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }

            switch (opcao) {
                case 1: novaVenda(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuRelatorios() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- RELATÓRIOS E LISTAGENS ---");
            System.out.println("1. Listar Clientes");
            System.out.println("2. Listar Barbeiros");
            System.out.println("3. Listar Serviços");
            System.out.println("4. Listar Produtos (Estoque)");
            System.out.println("5. Listar Insumos");
            System.out.println("6. Listar Promoções");
            System.out.println("7. Listar Categorias");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }

            switch (opcao) {
                case 1: listarClientes(); break;
                case 2: listarBarbeiros(); break;
                case 3: listarServicos(); break;
                case 4: listarProdutos(); break;
                case 5: listarInsumos(); break;
                case 6: listarPromocoes(); break;
                case 7: listarCategorias(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // ? --- MENU: MANUTENÇÃO (EDITAR/REMOVER) ---

    private static void menuManutencao() {
        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            System.out.println("\n--- MANUTENÇÃO DE CADASTROS ---");
            System.out.println("1. Remover Cliente");
            System.out.println("2. Editar Telefone do Cliente");
            System.out.println("3. Remover Barbeiro");
            System.out.println("4. Editar Barbeiro (Comissão/Especialidade)");
            System.out.println("5. Gerenciar Produtos (Editar/Remover)");
            System.out.println("6. Gerenciar Serviços (Editar/Remover)");
            System.out.println("7. Gerenciar Insumos (Editar/Remover)");
            System.out.println("8. Gerenciar Promoções (Editar/Remover)");
            System.out.println("9. Gerenciar Categorias (Editar/Remover)");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }

            switch (opcao) {
                case 1: removerCliente(); break;
                case 2: editarCliente(); break;
                case 3: removerBarbeiro(); break;
                case 4: editarBarbeiro(); break; 
                case 5: gerenciarProdutos(); break; 
                case 6: gerenciarServicos(); break; 
                case 7: gerenciarInsumos(); break; 
                case 8: removerPromocao(); break;
                case 9: gerenciarCategorias(); break; 
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // ? --- Métodos de Cadastro ---

    // ! Função para cadastrar um novo cliente
    private static void cadastrarCliente() {
        System.out.println("\n--- NOVO CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        String cpf = "";
        while(true){
            try {
                System.out.print("CPF (apenas números ou com pontuação): ");
                String entrada = scanner.nextLine();
                cpf = limparNumero(entrada);

                if(cpf.length() != 11){
                    throw new CPFInvalidoException("CPF inválido! Deve conter exatamente 11 dígitos numéricos.");
                }

                if(gerenciador.buscarClientePorCpf(cpf) != null) {
                    throw new CPFInvalidoException("Erro: Este CPF já está cadastrado no sistema.");
                }
                break;
            } catch (CPFInvalidoException e) {
                System.out.println(e.getMessage());
                System.out.println("Tente novamente.\n");
            }

        }


        System.out.print("Telefone (Com DDD): ");
        String telefone = limparNumero(scanner.nextLine());

        Cliente novoCliente = new Cliente(nome, cpf, telefone);
        gerenciador.cadastrarCliente(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    // ! Função para cadastrar um novo barbeiro
    private static void cadastrarBarbeiro() {
        System.out.println("\n--- NOVO BARBEIRO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        String cpf = "";
        while (true) {
            try {
                System.out.print("CPF (digite com ou sem pontos): ");
                String entrada = scanner.nextLine();
                cpf = limparNumero(entrada);
                
                if (cpf.length() != 11) {
                    throw new CPFInvalidoException("CPF inválido! Deve conter exatamente 11 dígitos numéricos.");
                }
                
                if (gerenciador.buscarBarbeiroPorCpf(cpf) != null) {
                    throw new CPFInvalidoException("Erro: Este CPF já está cadastrado no sistema.");
                }
                break;
                
            } catch (CPFInvalidoException e) {
                System.out.println(e.getMessage());
                System.out.println("Tente novamente.\n");
            }
        }

        System.out.print("Telefone (Com DDD): ");
        String telefone = limparNumero(scanner.nextLine());
        System.out.print("Especialidade (ex: Corte, Barba): ");
        String especialidade = scanner.nextLine();
        
        System.out.print("Comissão (ex: 10.0): ");
        double comissao = 0;
        try {
            comissao = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido, definindo comissão como 0.");
        }

        Barbeiro novoBarbeiro = new Barbeiro(nome, cpf, telefone, especialidade, comissao);
        gerenciador.cadastrarBarbeiro(novoBarbeiro);
        System.out.println("Barbeiro cadastrado com sucesso!");
    }

    // ! Função para cadastro de um serviço
    private static void cadastrarServico() {
        System.out.println("\n--- NOVO SERVIÇO ---");
        
        
        System.out.print("Nome do serviço (ex: Corte Masculino): ");
        String nome = "";
        while(true){
            String entrada = scanner.nextLine();

            if(entrada.trim().isEmpty()) {
                System.out.println("Por favor digite o nome do serviço");
                continue;
            }

            if(gerenciador.buscarServicoPorNome(entrada) != null){
                System.out.println("Erro: Já existe um serviço cadastrado com esse nome ou muito similar.");
                System.out.println("Tente outro nome.");
            } else {
                nome = entrada;
                break;
            }
        }
        
        double valor = 0;
        while (true) {
            try {
                System.out.print("Valor (ex: 35.00): ");
                String entrada = scanner.nextLine().replace(",", ".");
                valor = Double.parseDouble(entrada);
                
                if (valor <= 0) {
                    System.out.println("O valor deve ser maior que zero.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite apenas números (ex: 35.50).");
            }
        }

        int tempo = 0;
        while (true) {
            try {
                System.out.print("Tempo estimado em minutos (ex: 30): ");
                tempo = Integer.parseInt(scanner.nextLine());
                
                if (tempo <= 0) {
                    System.out.println("O tempo deve ser maior que zero.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Tempo inválido! Digite apenas números inteiros.");
            }
        }

        Servico novoServico = new Servico(nome, valor, tempo);
        gerenciador.cadastrarServico(novoServico);
        System.out.println("Serviço cadastrado com sucesso!");
    }

    // ! Função para cadastro de um produto
    private static void cadastrarProduto() {
        System.out.println("\n--- NOVO PRODUTO ---");
        String nome = "";
        String marca = "";

        while(true){
            System.out.print("Nome do produto(ex: Pomada Modeladora): ");
            nome = scanner.nextLine();

            if(nome.trim().isEmpty()) {
                System.out.println("O nome não pode ser vazio");
                continue;
            }

            System.out.print("Marca (ex: BarberX): ");
            marca = scanner.nextLine();

            if(marca.trim().isEmpty()) {
                System.out.println("A marca não pode ser vazia.");
                continue;
            }

            if(gerenciador.buscaProdutoPorNomeEMarca(nome, marca) != null) {
                System.out.println("Erro: Já existe um produto '" + nome + "' desta mesma marca (" + marca + ") cadastrado.");
                System.out.println("Tente novamente ou verifique se já foi cadastrado.\n");
            } else {
                break;
            }

        }
        
        double valor = 0;
        while(true){
            try{
                System.out.print("Preço de Venda: ");
                String entrada = scanner.nextLine().replace(",", ".");
                valor = Double.parseDouble(entrada);

                if(valor <= 0){
                    System.out.println("O preço deve ser maior que zero.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite apenas números.");
            }
        }

        int estoque = 0;
        while(true) {
            try {
                System.out.print("Quantidade em Estoque: ");
                estoque = Integer.parseInt(scanner.nextLine());

                if(estoque < 0) {
                    System.out.println("O estoque não pode ser negativo.");
                    continue;
                }
                break;
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida! Digite um número inteiro.");
        }
        }

        Produto novoProduto = new Produto(nome, valor, estoque, marca);
        gerenciador.cadastrarProduto(novoProduto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    // ! Função para cadastro de insumo 
    private static void cadastrarInsumo() {
        System.out.println("\n--- NOVO INSUMO ---");
        
        String nome = "";
        while(true){
            System.out.print("Nome do insumo (ex: Lâmina de Barbear): ");
            String entrada = scanner.nextLine();

            if(entrada.trim().isEmpty()) {
                System.out.println("O nome não pode ser vazio.");
                continue;
            }

            if(gerenciador.buscarInsumoPorNome(entrada) != null){
                System.out.println("Erro: Já existe um insumo cadastrado com este nome.");
                System.out.println("Dica: Se deseja aumentar a quantidade, edite o estoque.");
            } else {
                nome = entrada;
                break;
            }
        }

        double custo = 0;
        while(true){
            try {
                System.out.print("Custo Unitário: ");
                String entrada = scanner.nextLine().replace(",", ".");
                custo = Double.parseDouble(entrada);

                if(custo <= 0){
                    System.out.println("O custo deve ser maior que zero.");
                    continue;
                }
                break;
            }catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite apenas números (ex: 5.50).");
            }

        }

        int estoque = 0;
        while(true){
            try {
                System.out.println("Quantidade em Estoque: ");
                estoque = Integer.parseInt(scanner.nextLine());

                if(estoque < 0){
                    System.out.println("O estoque não pode ser negativo.");
                    continue;
                }
                break;
            } catch(NumberFormatException e) {
                System.out.println("Quantidade inválida! Digite um número inteiro.");
            }
        }

        Insumo novoInsumo = new Insumo(nome, estoque, custo);
        gerenciador.cadastrarInsumo(novoInsumo);
        System.out.println("Insumo cadastrado com sucesso!");
    }

    // ! Função para cadastro de promoção
    private static void cadastrarPromocao() {
        System.out.println("\n--- NOVA PROMOÇÃO ---");

        String nome = "";
        while (true) {
            System.out.print("Nome da promoção (ex: Black Friday): ");
            String entrada = scanner.nextLine();

            if (entrada.trim().isEmpty()) {
                System.out.println("O nome não pode ser vazio.");
                continue;
            }

            if (gerenciador.buscarPromocaoPorNome(entrada) != null) {
                System.out.println("Erro: Já existe uma promoção cadastrada com este nome.");
            } else {
                nome = entrada;
                break;
            }
        }
        

        double desconto = 0;
        while (true) {
            try {
                System.out.print("Porcentagem de desconto (ex: 15 para 15%): ");
                String entrada = scanner.nextLine().replace(",", ".");
                desconto = Double.parseDouble(entrada);
                
                if (desconto <= 0 || desconto > 100) {
                    System.out.println("O desconto deve ser maior que 0% e menor ou igual a 100%.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite apenas números.");
            }
        }

        java.time.LocalDate inicio = null;
        java.time.LocalDate fim = null;
        
        while (true) {
            try {
                System.out.print("Data de Início (AAAA-MM-DD): ");
                inicio = java.time.LocalDate.parse(scanner.nextLine());
                
                System.out.print("Data de Fim (AAAA-MM-DD): ");
                fim = java.time.LocalDate.parse(scanner.nextLine());
                
                if (fim.isBefore(inicio)) {
                    System.out.println("Erro: A data final não pode ser anterior à data inicial.");
                    continue;
                }
                break;
                
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato AAAA-MM-DD (ex: 2025-12-25).");
            }
        }

        Promocao novaPromocao = new Promocao(nome, desconto, inicio, fim);
        gerenciador.cadastrarPromocao(novaPromocao);
        System.out.println("Promoção cadastrada com sucesso!");
    }

    // ? --- Métodos de Agendamento ---

    // ! Função para fazer o agendamento
    private static void realizandoAgendamento() {
        System.out.println("\n--- NOVO AGENDAMENTO ---");

        System.out.print("Digite o CPF do Cliente: ");
        String cpfCliente = limparNumero(scanner.nextLine());
        Cliente cliente = gerenciador.buscarClientePorCpf(cpfCliente);
        
        if (cliente == null) {
            System.out.println("ERRO: Cliente não encontrado.");
            return;
        }
        System.out.println("Cliente selecionado: " + cliente.getNome());

        System.out.print("Digite o CPF do Barbeiro: ");
        String cpfBarbeiro = scanner.nextLine();
        Barbeiro barbeiro = null;
        
        for (Barbeiro b : gerenciador.getBarbeiros()) {
            if (b.getCpf().equals(cpfBarbeiro)) {
                barbeiro = b;
                break;
            }
        }

        if (barbeiro == null) {
            System.out.println("ERRO: Barbeiro não encontrado.");
            return;
        }
        System.out.println("Barbeiro selecionado: " + barbeiro.getNome());

        System.out.print("Digite o Nome do Serviço: ");
        String nomeServico = scanner.nextLine();
        Servico servico = null;

        for (Servico s : gerenciador.getServicos()) {
            if (s.getNome().equalsIgnoreCase(nomeServico)) {
                servico = s;
                break;
            }
        }

        if (servico == null) {
            System.out.println("ERRO: Serviço não encontrado.");
            return;
        }

        System.out.print("Data e Hora (AAAA-MM-DDTHH:MM) ex: 2025-12-10T14:30 : ");
        String dataStr = scanner.nextLine();
        
        try {
            java.time.LocalDateTime dataHora = java.time.LocalDateTime.parse(dataStr);
            
            gerenciador.verificarDisponibilidade(barbeiro, dataHora, servico.getTempoEstimado());
            
            Agendamento agendamento = new Agendamento(cliente, barbeiro, servico, dataHora);
            gerenciador.agendar(agendamento);
            System.out.println("Agendamento realizado com sucesso!");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Formato de data inválido! Use o formato: 2025-12-10T14:30");
        } catch (HorarioIndisponivelException e) {
            System.out.println("ERRO DE AGENDAMENTO: " + e.getMessage());
        }
    }

    // ! Função para listar todos os agendamentos
    private static void listarAgendamentos() {
        System.out.println("\n--- LISTA DE AGENDAMENTOS ---");
        if (gerenciador.getAgendamentos().isEmpty()) {
            System.out.println("Nenhum agendamento cadastrado.");
        } else {
            for (Agendamento a : gerenciador.getAgendamentos()) {
                System.out.println("Data: " + a.getDataHora() + 
                                   " | Cliente: " + a.getCliente().getNome() + 
                                   " | Barbeiro: " + a.getBarbeiro().getNome() +
                                   " | Serviço: " + a.getServico().getNome());
            }
        }
    }

    // ! Função para concluir um agendamento
    private static void concluirAgendamento() {
        System.out.println("\n--- CONCLUIR AGENDAMENTO ---");
        System.out.print("Digite o CPF do Cliente: ");
        String cpf = limparNumero(scanner.nextLine());
        Cliente cliente = gerenciador.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        List<Agendamento> agendamentosCliente = gerenciador.buscarAgendamentosPorCliente(cliente);
        
        if (agendamentosCliente.isEmpty()) {
            System.out.println("Este cliente não possui agendamentos.");
            return;
        }

        System.out.println("Agendamentos encontrados:");
        for (int i = 0; i < agendamentosCliente.size(); i++) {
            Agendamento a = agendamentosCliente.get(i);
            System.out.println((i + 1) + ". " + a.getServico().getNome() + " - " + 
                               a.getDataHora().toString() + " [" + a.getStatus() + "]");
        }

        System.out.print("Digite o número do agendamento para concluir (0 para cancelar): ");
        try {
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= agendamentosCliente.size()) {
                Agendamento selecionado = agendamentosCliente.get(escolha - 1);
                
                selecionado.setStatus(StatusAgendamento.CONCLUIDO);
                
                gerenciador.atualizarStatusAgendamento();
                System.out.println("Agendamento marcado como CONCLUÍDO!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida.");
        }
    }

    // ! Função para exluir um agendamento 
    private static void excluirAgendamento() {
        System.out.println("\n--- EXCLUIR AGENDAMENTO ---");
        Agendamento agendamento = selecionarAgendamentoDoCliente(); // Usa método auxiliar abaixo
        
        if (agendamento != null) {
            System.out.print("Tem certeza que deseja excluir este agendamento? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                gerenciador.removerAgendamento(agendamento);
                System.out.println("Agendamento removido com sucesso!");
            }
        }
    }

    // ! Função para editar um agendamento
    private static void editarAgendamento() {
        System.out.println("\n--- REAGENDAR (EDITAR) ---");
        Agendamento agendamento = selecionarAgendamentoDoCliente();
        
        if (agendamento != null) {
            System.out.println("Data atual: " + agendamento.getDataHora());
            System.out.print("Nova Data e Hora (AAAA-MM-DDTHH:MM): ");
            String dataStr = scanner.nextLine();
            
            try {
                java.time.LocalDateTime novaData = java.time.LocalDateTime.parse(dataStr);
                
                try {
                    gerenciador.verificarDisponibilidade(agendamento.getBarbeiro(), novaData, agendamento.getServico().getTempoEstimado());
                    
                    agendamento.setDataHora(novaData);
                    gerenciador.salvarAlteracoesAgendamentos();
                    System.out.println("Agendamento reagendado com sucesso!");
                    
                } catch (HorarioIndisponivelException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Formato de data inválido!");
            }
        }
    }

    // ? --- Métodos de Venda ---

    private static void novaVenda() {
        System.out.println("\n--- ABRINDO NOVA COMANDA ---");

        System.out.print("CPF do Cliente: ");
        String cpf = limparNumero(scanner.nextLine());
        Cliente cliente = gerenciador.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        System.out.println("Comanda aberta para: " + cliente.getNome());

        Comanda comanda = new Comanda(cliente);
        boolean comandaAberta = true;

        while (comandaAberta) {
            System.out.println("\n-- Adicionar Itens --");
            System.out.println("1. Adicionar Serviço");
            System.out.println("2. Adicionar Produto");
            System.out.println("3. Fechar Conta (Pagamento)");
            System.out.print("Escolha: ");

            int opcaoItem = -1;
            try {
                opcaoItem = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }

            switch (opcaoItem) {
                case 1: 
                    System.out.print("Nome do Serviço: ");
                    String nomeServico = scanner.nextLine();
                    Servico servicoEncontrado = null;
                    for (Servico s : gerenciador.getServicos()) {
                        if (s.getNome().equalsIgnoreCase(nomeServico)) {
                            servicoEncontrado = s;
                            break;
                        }
                    }
                    if (servicoEncontrado != null) {
                        comanda.adicionarItem(servicoEncontrado);
                        System.out.println("Serviço adicionado: " + servicoEncontrado.getNome());
                    } else {
                        System.out.println("Serviço não encontrado.");
                    }
                    break;

                case 2:
                    System.out.print("Nome do Produto: ");
                    String nomeProduto = scanner.nextLine();
                    Produto produtoEncontrado = null;
                    for (Produto p : gerenciador.getProdutos()) {
                        if (p.getNome().equalsIgnoreCase(nomeProduto)) {
                            produtoEncontrado = p;
                            break;
                        }
                    }
                    
                    if (produtoEncontrado != null) {
                        if (produtoEncontrado.getQtdEstoque() > 0) {
                            comanda.adicionarItem(produtoEncontrado);
                            
                            produtoEncontrado.setQtdEstoque(produtoEncontrado.getQtdEstoque() - 1);
                            
                            System.out.println("Produto adicionado: " + produtoEncontrado.getNome());
                        } else {
                            System.out.println("ERRO: Produto sem estoque!");
                        }
                    }
                    break;

                case 3:
                    fecharConta(comanda);
                    comandaAberta = false;
                    break;
                
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void fecharConta(Comanda comanda) {
        System.out.println("\n--- FECHAMENTO DE CONTA ---");
        System.out.println("Cliente: " + comanda.getCliente().getNome());
        System.out.println("Itens consumidos:");
        for (barbershop.model.abstratas.ItemComercializavel item : comanda.getItens()) {
            System.out.println("- " + item.getNome() + ": R$ " + String.format("%.2f", item.getPreco()));
        }

        double total = comanda.calcularValorTotal();
        System.out.println("Subtotal: R$ " + String.format("%.2f", total));

        System.out.print("Aplicar promoção? Digite o nome ou ENTER para pular: ");
        String nomePromo = scanner.nextLine();
        if (!nomePromo.isEmpty()) {
            for (Promocao p : gerenciador.getPromocoes()) {
                if (p.getNome().equalsIgnoreCase(nomePromo) && p.estaAtiva()) {
                    total = comanda.calcularValorComDesconto(p);
                    System.out.println("Promoção '" + p.getNome() + "' aplicada!");
                    break;
                }
            }
        }

        System.out.println("TOTAL FINAL: R$ " + String.format("%.2f", total));
        
        System.out.print("Confirmar pagamento? (S/N): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.equalsIgnoreCase("S")) {
            comanda.setStatusPagamento(StatusPagamento.PAGO);
            
            int pontosGanhos = (int) total / 10;
            if (pontosGanhos > 0) {
                comanda.getCliente().adicionarPontos(pontosGanhos);
                System.out.println("Cliente ganhou " + pontosGanhos + " pontos de fidelidade!");
            }

            System.out.println("Atualizando banco de dados...");
            gerenciador.atualizarEstoqueProdutos(); 
            gerenciador.atualizarPontosClientes(); 
            
            System.out.println("Venda concluída com sucesso!");
        } else {
            System.out.println("Venda cancelada.");
        }
    }

    // ? --- Métodos de Listagem ---

    private static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        if (gerenciador.getClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : gerenciador.getClientes()) {
                System.out.println("Nome: " + c.getNome() + " | CPF: " + c.getCpf() + " | Pontos: " + c.getPontosFidelidade());
            }
        }
    }

    private static void listarBarbeiros() {
        System.out.println("\n--- LISTA DE BARBEIROS ---");
        if (gerenciador.getBarbeiros().isEmpty()) {
            System.out.println("Nenhum barbeiro cadastrado.");
        } else {
            for (Barbeiro b : gerenciador.getBarbeiros()) {
                System.out.println("Nome: " + b.getNome() + " | Esp: " + b.getEspecialidade() + " | Comissão: " + b.getComissao() + "%");
            }
        }
    }

    private static void listarServicos() {
        System.out.println("\n--- LISTA DE SERVIÇOS ---");
        if (gerenciador.getServicos().isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
        } else {
            for (Servico s : gerenciador.getServicos()) {
                System.out.println("Nome: " + s.getNome() + " | Valor: R$ " + s.getPreco() + " | Tempo: " + s.getTempoEstimado() + "min");
            }
        }
    }

    private static void listarProdutos() {
        System.out.println("\n--- ESTOQUE DE PRODUTOS ---");
        if (gerenciador.getProdutos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : gerenciador.getProdutos()) {
                System.out.println("Produto: " + p.getNome() + " | Marca: " + p.getMarca() + " | Preço: R$ " + p.getPreco() + " | Qtd: " + p.getQtdEstoque());
            }
        }
    }

    private static void listarInsumos() {
        System.out.println("\n--- ESTOQUE DE INSUMOS ---");
        if (gerenciador.getInsumos().isEmpty()) {
            System.out.println("Nenhum insumo cadastrado.");
        } else {
            for (Insumo i : gerenciador.getInsumos()) {
                System.out.println("Item: " + i.getNome() + " | Custo: R$ " + i.getCustoUnitario() + " | Qtd: " + i.getQtdEstoque());
            }
        }
    }

    private static void listarPromocoes() {
        System.out.println("\n--- PROMOÇÕES ATIVAS E INATIVAS ---");
        if (gerenciador.getPromocoes().isEmpty()) {
            System.out.println("Nenhuma promoção cadastrada.");
        } else {
            for (Promocao p : gerenciador.getPromocoes()) {
                String status = p.estaAtiva() ? "[ATIVA]" : "[INATIVA]";
                System.out.println(status + " " + p.getNome() + " | Desconto: " + p.getDescontoPercentual() + "% | Fim: " + p.getDataFim());
            }
        }
    }

    // ? --- MÉTODOS DE MANUTENÇÃO (EDITAR/REMOVER) ---

    private static void removerCliente() {
        System.out.print("Digite o CPF do cliente a remover: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Tem certeza? Isso apagará o cliente permanentemente (S/N): ");
        if (!scanner.nextLine().equalsIgnoreCase("S")) return;

        boolean sucesso = gerenciador.removerCliente(cpf);
        if (sucesso) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void removerBarbeiro() {
        System.out.print("Digite o CPF do barbeiro a remover: ");
        String cpf = scanner.nextLine();
        
        boolean sucesso = gerenciador.removerBarbeiro(cpf);
        if (sucesso) {
            System.out.println("Barbeiro removido com sucesso!");
        } else {
            System.out.println("Barbeiro não encontrado.");
        }
    }

    private static void editarCliente() {
        System.out.print("Digite o CPF do cliente para editar: ");
        String cpf = scanner.nextLine();
        Cliente cliente = gerenciador.buscarClientePorCpf(cpf);

        if (cliente != null) {
            System.out.println("Cliente encontrado: " + cliente.getNome());
            System.out.println("Telefone atual: " + cliente.getTelefone());
            
            System.out.print("Novo Telefone: ");
            String novoTel = scanner.nextLine();
            
            cliente.setTelefone(novoTel);
            
            gerenciador.salvarAlteracoesClientes();
            System.out.println("Telefone atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void menuConfiguracoes() {
        System.out.println("\n--- CONFIGURAÇÕES ---");
        System.out.println("Nome atual: " + prefs.getNomeUsuario());
        System.out.println("Tema atual: " + prefs.getTema());
        
        System.out.println("1. Alterar Nome de Exibição");
        System.out.println("2. Alternar Tema (Claro/Escuro)");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        
        try {
            int op = Integer.parseInt(scanner.nextLine());
            switch (op) {
                case 1:
                    System.out.print("Digite o novo nome: ");
                    String novoNome = scanner.nextLine();
                    prefs.setNomeUsuario(novoNome);
                    System.out.println("Nome salvo!");
                    break;
                case 2:
                    String novoTema = prefs.getTema().equals("CLARO") ? "ESCURO" : "CLARO";
                    prefs.setTema(novoTema);
                    System.out.println("Tema alterado para " + novoTema + "!");
                    break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida.");
        }
    }


    // ? Funções auxiliares

    private static String limparNumero(String dado) {
        if (dado == null) return "";
        return dado.replaceAll("\\D", "");
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Método auxiliar para facilitar a escolha de um agendamento
    private static Agendamento selecionarAgendamentoDoCliente() {
        System.out.print("Digite o CPF do Cliente: ");
        String cpf = limparNumero(scanner.nextLine());
        Cliente cliente = gerenciador.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return null;
        }

        List<Agendamento> lista = gerenciador.buscarAgendamentosPorCliente(cliente);
        if (lista.isEmpty()) {
            System.out.println("Este cliente não possui agendamentos.");
            return null;
        }

        System.out.println("--- Agendamentos de " + cliente.getNome() + " ---");
        for (int i = 0; i < lista.size(); i++) {
            Agendamento a = lista.get(i);
            System.out.println((i + 1) + ". " + a.getServico().getNome() + 
                               " com " + a.getBarbeiro().getNome() + 
                               " em " + a.getDataHora().toString().replace("T", " ") +
                               " [" + a.getStatus() + "]");
        }

        System.out.print("Escolha o número do agendamento (0 para cancelar): ");
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            if (opcao > 0 && opcao <= lista.size()) {
                return lista.get(opcao - 1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida.");
        }
        return null;
    }

    // --- MÉTODOS DE MANUTENÇÃO ADICIONAIS ---

    private static void editarBarbeiro() {
        System.out.print("Digite o CPF do barbeiro para editar: ");
        String cpf = scanner.nextLine();
        Barbeiro b = gerenciador.buscarBarbeiroPorCpf(cpf); 

        if (b != null) {
            System.out.println("Barbeiro: " + b.getNome() + " | Especialidade: " + b.getEspecialidade() + " | Comissão: " + b.getComissao());
            System.out.println("1. Editar Especialidade");
            System.out.println("2. Editar Comissão");
            System.out.print("Opção: ");
            String op = scanner.nextLine();

            if (op.equals("1")) {
                System.out.print("Nova Especialidade: ");
                b.setEspecialidade(scanner.nextLine());
            } else if (op.equals("2")) {
                System.out.print("Nova Comissão: ");
                try {
                    double novaComissao = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    b.setComissao(novaComissao);
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido.");
                    return;
                }
            }
            gerenciador.salvarAlteracoesBarbeiros();
            System.out.println("Barbeiro atualizado com sucesso!");
        } else {
            System.out.println("Barbeiro não encontrado.");
        }
    }

    private static void gerenciarProdutos() {
        System.out.println("\n--- GERENCIAR PRODUTOS ---");
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        // Busca simples pelo nome
        Produto p = null;
        for(Produto prod : gerenciador.getProdutos()){
             if(prod.getNome().equalsIgnoreCase(nome)) { p = prod; break; }
        }

        if (p != null) {
            System.out.println("Produto: " + p.getNome() + " | Estoque: " + p.getQtdEstoque() + " | Preço: R$" + p.getPreco());
            System.out.println("1. Editar Estoque");
            System.out.println("2. Editar Preço");
            System.out.println("3. Remover Produto");
            System.out.print("Escolha: ");
            String op = scanner.nextLine();

            if (op.equals("1")) {
                System.out.print("Novo Estoque: ");
                try {
                    p.setQtdEstoque(Integer.parseInt(scanner.nextLine()));
                    gerenciador.salvarAlteracoesProdutos();
                    System.out.println("Estoque atualizado!");
                } catch (Exception e) { System.out.println("Erro: número inválido."); }
            } else if (op.equals("2")) {
                System.out.print("Novo Preço: ");
                try {
                    p.setValorBase(Double.parseDouble(scanner.nextLine().replace(",", ".")));
                    gerenciador.salvarAlteracoesProdutos();
                    System.out.println("Preço atualizado!");
                } catch (Exception e) { System.out.println("Erro: valor inválido."); }
            } else if (op.equals("3")) {
                if (gerenciador.removerProduto(nome)) {
                    System.out.println("Produto removido!");
                }
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void gerenciarServicos() {
        System.out.print("Nome do serviço: ");
        String nome = scanner.nextLine();
        Servico s = gerenciador.buscarServicoPorNome(nome);

        if (s != null) {
            System.out.println("Serviço: " + s.getNome() + " | Valor: " + s.getPreco() + " | Tempo: " + s.getTempoEstimado() + "min");
            System.out.println("1. Editar Valor");
            System.out.println("2. Editar Tempo Estimado");
            System.out.println("3. Remover este serviço");
            System.out.print("Escolha: ");
            String op = scanner.nextLine();

            if (op.equals("1")) {
                System.out.print("Novo Valor: ");
                try {
                    s.setValorBase(Double.parseDouble(scanner.nextLine().replace(",", ".")));
                    gerenciador.salvarAlteracoesServicos();
                    System.out.println("Valor atualizado!");
                } catch (Exception e) { System.out.println("Erro de número."); }
            } else if (op.equals("2")) {
                System.out.print("Novo Tempo (min): ");
                try {
                    s.setTempoEstimado(Integer.parseInt(scanner.nextLine()));
                    gerenciador.salvarAlteracoesServicos();
                    System.out.println("Tempo atualizado!");
                } catch (Exception e) { System.out.println("Erro de número."); }
            } else if (op.equals("3")) {
                if (gerenciador.removerServico(nome)) {
                    System.out.println("Serviço removido com sucesso.");
                }
            }
        } else {
            System.out.println("Serviço não encontrado.");
        }
    }

   private static void gerenciarInsumos() {
        System.out.print("Nome do insumo: ");
        String nome = scanner.nextLine();
        Insumo i = gerenciador.buscarInsumoPorNome(nome);

        if (i != null) {
            System.out.println("Insumo: " + i.getNome() + " | Qtd: " + i.getQtdEstoque());
            System.out.println("1. Editar Estoque");
            System.out.println("2. Editar Custo");
            System.out.println("3. Remover Insumo");
            System.out.print("Opção: ");
            String op = scanner.nextLine();
            
            if (op.equals("1")) {
                System.out.print("Nova quantidade total: ");
                try {
                     i.setQtdEstoque(Integer.parseInt(scanner.nextLine()));
                     gerenciador.salvarAlteracoesInsumos();
                     System.out.println("Estoque atualizado!");
                } catch (Exception e) { System.out.println("Valor inválido."); }
            } else if (op.equals("2")) {
                System.out.print("Novo Custo: ");
                try {
                     i.setCustoUnitario(Double.parseDouble(scanner.nextLine().replace(",", ".")));
                     gerenciador.salvarAlteracoesInsumos();
                     System.out.println("Custo atualizado!");
                } catch (Exception e) { System.out.println("Valor inválido."); }
            } else if (op.equals("3")) {
                 if (gerenciador.removerInsumo(nome)) {
                    System.out.println("Insumo removido.");
                 }
            }
        } else {
            System.out.println("Insumo não encontrado.");
        }
    }
    
    private static void removerPromocao() { 
        // Vamos renomear a chamada no switch case para este método se tornar "gerenciarPromocoes"
        // Mas se quiser manter o nome da função no switch, apenas substitua o conteúdo deste método:
        
        System.out.println("\n--- GERENCIAR PROMOÇÕES ---");
        System.out.print("Digite o nome da promoção: ");
        String nome = scanner.nextLine();
        Promocao p = gerenciador.buscarPromocaoPorNome(nome);

        if (p != null) {
            System.out.println("Promoção: " + p.getNome() + " | Desconto: " + p.getDescontoPercentual() + "%");
            System.out.println("1. Editar Desconto");
            System.out.println("2. Remover Promoção");
            System.out.print("Escolha: ");
            String op = scanner.nextLine();

            if (op.equals("1")) {
                System.out.print("Novo Desconto (%): ");
                try {
                    double novoDesconto = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    if (novoDesconto > 0 && novoDesconto <= 100) {
                        p.setDescontoPercentual(novoDesconto);
                        gerenciador.salvarAlteracoesPromocoes();
                        System.out.println("Desconto atualizado com sucesso!");
                    } else {
                        System.out.println("Valor deve ser entre 1 e 100.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido.");
                }
            } else if (op.equals("2")) {
                if (gerenciador.removerPromocao(nome)) {
                    System.out.println("Promoção removida!");
                }
            }
        } else {
            System.out.println("Promoção não encontrada.");
        }
    }

    // --- CRUD CATEGORIA ---

private static void cadastrarCategoria() {
    System.out.println("\n--- NOVA CATEGORIA ---");
    System.out.print("Nome da Categoria (ex: Cabelo): ");
    String nome = scanner.nextLine();

    if (gerenciador.buscarCategoriaPorNome(nome) != null) {
        System.out.println("Erro: Categoria já existe.");
        return;
    }

    System.out.print("Descrição (ex: Cortes e tratamentos): ");
    String descricao = scanner.nextLine();

    Categoria nova = new Categoria(nome, descricao);
    gerenciador.cadastrarCategoria(nova);
    System.out.println("Categoria cadastrada com sucesso!");
}

private static void listarCategorias() {
    System.out.println("\n--- LISTA DE CATEGORIAS ---");
    if (gerenciador.getCategorias().isEmpty()) {
        System.out.println("Nenhuma categoria cadastrada.");
    } else {
        for (Categoria c : gerenciador.getCategorias()) {
            System.out.println("Categoria: " + c.getNome() + " | Descrição: " + c.getDescricao());
        }
    }
}

private static void gerenciarCategorias() {
    System.out.print("Digite o nome da categoria para editar/remover: ");
    String nome = scanner.nextLine();
    Categoria c = gerenciador.buscarCategoriaPorNome(nome);

    if (c != null) {
        System.out.println("Categoria encontrada: " + c.getNome());
        System.out.println("1. Editar Nome");
        System.out.println("2. Editar Descrição");
        System.out.println("3. Remover Categoria");
        System.out.print("Opção: ");
        String op = scanner.nextLine();

        if (op.equals("1")) {
            System.out.print("Novo Nome: ");
            c.setNome(scanner.nextLine());
            gerenciador.salvarAlteracoesCategorias();
            System.out.println("Nome atualizado!");
        } else if (op.equals("2")) {
            System.out.print("Nova Descrição: ");
            c.setDescricao(scanner.nextLine());
            gerenciador.salvarAlteracoesCategorias();
            System.out.println("Descrição atualizada!");
        } else if (op.equals("3")) {
            if (gerenciador.removerCategoria(nome)) {
                System.out.println("Categoria removida com sucesso.");
            }
        }
    } else {
        System.out.println("Categoria não encontrada.");
    }
}

}