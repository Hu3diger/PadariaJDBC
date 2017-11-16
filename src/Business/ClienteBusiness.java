package Business;

import DAO.ClienteDAO;
import Model.Cliente;
import java.util.List;

public class ClienteBusiness {

    public static boolean create(String nome, String cpf, boolean estado) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEstado(estado);
        if (verifyCpf(cliente)) {
            ClienteDAO.create(cliente);
            return true;
        }
        return false;
    }

    public static boolean updateEstado(int id, boolean estado) {
        for (Cliente clientes : ClienteDAO.findAll()) {
            if (clientes.getId() == id) {
                clientes.setEstado(estado);
                ClienteDAO.update(clientes);
                return true;
            }
        }
        return false;
    }
    
    public static List<Cliente> findAll(){
        return ClienteDAO.findAll();
    }
    
    public static Cliente findById(int id){
        return ClienteDAO.findById(id);
    }

    public static boolean delete(int id, boolean estado) {
        if (ClienteDAO.findById(id) != null) {
            ClienteBusiness.updateEstado(id, estado);
            return true;
        } else {
            return false;
        }
    }
    public static boolean updateNome(int id, String nome) {
        for (Cliente clientes : ClienteDAO.findAll()) {
            if (clientes.getId() == id) {
                clientes.setNome(nome);
                ClienteDAO.update(clientes);
                return true;
            }
        }
        return false;
    }

    public static boolean verifyCpf(Cliente cliente) {
        for (Cliente clientes : ClienteDAO.findAll()) {
            if (clientes.getCpf().equals(cliente.getCpf())) {
                return false;
            }
        }
        return true;
    }
}
