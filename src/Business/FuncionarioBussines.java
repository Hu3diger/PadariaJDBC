package Business;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.TelefoneDAO;
import Model.CargoF;
import Model.Cliente;
import Model.Funcionario;
import Model.Telefone;
import java.util.List;

public class FuncionarioBussines {

    public static boolean create(String nome, String cpf, CargoF cargo, double Salario, boolean estado) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setCargo(cargo);
        funcionario.setSalario(Salario);
        funcionario.setEstado(estado);
        if (verifyCpf(funcionario)) {
            FuncionarioDAO.create(funcionario);
            return true;
        }
        return false;
    }

     public static boolean updateEstado(int id, boolean estado) {
        for (Funcionario funcionarios : FuncionarioDAO.findAll()) {
            if (funcionarios.getId() == id) {
                funcionarios.setEstado(estado);
                FuncionarioDAO.update(funcionarios);
                return true;
            }
        }
        return false;
    }

    public static boolean updateNome(int id, String nome) {
        for (Funcionario funcionarios : FuncionarioDAO.findAll()) {
            if (funcionarios.getId() == id) {
                funcionarios.setNome(nome);
                FuncionarioDAO.update(funcionarios);
                return true;
            }
        }
        return false;
    }

    public static boolean updateSalario(int id, double salario) {
        for (Funcionario funcionarios : FuncionarioDAO.findAll()) {
            if (funcionarios.getId() == id) {
                funcionarios.setSalario(salario);
                FuncionarioDAO.update(funcionarios);
                return true;
            }
        }
        return false;
    }

    public static boolean updateCargo(int id, CargoF cargo) {
        for (Funcionario funcionarios : FuncionarioDAO.findAll()) {
            if (funcionarios.getId() == id) {
                funcionarios.setCargo(cargo);
                FuncionarioDAO.update(funcionarios);
                return true;
            }
        }
        return false;
    }

    public static boolean verifyCpf(Funcionario funcionario) {
        for (Funcionario funcionarios : FuncionarioDAO.findAll()) {
            if (funcionarios.getCpf().equals(funcionario.getCpf())) {
                return false;
            }
        }
        return true;
    }
    
    public static List<Funcionario> findAll(){
        return FuncionarioDAO.findAll();
    }
    
    public static Funcionario findById(int id){
        return FuncionarioDAO.findById(id);
    }
}
