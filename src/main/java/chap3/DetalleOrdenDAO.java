package chap3;

public interface DetalleOrdenDAO {

    DetalleOrden find(int idDetalleOrden);

    void insert(DetalleOrden detalleOrden);

    void update(DetalleOrden detalleOrden);

    void delete(int idDetalleOrden);

}
