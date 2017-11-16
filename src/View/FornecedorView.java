package View;

import Business.FornecedorBusiness;
import Business.ProdutoBusiness;
import Business.TelefoneBusiness;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import DAO.TelefoneDAO;
import Model.Fornecedor;
import Model.Pessoa;
import Model.Telefone;
import java.util.Scanner;

public class FornecedorView {

    static Scanner in = new Scanner(System.in);

    public static void criar() {
        System.out.println("Digite o nome do novo fornecedor");
        String nome = in.nextLine();
        Main.clear();
        System.out.println("Digite o cnpj de " + nome);
        String cnpj = in.nextLine();
        Main.clear();
        if (FornecedorBusiness.create(nome, cnpj)) {
            Main.clear();
            System.out.println("\nFornecedor cadastrado com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao cadastrar fornecedor: Este CNPJ Já está registrado");
            Main.waiting();
        }
    }

    public static void delete() {
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("Digite o id do fornecedor a ser deletado");
        int id = 0;
        try {
            id = in.nextInt();
        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
            Main.waiting();
        }

        if (FornecedorBusiness.delete(id)) {
            Main.clear();
            System.out.println("\nFornecedor removido com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao remover cadastro: ID inexistente");
            Main.waiting();
        }

    }

    public static void updateName() {
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("Digite o id do fornecedor a ser editado");
        int id = 0;
        String nome = null;
        try {
            id = in.nextInt();
        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
        }
        in.nextLine();
        Main.clear();
        for (int i = 0; i < FornecedorDAO.findAll().size(); i++) {
            if (FornecedorDAO.findById(id) == null) {
            } else {
                System.out.println("Nome atual: " + FornecedorDAO.findById(id).getNome());
            }
        }
        System.out.println("Digite o novo nome do fornecedor " + id);
        nome = in.nextLine();
        if (FornecedorBusiness.updateNome(id, nome)) {
            Main.clear();
            System.out.println("\nNome alterado com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao alterar o nome: ID informada é inexistente");
            Main.waiting();
        }
    }

    public static void listAll() {
        Main.clear();
        if (FornecedorDAO.findAll().isEmpty()) {
            System.out.println("NÃO EXISTEM FORNECEDORES CADASTRADOS EM NOSSO SISTEMA");
            Main.waiting();
        } else {
            for (int i = 0; i < FornecedorDAO.findAll().size(); i++) {
                System.out.println("\n==============================");
                System.out.println("ID: " + FornecedorDAO.findAll().get(i).getId());
                System.out.println("Nome: " + FornecedorDAO.findAll().get(i).getNome());
                System.out.println("CNPJ: " + FornecedorDAO.findAll().get(i).getCnpj());
                System.out.println("==============================\n");

            }
            System.out.println("\nPressione enter para continuar");
            in.nextLine();
        }
    }

    public static void showFornecedor() {
        int id = 0;
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("\nDigite o ID do fornecedor a ser exibido as informações");
        try {
            id = Integer.parseInt(in.nextLine());
            if (id > FornecedorDAO.findAll().size()) {
                Main.clear();
                System.out.println("ID INEXISTENTE");
                Main.waiting();
                return;
            }
        } catch (Exception e) {
            Main.clear();
            System.out.println("\nDIGITE APENAS NUMEROS SEU IDIOTA");
            Main.waiting();
        }
        Fornecedor fornecedor = FornecedorDAO.findById(id);
        if (fornecedor.getId() != 0) {
            Main.clear();
            System.out.println("\n==============================");
            System.out.println("ID: " + fornecedor.getId());
            System.out.println("Nome: " + fornecedor.getNome());
            System.out.println("CNPJ: " + fornecedor.getCnpj());
            if (fornecedor.getTelefones().isEmpty()) {
                System.out.println("Telefones: SEM TELEFONE (POBRE)\n");
            } else {
                System.out.println("Telefones: ");
                for (Telefone telefone : fornecedor.getTelefones()) {
                    System.out.println("Telefone ID: " + telefone.getId());
                    System.out.println("Numero: " + telefone.getNumero());
                    System.out.println("");
                }
            }
            System.out.println("==============================\n");
            System.out.println("\nPressione enter para continuar");
            in.nextLine();
            Main.clear();
        } else {
            System.out.println("ERRO: ID não encontrada no sistema");
        }
    }

    public static void createFone() {
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("\nDigite o id do dono");
        try {
            int id = Integer.parseInt(in.nextLine());
            if (FornecedorDAO.findById(id) != null) {
                Main.clear();
                System.out.println("\nDigite o numero do telefone");
                String numero = in.nextLine();
                TelefoneBusiness.createTelefone(numero, FornecedorDAO.findById(id));
                Main.clear();
                System.out.println("\nTelefone cadastrado com sucesso");
            }else{
                Main.clear();
                System.out.println("ID Inválida");
                Main.waiting();
                Main.clear();
            }
        } catch (Exception e) {
            System.out.println("\nAPENAS NUMEROS SEU IDIOTA");
        }

    }
    
    public static void deleteFone(){
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("Digite o id do dono do telefone");
        try {
            int id = Integer.parseInt(in.nextLine());
            System.out.println("Digite o ID do numero");
            int idNum = Integer.parseInt(in.nextLine());
            if(TelefoneBusiness.verifyDonoTelefone(idNum, FornecedorDAO.findById(id))){
                TelefoneBusiness.deleteFone(idNum);
                Main.clear();
                System.out.println("Telefone removido com sucesso!");
                Main.waiting();
            }else {
                Main.clear();
                System.out.println("\nEsse fornecedor não possui esse telefone");
                Main.waiting();
            }

        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
            Main.waiting();
        }
    }
}
