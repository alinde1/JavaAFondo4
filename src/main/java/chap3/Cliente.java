package chap3;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String direccion;
    private TipoCliente tipoCliente;

    public Cliente() {}

    public Cliente(int id) {this.idCliente=id;}

    public boolean equals(Object o) {
        return ((Cliente) o).idCliente==this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", TipoCliente=" + tipoCliente +
                '}';
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
}
