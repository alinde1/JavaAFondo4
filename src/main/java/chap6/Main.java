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
    }

}
