package View;

import Business.CargoBusiness;
import DAO.CargoFDAO;
import Model.CargoF;
import java.util.List;
import java.util.Scanner;

public class CargoView {
    
    static Scanner in = new Scanner(System.in);
    
    public static void create(){
        Main.clear();
        System.out.println("Digite o nome do novo cargo");
        String nome = in.nextLine();
        CargoF cargo = new CargoF();
        cargo.setNome(nome);
        CargoFDAO.create(cargo);
        Main.clear();
        System.out.println("Cargo criado com sucesso");
        Main.waiting();
        Main.clear();
    }
    
    public static void update(){
         Main.clear();
        System.out.println("Para você escolher o ID, observe a lista a seguir");
        Main.waiting();
        Main.waiting();
        listAll();
        System.out.println("Digite o id do cargo a ser editado");
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
        for (int i = 0; i < CargoFDAO.findAll().size(); i++) {
            if (id == CargoFDAO.findAll().get(i).getId()) {
                System.out.println("Nome atual: " + CargoFDAO.findById(id).getNome());
            }
        }
        System.out.println("Digite o novo nome do cargo " + id);
        nome = in.nextLine();
        if (CargoBusiness.update(id, nome)) {
            Main.clear();
            System.out.println("\nNome alterado com sucesso");
            Main.waiting();
        } else {
            Main.clear();
            System.out.println("\nErro ao alterar o nome: ID informada é inexistente");
            Main.waiting();
        }
    }
    
    public static List<CargoF> findAll(){
        return CargoFDAO.findAll();
    }
    
    public static CargoF findById(int id){
        return CargoFDAO.findById(id);
    }
    
     public static void listAll() {
        Main.clear();
        if (CargoBusiness.findAll().isEmpty()) {
            System.out.println("NÃO EXISTEM CARGOS CADASTRADOS EM NOSSO SISTEMA");
            Main.waiting();
        } else {
            Main.clear();
            for (int i = 0; i < CargoBusiness.findAll().size(); i++) {
                System.out.println("\n==============================");
                System.out.println("ID: " + CargoBusiness.findAll().get(i).getId());
                System.out.println("Nome: " + CargoBusiness.findAll().get(i).getNome());
                System.out.println("==============================\n");
            }
            System.out.println("Pressione enter para continuar");
            in.nextLine();
            Main.clear();
        }
    }
}
