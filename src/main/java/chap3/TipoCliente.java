package chap3;

public class TipoCliente {
    private int idTipoCliente;
    private String descripcion;

    public TipoCliente() {}

    public TipoCliente(int idTipoCliente) {this.idTipoCliente =idTipoCliente;}

    @Override
    public String toString() {
        return "TipoCliente{" +
                "idTipoCliente=" + idTipoCliente +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
