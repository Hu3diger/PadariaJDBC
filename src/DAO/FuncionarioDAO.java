package DAO;

import Model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private static ConnectionManager mngr = ConnectionManager.getInstance();

    public static Funcionario findById(int id) {
        String sql = "SELECT * FROM funcionario WHERE ID=?";
        Funcionario funcionario = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCargo(CargoFDAO.findById(rs.getInt("cargo")));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setEstado(rs.getBoolean("estado"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return funcionario;
    }

    public static List<Funcionario> findAll() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCargo(CargoFDAO.findById(rs.getInt("cargo")));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setEstado(rs.getBoolean("estado"));
                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return funcionarios;
    }

    public static void update(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome=?, salario=?, cargo=?, estado=? WHERE id=?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareCall(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(3, funcionario.getCargo().getId());
            stmt.setDouble(2, funcionario.getSalario());
            stmt.setBoolean(4, funcionario.isEstado());
            stmt.setInt(5, funcionario.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
            JDBCUtil.close(stmt);
        }
    }

    public static void create(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario(nome, cpf, cargo, salario, estado) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setInt(3, funcionario.getCargo().getId());
            stmt.setDouble(4, funcionario.getSalario());
            stmt.setBoolean(5, funcionario.isEstado());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                funcionario.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
}
