package Business;

import DAO.Ped_ProdDAO;
import DAO.PedidoDAO;
import Model.Cliente;
import Model.FormaPagamento;
import Model.Funcionario;
import Model.Ped_Prod;
import Model.Pedido;
import java.sql.Date;
import java.util.List;

public class PedidoBusiness {

    public static Pedido create(Pedido pedido) {
        pedido = PedidoDAO.create(pedido);
        return pedido;
    }
    
    public static Pedido getPedido(int id){
        Pedido pedido = PedidoDAO.findById(id);
        if(pedido == null){
            return null;
        }
        List<Ped_Prod> produtos = Ped_ProdDAO.findByPedido(id);
        pedido.setProdutos(produtos);
        return pedido;
    }
    
    public static List<Pedido> findAll(){
        return PedidoDAO.findAll();
    }
    
    public static Pedido findById(int id){
        return PedidoDAO.findById(id);
    }
}
