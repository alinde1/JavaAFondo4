package chap4;

import chap3.*;

import java.util.List;

public class FacadeImple implements Facade{
    public List<Categoria> obtenerCategorias() {
        CategoriaDAO dao = DataAccess.getObject("categoriaDAO");
        List<Categoria> categorias = dao.findAll();
        return categorias;
    }

    public List<Producto> obtenerProductos(int idCat) {
        ProductoDAO dao = DataAccess.getObject("productoDAO");
        List<Producto> productos = dao.findProductos(idCat);
        return productos;
    }
}
