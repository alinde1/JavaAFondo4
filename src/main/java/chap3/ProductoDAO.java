package chap3;

import java.util.List;

public interface ProductoDAO {

    public Producto find(int idProducto);

    public List<Producto> findProductos(int idCat);

    public void insert(Producto producto);

    public void update(Producto producto);

    public void delete(int idProducto);

}
