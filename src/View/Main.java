package View;

import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void waiting() {
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while (true) {
            clear();
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Gerenciamento de clientes");
            System.out.println("[ 2 ] Gerenciamento de funcionarios");
            System.out.println("[ 3 ] Gerenciamento de fornecedores");
            System.out.println("[ 4 ] Gerenciamento de produtos");
            System.out.println("[ 5 ] Gerenciamento de pedidos");
            System.out.println("[ 6 ] Gerenciamento de cargos");
            System.out.println("[ 0 ] Desligar sistema\n");

            System.out.println("Digite a opção desejada\n");
            String menu = in.nextLine();

            switch (menu) {
                case "1":
                    clear();
                    menuCliente();
                    break;
                case "2":
                    clear();
                    menuFuncionario();
                    break;
                case "3":
                    clear();
                    menuFornecedor();
                    break;
                case "4":
                    clear();
                    menuProduto();
                    break;
                case "5":
                    clear();
                    menuPedido();
                    break;
                case "6":
                    clear();
                    menuCargo();
                    break;
                case "0":
                    clear();
                    System.out.println("Obrigado por utilizar o Hu3diger's BKM");
                    waiting();
                    System.exit(0);
                    break;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

    public static void menuCliente() {
        pau:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Adicionar cliente");
            System.out.println("[ 2 ] Alterar estado do cliente");
            System.out.println("[ 3 ] Alterar  cliente");
            System.out.println("[ 4 ] Listar clientes");
            System.out.println("[ 0 ] Voltar\n");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    ClienteView.criar();
                    break;
                case "2":
                    clear();
                    ClienteView.desable();
                    break;
                case "3":
                    clear();
                    alterationCliente();
                    break;
                case "4":
                    clear();
                    ClienteView.listAll();
                    break;
                case "0":
                    break pau;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

    public static void menuProduto() {
        pau:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Adicionar produto");
            System.out.println("[ 2 ] Alterar produto");
            System.out.println("[ 3 ] Listar produtos");
            System.out.println("[ 0 ] Voltar\n");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    ProdutoView.createProd();
                    break;
                case "2":
                    clear();
                    alterationProduto();
                    break;
                case "3":
                    clear();
                    ProdutoView.listAll();
                    break;
                case "0":
                    break pau;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }
    
    public static void menuCargo() {
        pau:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Criar novo cargo");
            System.out.println("[ 2 ] Alterar nome do cargo");
            System.out.println("[ 3 ] Listar cargos");
            System.out.println("[ 0 ] Voltar\n");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    CargoView.create();
                    break;
                case "2":
                    clear();
                    CargoView.update();
                    break;
                case "3":
                    clear();
                    CargoView.listAll();
                    break;
                case "0":
                    break pau;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

    public static void menuPedido() {
        pauzinho:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Criar novo pedido");
            System.out.println("[ 2 ] Listar pedidos");
            System.out.println("[ 3 ] Listar pedido expecífico");
            System.out.println("[ 0 ] Voltar\n");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    PedidoView.create();
                    break;
                case "2":
                    clear();
                    PedidoView.findAll();
                    break;
                case "3":
                    clear();
                    PedidoView.findById();
                    break;
                case "0":
                    return;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

    public static void alterationFuncionario() {
        pau:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Alterar nome");
            System.out.println("[ 2 ] Alterar salario");
            System.out.println("[ 3 ] Alterar cargo");
            System.out.println("[ 4 ] Adicionar telefone");
            System.out.println("[ 5 ] Remover telefone");
            System.out.println("[ 6 ] Mostrar informações do funcionario");
            System.out.println("[ 0 ] Voltar");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    FuncionarioView.updateName();
                    break;
                case "2":
                    clear();
                    FuncionarioView.updateSal();
                    break;
                case "3":
                    clear();
                    FuncionarioView.updateCargo();
                    break;
                case "4":
                    clear();
                    FuncionarioView.createFone();
                    break;
                case "5":
                    clear();
                    FuncionarioView.deleteFone();
                    break;
                case "6":
                    clear();
                    FuncionarioView.showFuncionario();
                    break;
                case "0":
                    break pau;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

    public static void alterationProduto() {
        pau:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Alterar nome");
            System.out.println("[ 2 ] Alterar preço");
            System.out.println("[ 3 ] Alterar descrição");
            System.out.println("[ 4 ] Adicionar estoque");
            System.out.println("[ 5 ] Adicionar fornecedor");
            System.out.println("[ 6 ] Alterar estado");
            System.out.println("[ 0 ] Voltar");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    ProdutoView.updateName();
                    break;
                case "2":
                    clear();
                    ProdutoView.updatePreco();
                    break;
                case "3":
                    clear();
                    ProdutoView.updateDescri();
                    break;
                case "4":
                    clear();
                    ProdutoView.addEstoque();
                    break;
                case "5":
                    clear();
                    ProdutoView.addFornecedor();
                    break;
                case "6":
                    clear();
                    ProdutoView.desable();
                    break;
                case "0":
                    break pau;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

    public static void alterationCliente() {
        pau:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Alterar nome");
            System.out.println("[ 2 ] Adicionar telefone");
            System.out.println("[ 3 ] Remover telefone");
            System.out.println("[ 4 ] Mostrar informações do funcionario");
            System.out.println("[ 0 ] Voltar");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    ClienteView.updateName();
                    break;
                case "2":
                    clear();
                    ClienteView.createFone();
                    break;
                case "3":
                    clear();
                    ClienteView.deleteFone();
                    break;
                case "4":
                    clear();
                    ClienteView.showClient();
                    break;
                case "0":
                    break pau;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

    public static void menuFornecedor() {
        pau:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Adicionar fornecedor");
            System.out.println("[ 2 ] Alterar fornecedor");
            System.out.println("[ 3 ] Listar fornecedores");
            System.out.println("[ 0 ] Voltar\n");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    FornecedorView.criar();
                    break;
                case "2":
                    clear();
                    alterationFornecedor();
                    break;
                case "3":
                    clear();
                    FornecedorView.listAll();
                    break;
                case "0":
                    break pau;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

    public static void alterationFornecedor() {
        pau:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Alterar nome");
            System.out.println("[ 2 ] Adicionar telefone");
            System.out.println("[ 3 ] Remover telefone");
            System.out.println("[ 4 ] Mostrar informações do fornecedor");
            System.out.println("[ 0 ] Voltar");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    FornecedorView.updateName();
                    break;
                case "2":
                    clear();
                    FornecedorView.createFone();
                    break;
                case "3":
                    clear();
                    FornecedorView.deleteFone();
                    break;
                case "4":
                    clear();
                    FornecedorView.showFornecedor();
                    break;
                case "0":
                    break pau;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

    public static void menuFuncionario() {
        pau:
        while (true) {
            System.out.println("\n================================");
            System.out.println("= Hu3diger's Bakery Management =");
            System.out.println("=========== v1.0 ===============");
            System.out.println("\n[ 1 ] Adicionar funcionario");
            System.out.println("[ 2 ] Alterar funcionario");
            System.out.println("[ 3 ] Listar funcionarios");
            System.out.println("[ 4 ] Alterar estado");
            System.out.println("[ 0 ] Voltar\n");

            System.out.println("Digite a opção desejada");
            String menu = in.nextLine();
            switch (menu) {
                case "1":
                    clear();
                    FuncionarioView.criar();
                    break;
                case "2":
                    clear();
                    alterationFuncionario();
                    break;
                case "3":
                    clear();
                    FuncionarioView.listAll();
                    break;
                case "4":
                    clear();
                    FuncionarioView.desable();
                    break;
                case "0":
                    break pau;
                default:
                    clear();
                    System.out.println("Opção inválida");
                    waiting();
            }
        }
    }

}
