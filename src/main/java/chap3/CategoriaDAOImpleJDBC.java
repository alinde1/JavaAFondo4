package chap3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpleJDBC implements CategoriaDAO {
    public Categoria find(int idCategoria) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";

        sql += "SELECT id_categoria ";
        sql += "      ,descripcion ";
        sql += "FROM categoria ";
        sql += "WHERE id_categoria = ? ";

        try {

            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idCategoria);

            rs = pstm.executeQuery();

            Categoria categoria = null;
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));
            }

            return categoria;

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

    public List<Categoria> findAll() {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT id_categoria FROM categoria ";

        try {
            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql);

            rs = pstm.executeQuery();

            List<Categoria> res = new ArrayList<>();
            while (rs.next()) {
                int idCategoria = rs.getInt("id_categoria");
                Categoria categoria = find(idCategoria);
                res.add(categoria);
            }

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insert(Categoria categoria) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "INSERT INTO categoria (descripcion) ";
        sql += "VALUES (?) ";

        try {

            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, categoria.getDescripcion());

            int rtdo = pstm.executeUpdate();

            if ( rtdo==1 ) {
                ResultSet rs = pstm.getGeneratedKeys();
                if ( rs.next() ) {
                    int id = rs.getInt(1);
                    categoria.setIdCategoria(id);
                } else {
                    throw new RuntimeException("Error en insert");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void update(Categoria categoria) {
        PreparedStatement pstm = null;

        String sql = "";
        sql += "UPDATE categoria ";
        sql += "SET descipcion = ? ";
        sql += "WHERE id_categoria = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, categoria.getDescripcion());
            pstm.setInt(2, categoria.getIdCategoria());

            int rtdo = pstm.executeUpdate();

            if ( rtdo != 1 ) {
                throw new RuntimeException("Error en update");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void delete(int idCategoria) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "DELETE FROM categoria WHERE id_categoria = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, idCategoria);

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