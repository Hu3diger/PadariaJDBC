package View;

import Business.CargoBusiness;
import Business.FuncionarioBussines;
import Business.ProdutoBusiness;
import Business.TelefoneBusiness;
import DAO.FuncionarioDAO;
import Model.CargoF;
import Model.Funcionario;
import Model.Produto;
import Model.Telefone;
import static View.ProdutoView.in;
import java.util.Scanner;

public class FuncionarioView {

    static Scanner in = new Scanner(System.in);

    public static void criar() {
        System.out.println("Digite o nome do novo funcionario");
        String nome = in.nextLine();
        Main.clear();
        System.out.println("Digite o cpf de " + nome);
        String cpf = in.nextLine();
        Main.clear();
        for (CargoF cargo : CargoBusiness.findAll()) {
            System.out.println("CARGOS DISPONÍVEIS");
            System.out.println("ID: " + cargo.getId());
            System.out.println("Nome: " + cargo.getNome() + "\n");
        }
        System.out.println("Digite o ID do cargo");
        int idcargo = Integer.parseInt(in.nextLine());
        System.out.println("Digite o salário de " + nome);
        CargoF cargo = null;
        cargo = CargoBusiness.findById(idcargo);
        boolean estado = true;
        double salario = in.nextDouble();
        if (FuncionarioBussines.create(nome, cpf, cargo, salario, estado)) {
            Main.clear();
            System.out.println("\nFuncionario cadastrado com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao cadastrar funcionario: Este CPF Já está registrado");
            Main.waiting();
        }
    }


