package Business;

import DAO.Ped_ProdDAO;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import Model.Ped_Prod;
import Model.Pedido;
import Model.Produto;
import java.util.List;

public class Ped_ProdBusiness {

    private static boolean create(int qtdProduto, Produto produto, Pedido pedido) {
        Ped_Prod pedprod = new Ped_Prod();
        pedprod.setQuantidade(qtdProduto);
        pedprod.setProduto(produto);
        pedprod.setPedido(pedido);
        Ped_ProdDAO.create(pedprod);
        return true;
    }

    private static boolean update(int id_pedido, int id_produto, int qtdProduto) {
        for (Ped_Prod ped_prod : Ped_ProdDAO.findByPedido(id_pedido)) {
            if (ped_prod.getProduto().getId() == id_produto) {
                ped_prod.setQuantidade(qtdProduto + ped_prod.getQuantidade());
                Ped_ProdDAO.update(ped_prod);
                return true;
            }
        }
        Produto produto = null;
        Pedido pedido = null;
        for (Produto produtos : ProdutoDAO.findAll()) {
            if (produtos.getId() == id_produto) {
                produto = ProdutoDAO.findById(id_produto);
            }
        }
        for (Pedido pedidos : PedidoDAO.findAll()) {
            if (pedidos.getId() == id_pedido) {
                pedido = PedidoDAO.findById(id_pedido);
            }
        }
        Ped_ProdBusiness.create(qtdProduto, produto, pedido);
        return true;
    }

    public static boolean setPed_Prod(int id_pedido, int id_produto, int qtdProduto) {
        Pedido pedido = PedidoDAO.findById(id_pedido);
        Produto produto = ProdutoDAO.findById(id_produto);
        if (pedido == null) {
            return false;
        } else if (produto == null) {
            return false;
        }
        Ped_ProdBusiness.update(id_pedido, id_produto, qtdProduto);
        return true;
    }

    public static List<Ped_Prod> findAll() {
        return Ped_ProdDAO.findAll();
    }
    
    public static List<Ped_Prod> findByPedido(int id) {
        return Ped_ProdDAO.findByPedido(id);
    }
    
    public static Ped_Prod findById(int id){
        return Ped_ProdDAO.findById(id);
    }
}
