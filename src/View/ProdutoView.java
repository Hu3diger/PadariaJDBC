package View;

import Business.FornecedorBusiness;
import Business.ProdutoBusiness;
import Model.Fornecedor;
import Model.Produto;
import java.util.Scanner;

public class ProdutoView {

    static Scanner in = new Scanner(System.in);

    public static void createProd() {
        Main.clear();
        System.out.println("Digite o nome do novo produto");
        String nome = in.nextLine();
        Main.clear();
        preco: while (true) {
            System.out.println("Digite o preço do novo produto");
            try {
                double preco = Double.parseDouble(in.nextLine());
                if (preco >= 0) {
                    Main.clear();
                    System.out.println("Informe uma breve descrição do produto");
                    String descri = in.nextLine();
                    Main.clear();
                    qtd: while (true) {
                        System.out.println("Qual a quantidade em estoque?");
                        int qtd = Integer.parseInt(in.nextLine());
                        if (qtd > -1) {
                            Main.clear();
                            Fornecedor fornecedor = null;
                            boolean estado = true;
                            ProdutoBusiness.create(nome, preco, descri, fornecedor, qtd, estado);
                            System.out.println("Produto cadastrado com sucesso!");
                            break preco;
                        } else {
                            Main.clear();
                            System.out.println("A Quantidade informada deve ser positiva");
                            Main.waiting();
                            Main.clear();
                        }
                    }
                } else {
                    Main.clear();
                    System.out.println("Preço deve ser válido (valor positivo ou zero)");
                    Main.waiting();
                    Main.clear();
                }
            } catch (Exception e) {
                Main.clear();
                System.out.println("DIGITE APENAS NUMERO SEU IDIOTA");
            }
            
        }

    }

    public static void listAll() {
        Main.clear();
        if (ProdutoBusiness.findAll().isEmpty()) {
            System.out.println("NÃO EXISTEM PRODUTOS CADASTRADOS EM NOSSO SISTEMA");
            Main.waiting();
        } else {
            for (int i = 0; i < ProdutoBusiness.findAll().size(); i++) {
                System.out.println("\n==============================");
                System.out.println("ID: " + ProdutoBusiness.findAll().get(i).getId());
                System.out.println("Nome: " + ProdutoBusiness.findAll().get(i).getNome());
                System.out.println("Preço: " + ProdutoBusiness.findAll().get(i).getPreco());
                System.out.println("Descrição: " + ProdutoBusiness.findAll().get(i).getDescricao());
                System.out.println("Estoque: " + ProdutoBusiness.findAll().get(i).getQuantidade());
                System.out.println("Fornecedor: " + (ProdutoBusiness.findAll().get(i).getFornecedor() == null ? "SEM FORNECEDOR" : ProdutoBusiness.findAll().get(i).getFornecedor().getNome()));
                System.out.println("Estado: " + (ProdutoBusiness.findAll().get(i).isEstado() == true ? "Ativado" : "Desativado"));
                System.out.println("==============================\n");

            }
            System.out.println("\nPressione enter para continuar");
            in.nextLine();
        }
    }

    public static void updateName() {
        System.out.println("Digite o id do produto a ser editado");
        int id = 0;
        String nome = null;
        try {
            id = in.nextInt();
        } catch (Exception e) {
            Main.clear();
            System.out.println("APEOS SEU IDIOTA");
        }
        in.nextLine();
        Main.clear();
        for (int i = 0; i < ProdutoBusiness.findAll().size(); i++) {
            if (ProdutoBusiness.findbyId(id) == null) {
            } else {
                System.out.println("Nome atual: " + ProdutoBusiness.findbyId(id).getNome());
            }
        }
        System.out.println("Digite o novo nome do produto " + id);
        nome = in.nextLine();
        if (ProdutoBusiness.updateNome(id, nome)) {
            Main.clear();
            System.out.println("\nNome alterado com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao alterar o nome: ID informada é inexistente");
            Main.waiting();
        }
    }

