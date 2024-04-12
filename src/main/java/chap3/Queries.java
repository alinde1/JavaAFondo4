package chap3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Queries {

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

            String sql = "";
            sql += "SELECT id_producto, descripcion ";
            sql += "FROM producto ";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            while( rs.next() ) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");

                System.out.println(idProducto + ", " + descripcion);

            }

            // Obtener 1 o ninguna fila

            System.out.println();

            int idProd = 25;

            sql = "";
            sql += "SELECT id_producto, descripcion ";
            sql += "FROM producto ";
            sql += "WHERE id_producto = " + idProd;

            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            if ( rs.next() ) {
                int idProducto = rs.getInt("id_producto");
                String descripcion = rs.getString("descripcion");
                System.out.println(idProducto + ", " + descripcion);
            } else {
                String mssg = "No existe un producto con id = " + idProd;
                System.out.println(mssg);
            }

            // Queries con JOIN

            System.out.println();

            sql = "";
            sql += "SELECT prod.id_producto AS prodId ";
            sql += "      ,prod.descripcion AS prodDesc ";
            sql += "      ,cat.descripcion AS catDesc ";
            sql += "FROM producto prod, categoria cat ";
            sql += "WHERE prod.id_categoria = cat.id_categoria ";

            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            while ( rs.next() ) {
                int prodId = rs.getInt("prodId");
                String prodDesc = rs.getString("prodDesc");
                String catDesc = rs.getString("catDesc");

                System.out.println("Id producto: " + prodId);
                System.out.println("Descrición producto: " + prodDesc);
                System.out.println("Descripción categoría: " + catDesc);
            }

            // Queries con valores retornados por funciones de la base de datos

            System.out.println();

            sql = "";
            sql += "SELECT COUNT(*) AS cantProds FROM producto ";

            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();

            if ( rs.next() ) {
                int cantProds = rs.getInt("cantProds");
                System.out.println("Cantidad de productos: "+ cantProds);
            }

            // Queries parametrizados

            System.out.println();

            sql = "";
            sql += "SELECT prod.id_producto AS prodId ";
            sql += "      ,prod.descripcion AS prodDesc ";
            sql += "      ,cat.descripcion AS catDesc ";
            sql += "FROM producto prod, categoria cat ";
            sql += "WHERE prod.id_categoria = cat.id_categoria ";
            sql += "  AND cat.descripcion LIKE ? ";

            pstm = con.prepareStatement(sql);
            pstm.setString(1, "Compu%");
            rs = pstm.executeQuery();

            while ( rs.next() ) {
                int prodId = rs.getInt("prodId");
                String prodDesc = rs.getString("prodDesc");
                String catDesc = rs.getString("catDesc");

                System.out.println("Id producto: " + prodId);
                System.out.println("Descripción producto: " + prodDesc);
                System.out.println("Descripción categoría: " + catDesc);
            }

            // Queries con fechas

            System.out.println();

            sql = "";
            sql += "SELECT promo.descripcion AS promoDesc ";
            sql += "      ,vige.fecha_fin AS vigeFin ";
            sql += "FROM promocion promo, promocion_vigencia vige ";
            sql += "WHERE promo.id_promocion = vige.id_promocion ";
            sql += "  AND vige.fecha_inicio <= ? ";
            sql += "  AND vige.fecha_fin >= ? ";

            pstm = con.prepareStatement(sql);

            GregorianCalendar gc = new GregorianCalendar();
            gc.set(Calendar.YEAR, 2024);
            gc.set(Calendar.MONTH, 2-1);
            gc.set(Calendar.DAY_OF_MONTH, 5);

            long ts = gc.getTimeInMillis();
            Date d = new Date(ts);

            pstm.setDate(1, d);
            pstm.setDate(2, d);

            rs = pstm.executeQuery();

            while ( rs.next() ) {
                String promoDesc = rs.getString("promoDesc");
                String vigeFin = rs.getString("vigeFin");

                System.out.println("Descripción promoción: " + promoDesc);
                System.out.println("Fin vigencia: " + vigeFin);
            }

            System.out.println();

            sql = "";
            sql += "SELECT promo.descripcion AS promoDes ";
            sql += "      ,vige.fecha_fin AS vigeFin ";
            sql += "FROM promocion AS promo, promocion_vigencia AS vige ";
            sql += "WHERE promo.id_promocion = vige.id_promocion ";
            sql += "  AND vige.fecha_inicio <= ? ";
            sql += "  AND vige.fecha_fin >= ? ";
            pstm = con.prepareStatement(sql);

            ts = System.currentTimeMillis();
            d = new Date(ts);

            pstm.setDate(1, d);
            pstm.setDate(2, d);
            rs = pstm.executeQuery();

            while ( rs.next() ) {
                String procDesc = rs.getString("promoDes");
                String vigeFin = rs.getString("vigeFin");

                System.out.println("Descripción promocion: " + procDesc);
                System.out.println("Fecha fin: " + vigeFin);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch(Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

}
