package chap3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpleJDBC implements ClienteDAO {
    public Cliente find(int idCliente) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";

        sql += "SELECT id_cliente ";
        sql += "      ,nombre ";
        sql += "      ,direccion ";
        sql += "      ,id_tipo_cliente ";
        sql += "FROM cliente ";
        sql += "WHERE id_cliente = ? ";

        try {

            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idCliente);

            rs = pstm.executeQuery();

            Cliente cli = null;
            if (rs.next()) {
                cli = new Cliente();
                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setDireccion(rs.getString("direccion"));

                int idTipoCliente = rs.getInt("id_tipo_cliente");

                TipoClienteDAO tipoClienteDAO = new TipoClienteDAO();
                TipoCliente tipoCliente = tipoClienteDAO.find(idTipoCliente);
                cli.setTipoCliente(tipoCliente);
            }

            return cli;

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

    public List<Cliente> findByTipoCliente(int idTipoCliente) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT id_cliente FROM cliente ";
        sql += "WHERE id_tipo_cliente = ? ";

        try {
            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idTipoCliente);

            rs = pstm.executeQuery();

            List<Cliente> res = new ArrayList<>();
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                Cliente cli = find(idCliente);
                res.add(cli);
            }

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insert(Cliente cli) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "INSERT INTO cliente (nombre ";
        sql += "                    ,direccion ";
        sql += "                    ,id_tipo_cliente) ";
        sql += "VALUES (?,?,?) ";

        try {

            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            pstm.setString(1, cli.getNombre());
            pstm.setString(2, cli.getDireccion());
            pstm.setInt(3, cli.getTipoCliente().getIdTipoCliente());

            int rtdo = pstm.executeUpdate();

            if ( rtdo==1 ) {
                ResultSet rs = pstm.getGeneratedKeys();
                if ( rs.next() ) {
                    int id = rs.getInt(1);
                    cli.setIdCliente(id);
                } else {
                    throw new RuntimeException("Error en insert");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void update(Cliente cli) {
        PreparedStatement pstm = null;

        String sql = "";
        sql += "UPDATE cliente ";
        sql += "SET nombre = ? ";
        sql += "   ,direccion = ? ";
        sql += "   ,id_tipo_cliente = ? ";
        sql += "WHERE id_cliente = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, cli.getNombre());
            pstm.setString(2, cli.getDireccion());
            pstm.setInt(3, cli.getTipoCliente().getIdTipoCliente());
            pstm.setInt(4, cli.getIdCliente());

            int rtdo = pstm.executeUpdate();

            if ( rtdo != 1 ) {
                throw new RuntimeException("Error en update");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void delete(int idCliente) {
        PreparedStatement pstm = null;

        String sql = "";

        sql += "DELETE FROM cliente WHERE id_cliente = ? ";

        try {

            Connection con = JdbcUtil.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, idCliente);

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