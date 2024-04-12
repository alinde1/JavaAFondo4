package chap3;

import java.util.List;

public interface CategoriaDAO {

    public Categoria find(int idCategoria);

    public List<Categoria> findAll();

    public void insert(Categoria categoria);

    public void update(Categoria categoria);

    public void delete(int idCategoria);

}
