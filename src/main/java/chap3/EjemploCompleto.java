package chap3;

import java.util.List;
import java.sql.Date;

public class EjemploCompleto {

    public static void main(String[] args) {

        // comienza la transacción
        Transaction trx = DataAccess.beginTransaction();

        // iserto una orden
        Orden orden = new Orden();
        orden.setCliente(new Cliente(1));
        orden.setEmpleado(new Empleado(2));
        orden.setFechaGenerada(new Date(System.currentTimeMillis()));
        orden.setFechaEntregada(null);

        OrdenDAO ordenDAO = DataAccess.getObject("ordenDAO");
        ordenDAO.insert(orden);

        // inserto un detalle
        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.setOrden(orden);
        detalleOrden.setProducto(new Producto(1));
        detalleOrden.setCantidad(5);

        DetalleOrdenDAO detalleOrdenDAO = DataAccess.getObject("detalleOrdenDAO");
        detalleOrdenDAO.insert(detalleOrden);

        // fin de la transacción
        trx.commit();

        int idCategoria = 2;
        CategoriaDAO cdao = DataAccess.getObject("categoriaDAO");
        Categoria categoria = cdao.find(idCategoria);
        System.out.println(categoria);

        List<Categoria> categorias = cdao.findAll();
        for (Categoria cat: categorias) {
            System.out.println(cat);
        }

        ProductoDAO pdao = DataAccess.getObject("productoDAO");
        List<Producto> productos = pdao.findProductos(idCategoria);
        for (Producto prod: productos) {
            System.out.println(prod);
        }

    }
}
