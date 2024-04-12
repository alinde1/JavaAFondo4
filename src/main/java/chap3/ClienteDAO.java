package chap3;

import java.util.List;

public interface ClienteDAO {

    public Cliente find(int idCliente);

    public List<Cliente> findByTipoCliente(int idTipoCliente);

    public void insert(Cliente cli);

    public void update(Cliente cli);

    public void delete(int idCliente);

}
