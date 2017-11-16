package View;

import Business.ClienteBusiness;
import Business.ProdutoBusiness;
import Business.TelefoneBusiness;
import DAO.ClienteDAO;
import Model.Cliente;
import Model.Produto;
import Model.Telefone;
import static View.ProdutoView.in;
import java.util.Scanner;

public class ClienteView {

    static Scanner in = new Scanner(System.in);

    public static void criar() {
        System.out.println("Digite o nome do novo cliente");
        String nome = in.nextLine();
        Main.clear();
        System.out.println("Digite o cpf do novo cliente");
        String cpf = in.nextLine();
        boolean estado = true;

        if (ClienteBusiness.create(nome, cpf, estado)) {
            Main.clear();
            System.out.println("\nCliente cadastrado com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao cadastrar cliente: Este CPF Já está registrado");
            Main.waiting();
        }
    }

    public static void desable() {
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("Digite o id do cliente");
        try {
            int id = Integer.parseInt(in.nextLine());
            boolean estado = true;
            boolean verif = false;
            Cliente cliente = ClienteBusiness.findById(id);
            for (Cliente clientes : ClienteBusiness.findAll()) {
                if (clientes.getId() == id) {
                    verif = true;
                    break;
                }
            }
            if (verif == false) {
                Main.clear();
                System.out.println("Este cliente não existe");
                Main.waiting();
                return;
            }
            if (cliente.isEstado() == false) {
                estado = true;
                if (ClienteBusiness.delete(id, estado)) {
                    Main.clear();
                    System.out.println("Cliente ativado com sucesso");
                    Main.waiting();
                }
            } else if (cliente.isEstado() == true) {
                estado = false;
                if (ClienteBusiness.delete(id, estado)) {
                    Main.clear();
                    System.out.println("Produto desativado com sucesso");
                    Main.waiting();
                }
            }
        } catch (Exception e) {
            System.out.println("APENAS NUMEROS");
        }

    }

    public static void updateName() {
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("Digite o id do cliente a ser editado");
        int id = 0;
        String nome = null;
        try {
            id = in.nextInt();
        } catch (Exception e) {
            Main.clear();
            System.out.println("Digite apenas numeros");
        }
        in.nextLine();
        Main.clear();
        for (int i = 0; i < ClienteDAO.findAll().size(); i++) {
            if (id == ClienteDAO.findAll().get(i).getId()) {
                System.out.println("Nome atual: " + ClienteDAO.findById(id).getNome());
            }
        }
        System.out.println("Digite o novo nome do cliente " + id);
        nome = in.nextLine();
        if (ClienteBusiness.updateNome(id, nome)) {
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
        if (ClienteDAO.findAll().isEmpty()) {
            System.out.println("NÃO EXISTEM CLIENTES| CADASTRADOS EM NOSSO SISTEMA");
            Main.waiting();
        } else {
            Main.clear();
            for (int i = 0; i < ClienteDAO.findAll().size(); i++) {
                System.out.println("\n==============================");
                System.out.println("ID: " + ClienteBusiness.findAll().get(i).getId());
                System.out.println("Nome: " + ClienteBusiness.findAll().get(i).getNome());
                System.out.println("CPF: " + ClienteBusiness.findAll().get(i).getCpf());
                System.out.println("Estado: " + (ClienteBusiness.findAll().get(i).isEstado() == true ? "Ativado" : "Desativado"));
                System.out.println("==============================\n");
            }
            System.out.println("Pressione enter para continuar");
            in.nextLine();
            Main.clear();
        }
    }

    public static void showClient() {
        int id = 0;
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("\nDigite o ID do cliente a ser exibido as informações");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            Main.clear();
            System.out.println("\nDIGITE APENAS NUMEROS SEU IDIOTA");
            Main.waiting();
        }
        Cliente cliente = ClienteDAO.findById(id);
        if (cliente != null) {
            System.out.println("\n==============================");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Estado: " + (ClienteBusiness.findById(id).isEstado() == true ? "Ativado" : "Desativado"));
            if (cliente.getTelefones().isEmpty()) {
                System.out.println("Telefones: SEM TELEFONE (POBRE)\n");
            } else {
                int i = 0;
                System.out.println("Telefones: \n");
                for (Telefone telefone : cliente.getTelefones()) {
                    System.out.println("Numero: " + cliente.getTelefones().get(i).getNumero());
                    i++;
                }
            }
            System.out.println("==============================\n");
            System.out.println("Pressione enter para continuar");
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
            if (ClienteDAO.findById(id) != null) {
                Main.clear();
                System.out.println("\nDigite o numero do telefone");
                String numero = in.nextLine();
                TelefoneBusiness.createTelefone(numero, ClienteDAO.findById(id));
                Main.clear();
                System.out.println("\nTelefone cadastrado com sucesso");
            } else {
                Main.clear();
                System.out.println("ID Inválida");
                Main.waiting();
                Main.clear();
            }
        } catch (Exception e) {
            System.out.println("\nAPENAS NUMEROS SEU IDIOTA");
        }
    }

    public static void deleteFone() {
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
            if (TelefoneBusiness.verifyDonoTelefone(idNum, ClienteDAO.findById(id))) {
                TelefoneBusiness.deleteFone(idNum);
                Main.clear();
                System.out.println("Telefone removido com sucesso!");
                Main.waiting();
            } else {
                Main.clear();
                System.out.println("\nEsse cliente não possui esse telefone");
                Main.waiting();
            }

        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
            Main.waiting();
        }
    }
}