    public static void updatePreco() {
        System.out.println("Digite o id do produto a ser editado");
        int id = 0;
        double preco = 0;
        try {
            id = in.nextInt();
        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
        }
        in.nextLine();
        Main.clear();
        for (int i = 0; i < ProdutoBusiness.findAll().size(); i++) {
            if (ProdutoBusiness.findbyId(id) == null) {
            } else {
                System.out.println("Preco atual: " + ProdutoBusiness.findbyId(id).getPreco());
            }
        }
        System.out.println("Digite o novo preco do produto ");
        preco = Double.parseDouble(in.nextLine());
        if (ProdutoBusiness.updatePreco(id, preco)) {
            Main.clear();
            System.out.println("\nPreço alterado com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao alterar o nome: ID informada é inexistente");
            Main.waiting();
        }
    }

    public static void updateDescri() {
        System.out.println("Digite o id do produto a ser editado");
        int id = 0;
        double preco = 0;
        try {
            id = in.nextInt();
        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
        }

        in.nextLine();
        Main.clear();
        for (int i = 0; i < ProdutoBusiness.findAll().size(); i++) {
            if (ProdutoBusiness.findbyId(id) == null) {
            } else {
                System.out.println("Descrição atual: " + ProdutoBusiness.findbyId(id).getDescricao());
            }
        }
        System.out.println("Digite a nova descrição do produto ");
        preco = Double.parseDouble(in.nextLine());
        if (ProdutoBusiness.updatePreco(id, preco)) {
            Main.clear();
            System.out.println("\nDescrição alterada com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao alterar o nome: ID informada é inexistente");
            Main.waiting();
        }
    }

    public static void addEstoque() {
        System.out.println("Digite o id do produto a ser adicionado estoque");
        int id = 0;
        int qtd = 0;
        try {
            id = in.nextInt();
        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
        }
        in.nextLine();
        Main.clear();
        for (int i = 0; i < ProdutoBusiness.findAll().size(); i++) {
            if (ProdutoBusiness.findbyId(id) == null) {
            } else {
                System.out.println("Quantidade atual: " + ProdutoBusiness.findbyId(id).getQuantidade());
            }
        }
        System.out.println("Digite a quantidade do produto a ser adicionado");
        qtd = Integer.parseInt(in.nextLine());
        int newEstoq = ProdutoBusiness.findbyId(id).getQuantidade() + qtd;
        if (ProdutoBusiness.updateQtd(id, newEstoq)) {
            Main.clear();
            System.out.println("\nEstoque alterado com sucesso\nAgora o estoque possui " + newEstoq + " itens");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao alterar o nome: ID informada é inexistente");
            Main.waiting();
        }
    }

    public static void addFornecedor() {
        Main.clear();
        System.out.println("Digite o id do produto a ser adicionado fornecedor");
        try {
            int idprod = Integer.parseInt(in.nextLine());
            System.out.println("Digite o id do fornecedor a ser adicionado");
            int idfun = Integer.parseInt(in.nextLine());
            ProdutoBusiness.updateFornecedor(idprod, FornecedorBusiness.findbyId(idfun));
            System.out.println("Fornecedor adicionado com sucesso");
            Main.waiting();
            Main.clear();
        } catch (Exception e) {
            System.out.println("ERRO - DIGITE APENAS NUMEROS SEU IDIOTA");
        }
    }

    public static void desable() {
        Main.clear();
        System.out.println("Digite o id do produto");
        try {
            int id = Integer.parseInt(in.nextLine());
            boolean estado = true;
            boolean verif = false;
            Produto produto = ProdutoBusiness.findbyId(id);
            for (Produto produtos : ProdutoBusiness.findAll()) {
                if (produtos.getId() == id) {
                    verif = true;
                    break;
                }
            }
            if (verif == false) {
                Main.clear();
                System.out.println("Este produto não existe");
                Main.waiting();
                return;
            }
            if (produto.isEstado() == false) {
                estado = true;
                if (ProdutoBusiness.delete(id, estado)) {
                    Main.clear();
                    System.out.println("Produto ativado com sucesso");
                    Main.waiting();
                }
            } else if (produto.isEstado() == true) {
                estado = false;
                if (ProdutoBusiness.delete(id, estado)) {
                    Main.clear();
                    System.out.println("Produto desativado com sucesso");
                    Main.waiting();
                }
            }
        } catch (Exception e) {
            System.out.println("APENAS NUMEROS");
        }

    }
}
