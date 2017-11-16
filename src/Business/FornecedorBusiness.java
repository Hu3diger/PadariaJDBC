package Business;

import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import Model.Fornecedor;
import Model.Produto;
import java.util.List;

public class FornecedorBusiness {

    public static boolean create(String nome, String cnpj) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        fornecedor.setCnpj(cnpj);
        if (verifyCnpj(fornecedor)) {
            FornecedorDAO.create(fornecedor);
            return true;
        }
        return false;
    }

    public static boolean delete(int id) {
        if (FornecedorDAO.findById(id) != null) {
            FornecedorDAO.delete(id);
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateNome(int id, String nome) {
        for (Fornecedor fornecedors : FornecedorDAO.findAll()) {
            if (fornecedors.getId() == id) {
                fornecedors.setNome(nome);
                FornecedorDAO.update(fornecedors);
                return true;
            }
        }
        return false;
    }

    public static boolean verifyCnpj(Fornecedor fornecedor) {
        for (Fornecedor fornecedores : FornecedorDAO.findAll()) {
            if (fornecedores.getCnpj().equals(fornecedor.getCnpj())) {
                return false;
            }
        }
        return true;
    }

    public static List<Fornecedor> findAll() {
        return FornecedorDAO.findAll();
    }

    public static Fornecedor findbyId(int id) {
        return FornecedorDAO.findById(id);
    }
}
