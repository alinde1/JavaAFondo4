package chap3;

public interface EmpleadoDAO {

    Empleado find(int idEmpleado);

    void insert(Empleado empleado);

    void update(Empleado empleado);

    void delete(int idEmpleado);

}
