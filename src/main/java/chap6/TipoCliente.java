package chap6;

@Table(name = "tipo_cliente")
public class TipoCliente {

    @Id
    @Column(name = "id_tipo_cliente")
    private int idTipoCliente;

    @Column(name = "descripcion")
    private String descripcion;

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoCliente{" +
                "idTipoCliente=" + idTipoCliente +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
