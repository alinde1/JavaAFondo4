package chap3;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private int idJefe;

    public Empleado() {}

    public Empleado(int id) { this.idEmpleado=id;}

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdJefe(int idJefe) {
        this.idJefe = idJefe;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", idJefe=" + idJefe +
                '}';
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdJefe() {
        return idJefe;
    }
}
