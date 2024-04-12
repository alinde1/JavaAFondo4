package chap3;

import java.util.List;

public interface ProductoDAO {

    Producto find(int idProducto);

    List<Producto> findProductos(int idCat);

    void insert(Producto producto);

    void update(Producto producto);

    void delete(int idProducto);

}
