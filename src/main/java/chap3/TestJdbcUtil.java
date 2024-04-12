package chap3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestJdbcUtil {

    public static void main(String[] args) {

        Connection con = JdbcUtil.getConnection();

        Transaction trx = JdbcUtil.beginTransaction();

        try {

            String sql = "";
            sql += "SELECT id_producto, descripcion ";
            sql += "FROM producto ";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ClienteDAO dao = DaoUtil.getObject("clienteDAO");

        int idCliente = 3;
        Cliente cli = dao.find(idCliente);

        System.out.println(cli);

        int idTipoCliente = cli.getTipoCliente().getIdTipoCliente();
        TipoClienteDAO tdao = new TipoClienteDAO();
        TipoCliente tipoCliente = tdao.find(idTipoCliente);
        System.out.println(tipoCliente);

        List<Cliente> clientes = dao.findByTipoCliente(idTipoCliente);

        for (Cliente cliente: clientes) {
            System.out.println(cliente);
        }

        Cliente cli2 = new Cliente();
        cli2.setNombre("Pablo");
        cli2.setDireccion("Av. Del Libertador, 123");
        cli2.setTipoCliente(new TipoCliente(1));

        dao.insert(cli2);

        cli.setNombre("Pepito");
        System.out.println(cli);
        dao.update(cli);

        dao.delete(cli2.getIdCliente());

        trx.commit();

    }

}
