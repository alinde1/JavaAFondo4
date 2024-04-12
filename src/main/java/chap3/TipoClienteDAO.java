package chap3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TipoClienteDAO {
    public TipoCliente find(int idCliente) {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        String sql = "";

        sql += "SELECT id_tipo_cliente ";
        sql += "      ,descripcion ";
        sql += "FROM tipo_cliente ";
        sql += "WHERE id_tipo_cliente = ? ";

        try {

            Connection con = JdbcUtil.getConnection();

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idCliente);

            rs = pstm.executeQuery();

            TipoCliente tipoCliente = null;
            if (rs.next()) {
                tipoCliente = new TipoCliente();
                tipoCliente.setIdTipoCliente(rs.getInt("id_tipo_cliente"));
                tipoCliente.setDescripcion(rs.getString("descripcion"));
            }

            return tipoCliente;

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
}
