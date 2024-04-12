package chap4;

import chap3.Categoria;
import chap3.Producto;

import java.util.List;

public interface Facade {
    List<Categoria> obtenerCategorias();
    List<Producto> obtenerProductos(int idCat);
}
