package dao;

import model.entities.Pedido;

import java.util.List;

public interface PedidoDao {
    void create(Pedido pedido);
    void update(Pedido pedido);
    void delete(Long idPedido);
    Pedido buscarPorId(Long idPedido);
    List<Pedido> buscarTodos();
    List<Pedido> buscarTodosPorCpfCliente(Long idCliente);
}
