package DAO;

import Model.FormaPagamento;
import Model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private static ConnectionManager mngr = ConnectionManager.getInstance();

    public static Pedido findById(int id) {
        String sql = "SELECT * FROM pedido WHERE ID=?";
        Pedido pedido = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setCliente(ClienteDAO.findById(rs.getInt("id_cliente")));
                pedido.setFuncionario(FuncionarioDAO.findById(rs.getInt("id_funcionario")));
                pedido.setDataPedido(rs.getDate("dataPedido"));
                pedido.setFormapagamento(FormaPagamento.valueOf(rs.getString("formaPagamento")));
                pedido.setProdutos(Ped_ProdDAO.findByPedido(pedido.getId()));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return pedido;
    }

    public static List<Pedido> findAll() {
        String sql = "SELECT * FROM pedido";
        List<Pedido> pedidos = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setCliente(ClienteDAO.findById(rs.getInt("id_cliente")));
                pedido.setFuncionario(FuncionarioDAO.findById(rs.getInt("id_funcionario")));
                pedido.setDataPedido(rs.getDate("dataPedido"));
                pedido.setFormapagamento(FormaPagamento.valueOf(rs.getString("formaPagamento")));
                pedido.setProdutos(Ped_ProdDAO.findByPedido(pedido.getId()));
                pedidos.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return pedidos;
    }

    public static Pedido create(Pedido pedido) {
        String sql = "INSERT INTO pedido(dataPedido, id_Cliente, id_Funcionario, formaPagamento) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, pedido.getDataPedido());
            stmt.setInt(2, pedido.getCliente().getId());
            stmt.setInt(3, pedido.getFuncionario().getId());
            stmt.setString(4, pedido.getFormapagamento().name());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                pedido.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return pedido;
    }
}
