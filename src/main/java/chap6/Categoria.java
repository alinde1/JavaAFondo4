package chap6;

@Table(name = "categoria")
public class Categoria {
    @Id
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "descripcion")
    private String descripcion;

    public Categoria() {
        super();
    }

    public Categoria(Integer id) {
        Categoria categoria = MyHibernate.find(this.getClass(), id);
        this.idCategoria = categoria.idCategoria;
        this.descripcion = categoria.descripcion;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
