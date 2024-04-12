package chap6;

@Table(name="producto")
public class Producto {
    @Id
    @Column(name="id_producto")
    private Integer idProducto;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="precio_unitario")
    private Double precioUnitario;

    @ManyToOne
    @Column(name="id_categoria")
    private Categoria categoria;

    @ManyToOne
    @Column(name="id_proveedor")
    private Proveedor proveedor;

    @Column(name="unidades_stock")
    private Integer unidadesStock;

    @Column(name="unidades_reposicion")
    private int unidadesReposicion;

    @Column(name="flg_discontinuo")
    private int flgDiscontinuo;

    public Integer getUnidadesStock() {
        return unidadesStock;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setUnidadesStock(Integer unidadesStock) {
        this.unidadesStock = unidadesStock;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getUnidadesReposicion() {
        return unidadesReposicion;
    }

    public void setUnidadesReposicion(int unidadesReposicion) {
        this.unidadesReposicion = unidadesReposicion;
    }

    public int getFlgDiscontinuo() {
        return flgDiscontinuo;
    }

    public void setFlgDiscontinuo(int flgDiscontinuo) {
        this.flgDiscontinuo = flgDiscontinuo;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", descripcion='" + descripcion + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", categoria=" + categoria +
                ", proveedor=" + proveedor +
                ", unidadesStock=" + unidadesStock +
                ", unidadesReposicion=" + unidadesReposicion +
                ", flgDiscontinuo=" + flgDiscontinuo +
                '}';
    }
}
