package chap6;

import java.util.List;

public interface Facade {
    List<Categoria> obtenerCategorias();
    List<Producto> obtenerProductos(int idCat);
}
