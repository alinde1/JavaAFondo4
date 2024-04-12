package chap3;

public class Categoria {
    private int idCategoria;
    private String descripcion;

    public Categoria() {}

    public Categoria(int idCategoria) {this.idCategoria = idCategoria;}

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
