package View;

import Business.ClienteBusiness;
import Business.FuncionarioBussines;
import Business.Ped_ProdBusiness;
import Business.PedidoBusiness;
import Business.ProdutoBusiness;
import DAO.ProdutoDAO;
import Model.Cliente;
import Model.FormaPagamento;
import Model.Funcionario;
import Model.Ped_Prod;
import Model.Pedido;
import Model.Produto;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoView {

    static Scanner in = new Scanner(System.in);

    public static void create() {
        if (ProdutoBusiness.findAll().isEmpty()) {
            Main.clear();
            System.out.println("Não existem produtos cadastrados");
            System.out.println("Revertendo solicitação...");
            Main.waiting();
            Main.clear();
            return;
        } else {
            boolean verif = false;
            boolean verif2 = false;
            boolean verif3 = false;
            Date dataUtil = Date.valueOf(LocalDate.now());
            ClienteBusiness.findAll();
            Main.clear();
            System.out.println("Digite o id do cliente:");
            int idCliente = Integer.parseInt(in.nextLine());
            Cliente cliente = ClienteBusiness.findById(idCliente);
            for (Cliente clientes : ClienteBusiness.findAll()) {
                if (clientes.getId() == idCliente) {
                    verif = true;
                    if (clientes.isEstado() == false) {
                        Main.clear();
                        System.out.println("O cliente está desativado");
                        Main.waiting();
                        return;
                    }
                    cliente = ClienteBusiness.findById(idCliente);
                    break;
                }
            }
            if (verif == false) {
                Main.clear();
                System.out.println("Este cliente não existe");
                Main.waiting();
                return;
            }
            FuncionarioBussines.findAll();
            Main.clear();
            System.out.println("Digite o id do funcionario responsavel pela venda:");
            int idFuncionario = Integer.parseInt(in.nextLine());
            Funcionario funcionario = FuncionarioBussines.findById(idFuncionario);
            for (Funcionario funcionarios : FuncionarioBussines.findAll()) {
                if (funcionarios.getId() == idFuncionario) {
                    verif2 = true;
                    if (funcionarios.isEstado() == false) {
                        Main.clear();
                        System.out.println("Funcionario esta desativado");
                        Main.waiting();
                        return;
                    }
                    funcionario = FuncionarioBussines.findById(idFuncionario);
                    break;
                }
            }
            if (verif2 == false) {
                Main.clear();
                System.out.println("Este funcionario não existe");
                Main.waiting();
                return;
            }
            Main.clear();
            while (true) {
                System.out.println("\n=== FORMAS DE PAGAMENTO ===");
                System.out.println("> " + FormaPagamento.values()[0].name());
                System.out.println("> " + FormaPagamento.values()[1].name());
                System.out.println("===========================");
                try {
                    System.out.println("Informe a forma de pagamento [Apenas o nome]");
                    FormaPagamento formaPagamento = FormaPagamento.valueOf(in.nextLine().toUpperCase());
                    Pedido pedido = new Pedido(dataUtil, cliente, funcionario, formaPagamento);
                    Main.clear();
                    System.out.println("Pedido cadastrado com sucesso");
                    Main.waiting();
                    carrinho(pedido);
                    break;
                } catch (Exception e) {
                    Main.clear();
                    e.printStackTrace();
                    System.out.println("BURRO - Digitou a forma de pagamento errado");
                    Main.waiting();
                }
            }
        }
    }

    public static void carrinho(Pedido pedido) {
        List<Ped_Prod> produtosPed = new ArrayList();
        Ped_Prod apenasProds = new Ped_Prod();
        int qtdProduto;
        int idPedido = pedido.getId();
        boolean verif = false;
        while (true) {
            boolean verif10 = false;
            boolean verif3 = false;
            Main.clear();
            ProdutoView.listAll();
            System.out.println("Informe o id do produto:");
            int idProd = Integer.parseInt(in.nextLine());
            Produto produto = ProdutoBusiness.findbyId(idProd);
            for (Produto maisprodutos : ProdutoBusiness.findAll()) {
                if (maisprodutos.getId() == idProd) {
                    verif10 = true;
                }
            }
            if (!verif10) {
                Main.clear();
                System.out.println("Produto inexistente");
                Main.waiting();
                continue;
            }
            if (produto.isEstado() == false) {
                Main.clear();
                System.out.println("Produto está desativado");
                Main.waiting();
                continue;
            } else {
                apenasProds.setProduto(produto);
            }
            Main.clear();
            System.out.println("Informe a quantidade desejada");
            qtdProduto = Integer.parseInt(in.nextLine());
            if (qtdProduto <= 0) {
                Main.clear();
                System.out.println("ERRO - Quantidade inválida");
                Main.waiting();
                continue;
            }
            if (qtdProduto > produto.getQuantidade()) {
                Main.clear();
                System.out.println("SORRY =( Estoque insuficiente");
                Main.waiting();
            } else if (qtdProduto <= produto.getQuantidade()) {
                apenasProds.setQuantidade(qtdProduto);
                apenasProds.setPedido(pedido);
                produtosPed.add(apenasProds);
                verif3 = true;
            }
            if (pedido.getFormapagamento() == FormaPagamento.DINHEIRO) {
                System.out.println("Valor da compra: " + ( apenasProds.getPreco() - (apenasProds.getPreco()*0.05)));
            } else {
                System.out.println("Valor da compra: " + apenasProds.getPreco());
            }
            System.out.println("Informe a opção desejada\n[ 0 ] Finalizar Pedido\n[ 1 ] Continuar comprando\n[ 2 ]Cancelar a compra");
            int opcao = Integer.parseInt(in.nextLine());
            if (opcao == 0) {
                if (verif3 == false) {
                    System.out.println("Nenhum produto");
                } else {
                    pedido.setProdutos(produtosPed);
                    PedidoBusiness.create(pedido);
                    for (int i = 0; i < produtosPed.size(); i++) {
                        Ped_ProdBusiness.setPed_Prod(produtosPed.get(i).getPedido().getId(), produtosPed.get(i).getProduto().getId(), produtosPed.get(i).getQuantidade());
                        for (Produto produto3 : ProdutoDAO.findAll()) {
                            if (produto3.getId() == produtosPed.get(i).getProduto().getId()) {
                                ProdutoBusiness.updateMenosQtd(produto3.getId(), produtosPed.get(i).getQuantidade());
                            }
                        }
                    }
                    Main.clear();
                    System.out.println("Aguardando pagamento...");
                    Main.waiting();
                    Main.clear();
                    System.out.println("Contagem de notas...");
                    Main.waiting();
                    Main.clear();
                    System.out.println("Finalizando pedido...");
                    Main.waiting();
                    Main.clear();
                    System.out.println("Pedido finalizado");
                    return;
                }
            } else if (opcao == 1) {
                Main.clear();
                System.out.println("Continuando compra...");
                Main.waiting();
            } else if (opcao == 2) {
                Main.clear();
                System.out.println("Cancelando compra...");
                Main.waiting();
                return;
            } else {
                System.out.println("Hummm, parece que achou um easteregg");
            }
        }
    }

    public static void findAll() {
        boolean verif = false;
        for (Pedido pedido : PedidoBusiness.findAll()) {
            System.out.println("=========================");
            System.out.println("ID do pedido: " + pedido.getId());
            System.out.println("Cliente: " + pedido.getCliente().getNome() + "  ID: " + pedido.getCliente().getId());
            System.out.println("Funcionario: " + pedido.getFuncionario().getNome() + " ID: " + pedido.getFuncionario().getId());
            System.out.println("Forma Pagamento: " + pedido.getFormapagamento().name());
            verif = true;
            System.out.println("=========================");
        }
        System.out.println("´\nPressione enter para continuar");
        in.nextLine();
        if (verif == false) {
            Main.clear();
            System.out.println("Não existem pedidos cadastrados até o momento");
        }
    }

    public static void findById() {
        boolean verif = false;
        boolean verif2 = false;
        Main.clear();
        System.out.println("=== ID's Cadastradas ===");
        for (Pedido pedidos : PedidoBusiness.findAll()) {
            System.out.println("ID: " + pedidos.getId());
            verif2 = true;
        }
        if (verif2 == false) {
            Main.clear();
            System.out.println("Não existem pedidos cadastrados até o momento");
            Main.waiting();
            return;
        }
        System.out.println("Informe o ID do pedido:");
        int id = Integer.parseInt(in.nextLine());
        Pedido pedido = PedidoBusiness.findById(id);
        if (pedido.getId() == id) {
            verif = true;
            Main.clear();
            System.out.println("=========================");
            System.out.println("ID: " + pedido.getId());
            if (pedido.getCliente().isEstado() == false) {
                System.out.println("Cliente: Desativado");
            } else {
                System.out.println("Cliente: " + pedido.getCliente().getNome() + " ID: " + pedido.getCliente().getId());
            }
            if (pedido.getFuncionario().isEstado() == false) {
                System.out.println("Funcionário desativado");
            } else {
                System.out.println("Funcionário: " + pedido.getFuncionario().getNome() + " ID: " + pedido.getFuncionario().getId());
            }
            System.out.println("Payment method: " + pedido.getFormapagamento().name());
            System.out.println("Preço total: " + pedido.getPrecoTotal());
            System.out.println("\\======LISTA DE PRODUTOS COMPRADOS======/");
            for (int i = 0; i < pedido.getProdutos().size(); i++) {
                System.out.println("\nProduto:" + pedido.getProdutos().get(i).getProduto().getNome() + "  Quantidade: " + pedido.getProdutos().get(i).getQuantidade());
            }
            System.out.println("\n=========================");
            System.out.println("Pressione enter para continuar");
            in.nextLine();

        }
        if (verif == false) {
            Main.clear();
            System.out.println("Este pedido não existe");
        }
        if (pedido == null) {
            Main.clear();
            System.out.println("Este pedido não existe");
            Main.waiting();
            return;
        }
    }
}
