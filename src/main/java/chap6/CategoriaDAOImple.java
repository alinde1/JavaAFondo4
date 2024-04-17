package chap6;

import java.util.List;

@Component
public class CategoriaDAOImple implements CategoriaDAO {

    public List<Categoria> findAll() {
        return MyHibernate.findAll(Categoria.class);
    }

    public Categoria find(int idCategoria) {
        return MyHibernate.find(Categoria.class, idCategoria);
    }

    public void insert(Categoria categoria) {
        MyHibernate.insert(categoria);
    }

    public void update(Categoria categoria) {
        MyHibernate.update(categoria);
    }

    public void delete(int idCategoria) {
        MyHibernate.delete(Categoria.class, idCategoria);
    }
}
