package chap3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrdenDAOImpleJDBC implements OrdenDAO {

    public Orden find(int idOrden) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";

        sql += "SELECT id_orden ";
        sql += "      ,id_cliente ";
        sql += "      ,id_empleado ";
        sql += "      ,fecha_generada ";
        sql += "      ,fecha_entragada ";
        sql += "FROM orden ";
        sql += "WHERE id_orden = ? ";

        try {

            Connection con = DataAccess.getConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idOrden);

            rs = pstm.executeQuery();

            Orden orden = null;
            if (rs.next()) {
                orden = new Orden();
                orden.setIdOrden(rs.getInt("id_orden"));
                orden.setFechaGenerada(rs.getDate("fecha_generada"));
                orden.setFechaEntregada(rs.getDate("fecha_entregada"));

                int idCliente = rs.getInt("id_cliente");
                int idEmpleado = rs.getInt("id_empleado");

                ClienteDAO dao = DaoUtil.getObject("clienteDAO");
                Cliente cli = dao.find(idCliente);
                orden.setCliente(cli);

                EmpleadoDAO edao = DaoUtil.getObject("empleadoDAO");
                Empleado empleado = edao.find(idEmpleado);
                orden.setEmpleado(empleado);
            }

            return orden;

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

    public void insert(Orden orden) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "INSERT INTO orden (id_cliente ";
        sql += "                  ,id_empleado ";
        sql += "                  ,fecha_generada ";
        sql += "                  ,fecha_entregada) ";
        sql += "VALUES (?,?,?,?) ";

        try {

            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, orden.getCliente().getIdCliente());
            pstm.setInt(2, orden.getEmpleado().getIdEmpleado());
            pstm.setDate(3, orden.getFechaGenerada());
            pstm.setDate(4, orden.getFechaEntregada());

            int rtdo = pstm.executeUpdate();

            if ( rtdo==1 ) {
                ResultSet rs = pstm.getGeneratedKeys();
                if ( rs.next() ) {
                    int id = rs.getInt(1);
                    orden.setIdOrden(id);
                } else {
                    throw new RuntimeException("Error en insert");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void update(Orden orden) {
        PreparedStatement pstm = null;

        String sql = "";
        sql += "UPDATE orden ";
        sql += "SET id_cliente = ? ";
        sql += "   ,id_empleado = ? ";
        sql += "   ,fecha_generada = ? ";
        sql += "   ,fecha_entregada = ? ";
        sql += "WHERE id_orden = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, orden.getCliente().getIdCliente());
            pstm.setInt(2, orden.getEmpleado().getIdEmpleado());
            pstm.setDate(3, orden.getFechaGenerada());
            pstm.setDate(4, orden.getFechaEntregada());
            pstm.setInt(5, orden.getIdOrden());

            int rtdo = pstm.executeUpdate();

            if ( rtdo != 1 ) {
                throw new RuntimeException("Error en update");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void delete(int idOrden) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "DELETE FROM orden WHERE id_orden = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, idOrden);

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