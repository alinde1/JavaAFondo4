package chap3;

public interface EmpleadoDAO {

    public Empleado find(int idEmpleado);

    public void insert(Empleado empleado);

    public void update(Empleado empleado);

    public void delete(int idEmpleado);

}
