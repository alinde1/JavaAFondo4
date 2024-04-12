package chap4;

import chap3.Categoria;
import chap3.Producto;

import java.util.List;

public interface Facade {
    public List<Categoria> obtenerCategorias();
    public List<Producto> obtenerProductos(int idCat);
}
