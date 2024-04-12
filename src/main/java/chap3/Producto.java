package chap3;

public class Producto {
    private int idProducto;
    private String descripcion;
    private int idProveedor;
    private int idCategoria;
    private float precioUnitario;
    private int unidadesStock;
    private int unidadesReposicion;
    private int flgDiscontinuo;

    public Producto() {}

    public Producto(int idProducto) {this.idProducto =idProducto;}

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", descripcion='" + descripcion + '\'' +
                ", idProveedor=" + idProveedor +
                ", idCategoria=" + idCategoria +
                ", precioUnitario=" + precioUnitario +
                ", unidadesStock=" + unidadesStock +
                ", unidadesReposicion=" + unidadesReposicion +
                ", flgDiscontinuo=" + flgDiscontinuo +
                '}';
    }

    public int getIdProducto() {
        return idProducto;
    }
    public int getIdProveedor() {
        return idProveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public int getUnidadesStock() {
        return unidadesStock;
    }

    public int getUnidadesReposicion() {
        return unidadesReposicion;
    }

    public int getFlgDiscontinuo() {
        return flgDiscontinuo;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setUnidadesStock(int unidadesStock) {
        this.unidadesStock = unidadesStock;
    }

    public void setUnidadesReposicion(int unidadesReposicion) {
        this.unidadesReposicion = unidadesReposicion;
    }

    public void setFlgDiscontinuo(int flgDiscontinuo) {
        this.flgDiscontinuo = flgDiscontinuo;
    }
}
