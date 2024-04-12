package chap3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpleadoDAOImpleJDBC implements EmpleadoDAO {

    public Empleado find(int idEmpleado) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";

        sql += "SELECT id_empleado ";
        sql += "      ,nombre ";
        sql += "      ,id_jefe ";
        sql += "FROM empleado ";
        sql += "WHERE id_empleado = ? ";

        try {

            Connection con = DataAccess.getConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idEmpleado);

            rs = pstm.executeQuery();

            Empleado empleado = null;
            if (rs.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setIdJefe(rs.getInt("id_jefe"));
            }

            return empleado;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            }
        }
    }

    public void insert(Empleado empleado) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "INSERT INTO empleado (nombre ";
        sql += "                     ,id_jefe) ";
        sql += "VALUES (?,?) ";

        try {

            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, empleado.getNombre());
            pstm.setInt(2, empleado.getIdJefe());

            int rtdo = pstm.executeUpdate();

            if ( rtdo==1 ) {
                ResultSet rs = pstm.getGeneratedKeys();
                if ( rs.next() ) {
                    int id = rs.getInt(1);
                    empleado.setIdEmpleado(id);
                } else {
                    throw new RuntimeException("Error en insert");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void update(Empleado empleado) {
        PreparedStatement pstm = null;

        String sql = "";
        sql += "UPDATE empleado ";
        sql += "SET nombre = ? ";
        sql += "   ,id_jefe = ? ";
        sql += "WHERE id_empleado = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, empleado.getNombre());
            pstm.setInt(2, empleado.getIdJefe());
            pstm.setInt(3, empleado.getIdEmpleado());

            int rtdo = pstm.executeUpdate();

            if ( rtdo != 1 ) {
                throw new RuntimeException("Error en update");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void delete(int idEmpleado) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "DELETE FROM empleado WHERE id_empleado = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, idEmpleado);

            int rtdo = pstm.executeUpdate();

            if ( rtdo != 1 ) {
                throw new RuntimeException("Error en update");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}