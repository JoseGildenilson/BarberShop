package barbershop;

import barbershop.model.*; 
import barbershop.service.GerenciadorDeDados;
import java.util.Scanner;

public class Main {
    private static GerenciadorDeDados gerenciador = new GerenciadorDeDados();
    private static Scanner scanner = new Scanner(System.in);
    private static barbershop.service.GerenciadorPreferencias prefs = new barbershop.service.GerenciadorPreferencias();

    public static void main(String[] args) {
        int opcao = -1;

        String nome = prefs.getNomeUsuario();
        String tema = prefs.getTema();
        
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
                    menuManutencao(); // NOVO MENU!
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
        System.out.println("6. Configurações (Preferências)"); // NOVO
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // --- SUB-MENUS ---

    private static void menuCadastros() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE CADASTROS ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Barbeiro");
            System.out.println("3. Cadastrar Serviço");
            System.out.println("4. Cadastrar Produto");
            System.out.println("5. Cadastrar Insumo");
            System.out.println("6. Cadastrar Promoção");
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
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuAgendamentos() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE AGENDAMENTOS ---");
            System.out.println("1. Novo Agendamento");
            System.out.println("2. Listar Agendamentos");
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
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // --- NOVO MENU: MANUTENÇÃO (EDITAR/REMOVER) ---
    private static void menuManutencao() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MANUTENÇÃO DE CADASTROS ---");
            System.out.println("1. Remover Cliente");
            System.out.println("2. Editar Telefone do Cliente");
            System.out.println("3. Remover Barbeiro");
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
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    // --- Métodos de Cadastro ---

    private static void cadastrarCliente() {
        System.out.println("\n--- NOVO CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente novoCliente = new Cliente(nome, cpf, telefone);
        gerenciador.cadastrarCliente(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void cadastrarBarbeiro() {
        System.out.println("\n--- NOVO BARBEIRO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
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

    private static void cadastrarServico() {
        System.out.println("\n--- NOVO SERVIÇO ---");
        System.out.print("Nome do serviço (ex: Corte Masculino): ");
        String nome = scanner.nextLine();
        
        double valor = 0;
        try {
            System.out.print("Valor (ex: 35.00): ");
            valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
        }

        int tempo = 0;
        try {
            System.out.print("Tempo estimado em minutos (ex: 30): ");
            tempo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Tempo inválido.");
        }

        Servico novoServico = new Servico(nome, valor, tempo);
        gerenciador.cadastrarServico(novoServico);
        System.out.println("Serviço cadastrado com sucesso!");
    }

    private static void cadastrarProduto() {
        System.out.println("\n--- NOVO PRODUTO ---");
        System.out.print("Nome do produto (ex: Pomada Modeladora): ");
        String nome = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        
        double valor = 0;
        try {
            System.out.print("Preço de Venda: ");
            valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
        }

        int estoque = 0;
        try {
            System.out.print("Quantidade em Estoque: ");
            estoque = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida.");
        }

        Produto novoProduto = new Produto(nome, valor, estoque, marca);
        gerenciador.cadastrarProduto(novoProduto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void cadastrarInsumo() {
        System.out.println("\n--- NOVO INSUMO ---");
        System.out.print("Nome do insumo (ex: Lâmina de Barbear): ");
        String nome = scanner.nextLine();
        
        double custo = 0;
        try {
            System.out.print("Custo Unitário: ");
            custo = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
        }

        int estoque = 0;
        try {
            System.out.print("Quantidade em Estoque: ");
            estoque = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida.");
        }

        Insumo novoInsumo = new Insumo(nome, estoque, custo);
        gerenciador.cadastrarInsumo(novoInsumo);
        System.out.println("Insumo cadastrado com sucesso!");
    }

    private static void cadastrarPromocao() {
        System.out.println("\n--- NOVA PROMOÇÃO ---");
        System.out.print("Nome da promoção (ex: Black Friday): ");
        String nome = scanner.nextLine();
        
        double desconto = 0;
        try {
            System.out.print("Porcentagem de desconto (ex: 15 para 15%): ");
            desconto = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
        }

        java.time.LocalDate inicio = java.time.LocalDate.now();
        java.time.LocalDate fim = java.time.LocalDate.now();
        
        try {
            System.out.print("Data de Início (AAAA-MM-DD): ");
            inicio = java.time.LocalDate.parse(scanner.nextLine());
            
            System.out.print("Data de Fim (AAAA-MM-DD): ");
            fim = java.time.LocalDate.parse(scanner.nextLine());
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Data inválida! Usando data de hoje para evitar erro.");
        }

        Promocao novaPromocao = new Promocao(nome, desconto, inicio, fim);
        gerenciador.cadastrarPromocao(novaPromocao);
        System.out.println("Promoção cadastrada com sucesso!");
    }

    // --- Métodos de Agendamento ---

    private static void realizandoAgendamento() {
        System.out.println("\n--- NOVO AGENDAMENTO ---");

        System.out.print("Digite o CPF do Cliente: ");
        String cpfCliente = scanner.nextLine();
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
            
            Agendamento agendamento = new Agendamento(cliente, barbeiro, servico, dataHora);
            gerenciador.agendar(agendamento);
            System.out.println("Agendamento realizado com sucesso!");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Formato de data inválido! Use o formato: 2025-12-10T14:30");
        }
    }

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

    // --- Métodos de Venda ---

    private static void novaVenda() {
        System.out.println("\n--- ABRINDO NOVA COMANDA ---");

        System.out.print("CPF do Cliente: ");
        String cpf = scanner.nextLine();
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
                            
                            // --- CORREÇÃO AQUI: Baixa real no estoque na memória ---
                            produtoEncontrado.setQtdEstoque(produtoEncontrado.getQtdEstoque() - 1);
                            // -------------------------------------------------------
                            
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

    // --- Métodos de Listagem ---

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

    // --- MÉTODOS DE MANUTENÇÃO (EDITAR/REMOVER) ---

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

    //Função para limpar a tela
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}