package barbershop;

import barbershop.model.*; // Importante para reconhecer Cliente e Barbeiro
import barbershop.service.GerenciadorDeDados;
import java.util.Scanner;

public class Main {
    private static GerenciadorDeDados gerenciador = new GerenciadorDeDados();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;

        System.out.println("=== BEM-VINDO AO BARBERSHOP MANAGER ===");

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
                    menuComanda(); // Vendas e serviços
                    break;
                case 4:
                    menuRelatorios(); // Listar dados
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
        System.out.println("1. Cadastros (Cliente, Barbeiro, Produto...)");
        System.out.println("2. Agendamentos");
        System.out.println("3. Vendas e Comandas");
        System.out.println("4. Relatórios e Listagens");
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
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarBarbeiro();
                    break;
                case 3:
                    // cadastrarServico(); 
                    System.out.println("Em desenvolvimento...");
                    break;
                case 4:
                    // cadastrarProduto(); 
                    System.out.println("Em desenvolvimento...");
                    break;
                case 5:
                     // cadastrarInsumo();
                     System.out.println("Em desenvolvimento...");
                     break;
                case 6:
                     // cadastrarPromocao();
                     System.out.println("Em desenvolvimento...");
                     break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Stubs para os outros menus principais
    private static void menuAgendamentos() {
        System.out.println("\n--- AGENDAMENTOS ---");
        System.out.println("Funcionalidade em desenvolvimento...");
    }

    private static void menuComanda() {
        System.out.println("\n--- VENDAS ---");
        System.out.println("Funcionalidade em desenvolvimento...");
    }

    private static void menuRelatorios() {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("Funcionalidade em desenvolvimento...");
    }

    // --- Métodos Auxiliares de Cadastro ---

    private static void cadastrarCliente() {
        System.out.println("\n--- NOVO CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        // Cria o objeto
        Cliente novoCliente = new Cliente(nome, cpf, telefone);
        
        // Manda pro gerenciador salvar (Memória + Arquivo)
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
}