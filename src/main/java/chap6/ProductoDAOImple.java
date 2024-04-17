package chap6;

import java.util.List;

@Component
public class ProductoDAOImple implements ProductoDAO{

    public List<Producto> findProductos(int idCat) {
        return MyHibernate.findBy(Producto.class, "idProducto", idCat);
    }

    public Producto find(int idProducto) {
        return MyHibernate.find(Producto.class, idProducto);
    }

    public void insert(Producto producto) {
        MyHibernate.insert(producto);
    }

    public void update(Producto producto) {
        MyHibernate.update(producto);
    }

    public void delete(int idProducto) {
        MyHibernate.delete(Producto.class, idProducto);
    }
}
