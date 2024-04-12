package chap3;

import java.util.List;

public interface ClienteDAO {

    Cliente find(int idCliente);

    List<Cliente> findByTipoCliente(int idTipoCliente);

    void insert(Cliente cli);

    void update(Cliente cli);

    void delete(int idCliente);

}
