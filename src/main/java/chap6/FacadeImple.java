package chap6;

import java.util.List;

@Component
public class FacadeImple implements Facade {
    @Autowired
    private CategoriaDAO categoriaDAO;

    @Autowired
    private ProductoDAO productoDAO;

    public List<Categoria> obtenerCategorias() {
        return categoriaDAO.findAll();
    }

    public List<Producto> obtenerProductos(int idCat) {
        return productoDAO.findProductos(idCat);
    }

}
