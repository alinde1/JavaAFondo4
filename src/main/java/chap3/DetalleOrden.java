package chap3;

public class DetalleOrden {
    private int idDetalleOrden;
    private Orden orden;
    private Producto producto;
    private int cantidad;

    public DetalleOrden() {}

    public DetalleOrden(int id) {
        this.idDetalleOrden=id;
    }

    @Override
    public String toString() {
        return "DetalleOrden{" +
                "idDetalleOrden=" + idDetalleOrden +
                ", orden=" + orden +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }

    public int getIdDetalleOrden() {
        return idDetalleOrden;
    }

    public Orden getIdOrden() {
        return orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setIdDetalleOrden(int idDetalleOrden) {
        this.idDetalleOrden = idDetalleOrden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
