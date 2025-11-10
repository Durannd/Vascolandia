package dao.impl;

import dao.PedidoDao;
import exceptions.DataAccessException;
import model.entities.Pedido;
import model.entities.Prato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PedidoDaoImpl implements PedidoDao {
    @Override
    public void create(Pedido pedido, List<Prato> pratos) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO pedido (data_hora, valor_total, status, idCliente) " +
                    "VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, pedido.getData_hora());
            stmt.setDouble(2, pedido.getValor_total());
            stmt.setString(3, pedido.getStatus());
            stmt.setLong(4, pedido.getIdCliente());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                for(Prato prato : pratos) {
                    Long idPedido = rs.getLong(1);

                    PreparedStatement stmtItem = conn.prepareStatement("INSERT INTO pedido_prato (idPedido, idPrato, quantidade) " +
                            "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                    stmtItem.setLong(1, idPedido);
                    stmtItem.setLong(2, prato.getIdPrato());
                    stmtItem.setInt(3,1 );
                    stmtItem.executeUpdate();
                    ResultSet rsItem = stmtItem.getGeneratedKeys();
                    if (!rsItem.next()) {
                        throw new exceptions.ResourceNotFoundException();
                    }
                }

            }

        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void update(Pedido pedido) {

    }

    @Override
    public void delete(Long idPedido) {

    }

    @Override
    public Pedido buscarPorId(Long idPedido) {
        return null;
    }

    @Override
    public List<Pedido> buscarTodos() {
        return List.of();
    }

    @Override
    public List<Pedido> buscarTodosPorCpfCliente(Long idCliente) {
        return List.of();
    }
}
