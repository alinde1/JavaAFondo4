package chap6;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Obtenemos el producto cuyo id es 1
        Producto p = MyHibernate.find(Producto.class, 8);

        // Mostramos la descripción
        System.out.println(p.getDescripcion());

        // Mostramos a que categoria pertenece
        Categoria c = p.getCategoria();
        System.out.println(c.getDescripcion());

        List<Producto> lst = MyHibernate.findAll(Producto.class);
        for (Producto prod : lst) {
            System.out.println(prod);
        }

        TipoCliente tp = MyHibernate.find(TipoCliente.class, 1);
        System.out.println(tp);

        System.out.println(MyHibernate.findAll(TipoCliente.class));

        List<Producto> lstProd = MyHibernate.findBy(Producto.class, "descripcion", "S8");
        System.out.println(lstProd);

        lstProd = MyHibernate.findBy(Producto.class, "unidadesStock", 3);
        System.out.println(lstProd);

        System.out.println(MyHibernate.findAll(Proveedor.class));

        Producto p2 = new Producto();
        p2.setDescripcion("nuevo producto");
        p2.setProveedor(new Proveedor(1));
        p2.setCategoria(new Categoria(2));
        p2.setPrecioUnitario(123D);
        p2.setUnidadesStock(40);
        p2.setUnidadesReposicion(0);
        p2.setFlgDiscontinuo(0);

        MyHibernate.insert(p2);

        System.out.println(MyHibernate.find(Producto.class, p2.getIdProducto()));
    }

}
