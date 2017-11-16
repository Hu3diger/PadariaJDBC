package Business;

import DAO.ProdutoDAO;
import Model.Fornecedor;
import Model.Produto;
import java.util.List;

public class ProdutoBusiness {

    public static Produto create(String nome, double preco, String descricao, Fornecedor fornecedor, int qtd, boolean estado) {
        Produto produto = new Produto(nome, preco, fornecedor, descricao, qtd, estado);
        ProdutoDAO.create(produto);
        return produto;
    }

    public static boolean delete(int id, boolean estado) {
        if (ProdutoDAO.findById(id) != null) {
            ProdutoBusiness.updateEstado(id, estado);
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateNome(int id, String nome) {
        for (Produto produtos : ProdutoDAO.findAll()) {
            if (produtos.getId() == id) {
                produtos.setNome(nome);
                ProdutoDAO.update(produtos);
                return true;
            }
        }
        return false;
    }

    public static boolean updatePreco(int id, double preco) {
        for (Produto produtos : ProdutoDAO.findAll()) {
            if (produtos.getId() == id) {
                produtos.setPreco(preco);
                ProdutoDAO.update(produtos);
                return true;
            }
        }
        return false;
    }

    public static boolean updateDesc(int id, String descricao) {
        for (Produto produtos : ProdutoDAO.findAll()) {
            if (produtos.getId() == id) {
                produtos.setDescricao(descricao);
                ProdutoDAO.update(produtos);
                return true;
            }
        }
        return false;
    }

    public static boolean updateQtd(int id, int qtd) {
        for (Produto produtos : ProdutoDAO.findAll()) {
            if (produtos.getId() == id) {
                produtos.setQuantidade(produtos.getQuantidade() + qtd);
                ProdutoDAO.update(produtos);
                return true;
            }
        }
        return false;
    }
    
        public static boolean updateMenosQtd(int id, int qtd) {
        for (Produto produtos : ProdutoDAO.findAll()) {
            if (produtos.getId() == id) {
                produtos.setQuantidade(produtos.getQuantidade() - qtd);
                ProdutoDAO.update(produtos);
                return true;
            }
        }
        return false;
    }

    public static boolean updateFornecedor(int id, Fornecedor fornecedor) {
        for (Produto produtos : ProdutoDAO.findAll()) {
            if (produtos.getId() == id) {
                produtos.setFornecedor(fornecedor);
                ProdutoDAO.update(produtos);
                return true;
            }
        }
        return false;
    }

    public static boolean updateEstado(int id, boolean estado) {
        for (Produto produtos : ProdutoDAO.findAll()) {
            if (produtos.getId() == id) {
                produtos.setEstado(estado);
                ProdutoDAO.update(produtos);
                return true;
            }
        }
        return false;
    }


    public static List<Produto> findAll() {
        return ProdutoDAO.findAll();
    }

    public static Produto findbyId(int id) {
        return ProdutoDAO.findById(id);
    }
}
