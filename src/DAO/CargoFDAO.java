package DAO;

import Model.CargoF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CargoFDAO {

    private static ConnectionManager mngr = ConnectionManager.getInstance();

    public static CargoF findById(int id) {
        String sql = "SELECT * FROM cargo WHERE ID=?";
        CargoF cargo = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                cargo = new CargoF();
                cargo.setId(rs.getInt("id"));
                cargo.setNome(rs.getString("nome"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return cargo;
    }

    public static List<CargoF> findAll() {
        String sql = "SELECT * FROM cargo";
        List<CargoF> cargos = new ArrayList<CargoF>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                CargoF cargo = new CargoF();
                cargo.setId(rs.getInt("id"));
                cargo.setNome(rs.getString("nome"));
                cargos.add(cargo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }

        return cargos;
    }

    public static void create(CargoF cargo) {
        String sql = "INSERT INTO cargo(nome) VALUES (?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = mngr.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cargo.getNome());

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                cargo.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
    
    public static void update(CargoF cargo) {
        String sql = "UPDATE cargo SET nome=? WHERE id=?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = mngr.getConnection();
            stmt = conn.prepareCall(sql);
            stmt.setString(1, cargo.getNome());
            stmt.setInt(2, cargo.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
            JDBCUtil.close(stmt);
        }
    }
}