    public static void updateName() {
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("Digite o id do funcionario a ser editado");
        int id = 0;
        String nome = null;
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            Main.clear();
            System.out.println("Digite apenas numeros");
        }
        in.nextLine();
        Main.clear();
        for (int i = 0; i < FuncionarioDAO.findAll().size(); i++) {
            if (FuncionarioDAO.findById(id) == null) {
            } else {
                System.out.println("Nome atual: " + FuncionarioDAO.findById(id).getNome());
            }
        }
        System.out.println("Digite o novo nome do funcionario " + id);
        nome = in.nextLine();
        if (FuncionarioBussines.updateNome(id, nome)) {
            Main.clear();
            System.out.println("\nNome alterado com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao alterar o nome: ID informada é inexistente");
            Main.waiting();
        }
    }
    
    public static void desable() {
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("Digite o id do funcionario");
        try {
            int id = Integer.parseInt(in.nextLine());
            boolean estado = true;
            boolean verif = false;
            Funcionario funcionario = FuncionarioBussines.findById(id);
            for (Funcionario funcionarios : FuncionarioBussines.findAll()) {
                if (funcionarios.getId() == id) {
                    verif = true;
                    break;
                }
            }
            if (verif == false) {
                Main.clear();
                System.out.println("Este funcionario não existe");
                Main.waiting();
                return;
            }
            if (funcionario.isEstado() == false) {
                estado = true;
                if (FuncionarioBussines.updateEstado(id, estado)) {
                    Main.clear();
                    System.out.println("Funcionario recontratado");
                    Main.waiting();
                }
            } else if (funcionario.isEstado() == true) {
                estado = false;
                if (FuncionarioBussines.updateEstado(id, estado)) {
                    Main.clear();
                    System.out.println("Funcionario desligado com sucesso");
                    Main.waiting();
                }
            }
        } catch (Exception e) {
            System.out.println("APENAS NUMEROS");
        }

    }

    public static void listAll() {
        Main.clear();
        if (FuncionarioDAO.findAll().isEmpty()) {
            System.out.println("NÃO EXISTEM FUNCIONÁRIOS CADASTRADOS EM NOSSO SISTEMA");
            Main.waiting();
        } else {
            for (int i = 0; i < FuncionarioDAO.findAll().size(); i++) {
                System.out.println("\n==============================");
                System.out.println("ID: " + FuncionarioDAO.findAll().get(i).getId());
                System.out.println("Nome: " + FuncionarioDAO.findAll().get(i).getNome());
                System.out.println("CPF: " + FuncionarioDAO.findAll().get(i).getCpf());
                System.out.println("Estado: " + (FuncionarioBussines.findAll().get(i).isEstado() == true ? "Ativado" : "Desativado"));
                System.out.println("==============================\n");
            }
            System.out.println("Pressione enter para continuar");
            in.nextLine();
            in.nextLine();
        }
    }

    public static void showFuncionario() {
        int id = 0;
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("\nDigite o ID do funcionario a ser exibido as informações");
        try {
            id = Integer.parseInt(in.nextLine());
            if (id > FuncionarioDAO.findAll().size()) {
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
        Funcionario funcionario = FuncionarioDAO.findById(id);
        if (funcionario.getId() != 0) {
            Main.clear();
            System.out.println("\n==============================");
            System.out.println("ID: " + funcionario.getId());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("CPF: " + funcionario.getCpf());
            System.out.println("Estado: " + (FuncionarioBussines.findById(id).isEstado() == true ? "Ativado" : "Desativado"));
            if (funcionario.getTelefones().isEmpty()) {
                System.out.println("Telefones: SEM TELEFONE (POBRE)\n");
            } else {
                System.out.println("Telefones: \n");
                for (Telefone telefone : funcionario.getTelefones()) {
                    System.out.println("ID: " + telefone.getId());
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
            if (FuncionarioDAO.findById(id) != null) {
                Main.clear();
                System.out.println("\nDigite o numero do telefone");
                String numero = in.nextLine();
                TelefoneBusiness.createTelefone(numero, FuncionarioDAO.findById(id));
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
            if (TelefoneBusiness.verifyDonoTelefone(idNum, FuncionarioDAO.findById(id))) {
                TelefoneBusiness.deleteFone(idNum);
                Main.clear();
                System.out.println("Telefone removido com sucesso!");
                Main.waiting();
            } else {
                Main.clear();
                System.out.println("\nEsse funcionário não possui esse telefone");
                Main.waiting();
            }
        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
            Main.waiting();
        }
    }

    public static void updateSal() {
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        int id = 0;
        System.out.println("\nDigite o ID do funcionario a ser alterado o salário");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
            Main.waiting();
        }
        for (int i = 0; i < FuncionarioDAO.findAll().size(); i++) {
            if (FuncionarioDAO.findById(id) == null) {
            } else {
                System.out.println("Nome atual: " + FuncionarioDAO.findById(id).getSalario());
            }
        }
        System.out.println("Informe o novo salário");
        double sal = in.nextDouble();

        FuncionarioBussines.updateSalario(id, sal);
        Main.clear();
        System.out.println("Salário alterado com sucesso");
        Main.waiting();
    }

    public static void updateCargo() {
        Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        int id = 0;
        System.out.println("\nDigite o ID do funcionario a ser alterado o salário");
        try {
            id = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            Main.clear();
            System.out.println("APENAS NUMEROS SEU IDIOTA");
            Main.waiting();
        }
        for (int i = 0; i < FuncionarioDAO.findAll().size(); i++) {
            if (FuncionarioDAO.findById(id) == null) {
            } else {
                for (CargoF cargo : CargoBusiness.findAll()) {
                    System.out.println("CARGOS DISPONÍVEIS");
                    System.out.println("ID: " + cargo.getId());
                    System.out.println("Nome: " + cargo.getNome() + "\n");
                }
                System.out.println("\nCargo atual: " + FuncionarioBussines.findById(id).getCargo().getNome());
            }
        }
        System.out.println("Informe o id do novo cargo");
        int idcargo = Integer.parseInt(in.nextLine());
        CargoF cargo = null;
        cargo = CargoBusiness.findById(idcargo);

        if (FuncionarioBussines.updateCargo(id, cargo)) {
            Main.clear();
            System.out.println("Cargo alterado com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("Funcionário inválido");
            Main.waiting();
        }
    }
}
