package chap3;

import java.util.List;

public interface OrdenDAO {

    public Orden find(int idOrden);

    public void insert(Orden orden);

    public void update(Orden orden);

    public void delete(int idOrden);

}
