package dao;

import model.entities.Pedido;
import model.entities.Prato;

import java.util.List;

public interface PedidoDao {
    void create(Pedido pedido, List<Prato> pratos);
    void update(Pedido pedido);
    void delete(Long idPedido);
    Pedido buscarPorId(Long idPedido);
    List<Pedido> buscarTodos();
    List<Pedido> buscarTodosPorCpfCliente(Long idCliente);
}
