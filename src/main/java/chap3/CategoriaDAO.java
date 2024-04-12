package chap3;

import java.util.List;

public interface CategoriaDAO {

    Categoria find(int idCategoria);

    List<Categoria> findAll();

    void insert(Categoria categoria);

    void update(Categoria categoria);

    void delete(int idCategoria);

}
