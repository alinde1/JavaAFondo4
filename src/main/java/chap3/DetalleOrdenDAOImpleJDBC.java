package chap3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DetalleOrdenDAOImpleJDBC implements DetalleOrdenDAO {

    public DetalleOrden find(int idDetalleOrden) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";

        sql += "SELECT id_detalle_orden ";
        sql += "      ,id_orden ";
        sql += "      ,id_producto ";
        sql += "      ,cantidad ";
        sql += "FROM detalle_orden ";
        sql += "WHERE id_detalle_orden = ? ";

        try {

            Connection con = DataAccess.getConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idDetalleOrden);

            rs = pstm.executeQuery();

            DetalleOrden detalleOrden = null;
            if (rs.next()) {
                detalleOrden = new DetalleOrden();
                detalleOrden.setIdDetalleOrden(rs.getInt("id_detalle_orden"));
                detalleOrden.setCantidad(rs.getInt("cantidad"));

                int idOrden = rs.getInt("id_orden");
                int idProducto = rs.getInt("id_producto");

                OrdenDAO dao = DaoUtil.getObject("ordenDAO");
                Orden orden = dao.find(idOrden);
                detalleOrden.setOrden(orden);

                ProductoDAO pdao = DaoUtil.getObject("productoDAO");
                Producto producto = pdao.find(idProducto);
                detalleOrden.setProducto(producto);
            }

            return detalleOrden;

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

    public void insert(DetalleOrden detalleOrden) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "INSERT INTO detalle_orden (id_orden ";
        sql += "                          ,id_producto ";
        sql += "                          ,cantidad) ";
        sql += "VALUES (?,?,?) ";

        try {

            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, detalleOrden.getIdOrden().getIdOrden());
            pstm.setInt(2, detalleOrden.getProducto().getIdProducto());
            pstm.setInt(3, detalleOrden.getCantidad());

            int rtdo = pstm.executeUpdate();

            if ( rtdo==1 ) {
                ResultSet rs = pstm.getGeneratedKeys();
                if ( rs.next() ) {
                    int id = rs.getInt(1);
                    detalleOrden.setIdDetalleOrden(id);
                } else {
                    throw new RuntimeException("Error en insert");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void update(DetalleOrden detalleOrden) {
        PreparedStatement pstm = null;

        String sql = "";
        sql += "UPDATE detalle_orden ";
        sql += "SET id_orden = ? ";
        sql += "   ,id_producto = ? ";
        sql += "   ,cantidad = ? ";
        sql += "WHERE id_detalle_orden = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, detalleOrden.getIdOrden().getIdOrden());
            pstm.setInt(2, detalleOrden.getProducto().getIdProducto());
            pstm.setInt(3, detalleOrden.getCantidad());
            pstm.setInt(5, detalleOrden.getIdDetalleOrden());

            int rtdo = pstm.executeUpdate();

            if ( rtdo != 1 ) {
                throw new RuntimeException("Error en update");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void delete(int idDetalleOrden) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "DELETE FROM detalle_orden WHERE id_detalle_orden = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, idDetalleOrden);

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