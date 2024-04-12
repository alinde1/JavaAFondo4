package chap3;

import java.sql.Date;

public class Orden {
    private int idOrden;
    private Cliente Cliente;
    private Empleado empleado;
    private Date fechaGenerada;
    private Date fechaEntregada;

    public Orden() {}

    public Orden(int idOrden) {
        this.idOrden = idOrden;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "idOrden=" + idOrden +
                ", Cliente=" + Cliente +
                ", Empleado=" + empleado +
                ", fechaGenerada=" + fechaGenerada +
                ", fechaEntregada=" + fechaEntregada +
                '}';
    }

    public int getIdOrden() {
        return idOrden;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Date getFechaGenerada() {
        return fechaGenerada;
    }

    public Date getFechaEntregada() {
        return fechaEntregada;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public void setCliente(Cliente cliente) {
        this.Cliente = cliente;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setFechaGenerada(Date fechaGenerada) {
        this.fechaGenerada = fechaGenerada;
    }

    public void setFechaEntregada(Date fechaEntregada) {
        this.fechaEntregada = fechaEntregada;
    }
}
