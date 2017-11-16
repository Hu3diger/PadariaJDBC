package DAO;

import Model.Ped_Prod;
import Model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Ped_ProdDAO {

    private static ConnectionManager mngr = ConnectionManager.getInstance();
    
    
        public static Ped_Prod findById(int id) {
        String sql = "SELECT * FROM ped_prod WHERE id=?";
        Ped_Prod pedProd = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Ped_Prod ped_prod = new Ped_Prod();
                ped_prod.setId(rs.getInt("id"));
                ped_prod.setQuantidade(rs.getInt("qtdProduto"));
                ped_prod.setPedido(PedidoDAO.findById(rs.getInt("id_pedido")));
                ped_prod.setProduto(ProdutoDAO.findById(rs.getInt("id_produto")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return pedProd;
    }
    
    public static List<Ped_Prod> findByPedido(int id) {
        String sql = "SELECT * FROM ped_prod WHERE id_pedido=?";
        List<Ped_Prod> produtos = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Ped_Prod ped_prod = new Ped_Prod();
                ped_prod.setId(rs.getInt("id"));
                ped_prod.setQuantidade(rs.getInt("qtdProduto"));
                ped_prod.setProduto(ProdutoDAO.findById(rs.getInt("id_produto")));
                ped_prod.setPedido(new Pedido(rs.getInt("id_pedido")));
                produtos.add(ped_prod);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return produtos;
    }
    
    public static List<Ped_Prod> findByProduto(int id) {
        String sql = "SELECT * FROM ped_prod WHERE id_produto=?";
        List<Ped_Prod> produtos = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Ped_Prod ped_prod = new Ped_Prod();
                ped_prod.setId(rs.getInt("id"));
                ped_prod.setQuantidade(rs.getInt("qtdProduto"));
                ped_prod.setPedido(PedidoDAO.findById(rs.getInt("id_pedido")));
                ped_prod.setProduto(ProdutoDAO.findById(rs.getInt("id_produto")));
                produtos.add(ped_prod);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return produtos;
    }
    
    public static List<Ped_Prod> findAll() {
        String sql = "SELECT * FROM ped_prod";
        List<Ped_Prod> produtos = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Ped_Prod ped_prod = new Ped_Prod();
                ped_prod.setId(rs.getInt("id"));
                ped_prod.setQuantidade(rs.getInt("qtdProduto"));
                ped_prod.setPedido(PedidoDAO.findById(rs.getInt("id_pedido")));
                ped_prod.setProduto(ProdutoDAO.findById(rs.getInt("id_produto")));
                produtos.add(ped_prod);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return produtos;
    }
    
    public static void update(Ped_Prod ped_prod) {
        String sql = "UPDATE ped_prod SET qtdProduto=? WHERE id=?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareCall(sql);
            
            stmt.setDouble(1, ped_prod.getQuantidade());
            stmt.setInt(2, ped_prod.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
            JDBCUtil.close(stmt);
        }
    }

    public static void create(Ped_Prod ped_prod) {
        String sql = "INSERT INTO ped_prod(qtdProduto, id_Pedido, id_Produto) VALUES (?,?,?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, ped_prod.getQuantidade());
            stmt.setInt(2, ped_prod.getPedido().getId());
            stmt.setInt(3, ped_prod.getProduto().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                ped_prod.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
}
