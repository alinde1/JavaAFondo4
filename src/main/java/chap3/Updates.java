package chap3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Updates {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {

            // Obtener múltiples filas
            String drv = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:15432/postgres";
            String usr = "postgres";
            String pwd = "1234";

            Class.forName(drv);
            con = DriverManager.getConnection(url, usr, pwd);

            // Insertar una fila

            /*int idCat = 9;
            String descripcion = "Nueva categoría";

            String sql = "";
            sql += "INSERT INTO categoria (id_categoria, descripcion) ";
            sql += "VALUES (?,?) ";

            pstm = con.prepareStatement(sql);
            pstm.setInt(1, idCat);
            pstm.setString(2, descripcion);

            int rtdo = pstm.executeUpdate();

            if (rtdo == 1) {
                System.out.println("La fila se insertó correctamente");
            } else {
                System.out.println("Ocurrió un error");
            }*/

            // Valores autoincrementales

            /*System.out.println();

            sql = "";
            sql += "INSERT INTO categoria (descripcion) VALUES (?) ";

            pstm = con.prepareStatement(
                    sql,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            pstm.setString(1, descripcion);

            rtdo = pstm.executeUpdate();

            if ( rtdo == 1 ) {
                System.out.println("La fila se insertó correctamente" );
            } else {
                System.out.println("Ocurrió un error");
            }

            rs = pstm.getGeneratedKeys();
            if ( rs.next() ) {
                int id = rs.getInt(1);
                System.out.println("id="+id);
            }*/

            // Modificar una fila

            int idCat = 1;
            String descripcion = "Descripción modificada";

            String sql = "";
            sql += "UPDATE categoria SET descripcion=? ";
            sql += "WHERE id_categoria = ? ";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, descripcion);
            pstm.setInt(2,idCat);

            int rtdo = pstm.executeUpdate();
            System.out.println(rtdo + " filas modificadas.");

            // Borrar una fila

            System.out.println();

            idCat = 99;

            sql = "";
            sql += "DELETE FROM categoria WHERE id_categoria = ? ";
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, idCat);

            rtdo = pstm.executeUpdate();
            System.out.println(rtdo + " filas borradas.");

            // Transacciones

            System.out.println();

            con.setAutoCommit(false);

            System.out.println("Ejecutando la transacción");

            con.commit();


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
