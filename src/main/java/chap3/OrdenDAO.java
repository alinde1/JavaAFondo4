package chap3;

public interface OrdenDAO {

    Orden find(int idOrden);

    void insert(Orden orden);

    void update(Orden orden);

    void delete(int idOrden);

}
