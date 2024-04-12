package chap3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpleJDBC implements ProductoDAO {

    public Producto find(int idProducto) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";

        sql += "SELECT * ";
        sql += "FROM producto ";
        sql += "WHERE id_producto = ? ";

        try {

            Connection con = DataAccess.getConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idProducto);

            rs = pstm.executeQuery();

            Producto producto = null;
            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setIdProveedor(rs.getInt("id_proveedor"));
                producto.setIdCategoria(rs.getInt("id_categoria"));
                producto.setPrecioUnitario(rs.getFloat("precio_unitario"));
                producto.setUnidadesStock(rs.getInt("unidades_stock"));
                producto.setUnidadesReposicion(rs.getInt("unidades_reposicion"));
                producto.setFlgDiscontinuo(rs.getInt("flg_discontinuo"));
            }

            return producto;

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

    public List<Producto> findProductos(int idCat) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT id_producto FROM producto ";
        sql += "WHERE id_categoria = ? ";

        try {
            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idCat);

            rs = pstm.executeQuery();

            List<Producto> res = new ArrayList<>();
            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                Producto producto = find(idProducto);
                res.add(producto);
            }

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insert(Producto producto) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "INSERT INTO producto (descripcion ";
        sql += "                     ,id_proveedor ";
        sql += "                     ,id_categoria ";
        sql += "                     ,precio_unitario ";
        sql += "                     ,unidades_stock ";
        sql += "                     ,unidades_reposicion ";
        sql += "                     ,fig_discontinuo) ";
        sql += "VALUES (?,?,?,?,?,?,?,?) ";

        try {

            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, producto.getDescripcion());
            pstm.setInt(2, producto.getIdProveedor());
            pstm.setInt(3, producto.getIdCategoria());
            pstm.setFloat(4, producto.getPrecioUnitario());
            pstm.setInt(5, producto.getUnidadesStock());
            pstm.setInt(6, producto.getUnidadesReposicion());
            pstm.setInt(7, producto.getFlgDiscontinuo());

            int rtdo = pstm.executeUpdate();

            if ( rtdo==1 ) {
                ResultSet rs = pstm.getGeneratedKeys();
                if ( rs.next() ) {
                    int id = rs.getInt(1);
                    producto.setIdProducto(id);
                } else {
                    throw new RuntimeException("Error en insert");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void update(Producto producto) {
        PreparedStatement pstm = null;

        String sql = "";
        sql += "UPDATE producto ";
        sql += "SET descripcion = ? ";
        sql += "   ,id_proveedor = ? ";
        sql += "   ,id_categoria = ? ";
        sql += "   ,precio_unitario = ? ";
        sql += "   ,unidades_stock = ? ";
        sql += "   ,unidades_disposicion = ? ";
        sql += "   ,fig_disposicion = ? ";
        sql += "WHERE id_producto = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, producto.getDescripcion());
            pstm.setInt(2, producto.getIdProveedor());
            pstm.setInt(3, producto.getIdCategoria());
            pstm.setFloat(4, producto.getPrecioUnitario());
            pstm.setInt(5, producto.getUnidadesStock());
            pstm.setInt(6, producto.getUnidadesReposicion());
            pstm.setInt(7, producto.getFlgDiscontinuo());
            pstm.setInt(8, producto.getIdProducto());

            int rtdo = pstm.executeUpdate();

            if ( rtdo != 1 ) {
                throw new RuntimeException("Error en update");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void delete(int idProducto) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "DELETE FROM producto WHERE id_producto = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, idProducto);

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