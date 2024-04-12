package chap3;

public interface DetalleOrdenDAO {

    public DetalleOrden find(int idDetalleOrden);

    public void insert(DetalleOrden detalleOrden);

    public void update(DetalleOrden detalleOrden);

    public void delete(int idDetalleOrden);

}
