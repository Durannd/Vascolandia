package dao.impl;

import dao.PedidoDao;
import exceptions.DataAccessException;
import model.entities.Pedido;
import model.entities.Prato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    public void update(Pedido pedido, List<Prato> pratos) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE pedido SET data_hora = ?, valor_total = ?, status = ?, idCliente = ? " +
                    "WHERE idPedido = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, pedido.getData_hora());
            stmt.setDouble(2, pedido.getValor_total());
            stmt.setString(3, pedido.getStatus());
            stmt.setLong(4, pedido.getIdCliente());
            stmt.setLong(5, pedido.getIdPedido());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {

                PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM pedido_prato WHERE idPedido = ?");
                deleteStmt.setLong(1, pedido.getIdPedido());
                deleteStmt.executeUpdate();


                for(Prato prato : pratos) {
                    PreparedStatement stmtItem = conn.prepareStatement("INSERT INTO pedido_prato (idPedido, idPrato, quantidade) " +
                            "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                    stmtItem.setLong(1, pedido.getIdPedido());
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
    public void delete(Long idPedido) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM pedido WHERE idPedido = ?");
            stmt.setLong(1, idPedido);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Pedido buscarPorId(Long idPedido) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pedido WHERE idPedido = ?");
            stmt.setLong(1, idPedido);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getLong("idPedido"));
                pedido.setData_hora(rs.getTimestamp("data_hora"));
                pedido.setValor_total((float) rs.getDouble("valor_total"));
                pedido.setStatus(rs.getString("status"));
                pedido.setIdCliente(rs.getLong("idCliente"));
                return pedido;
            } else {
                throw new exceptions.ResourceNotFoundException();
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarTodos() {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pedido");
            ResultSet rs = stmt.executeQuery();
            List<Pedido> pedidos = new ArrayList<>();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getLong("idPedido"));
                pedido.setData_hora(rs.getTimestamp("data_hora"));
                pedido.setValor_total((float) rs.getDouble("valor_total"));
                pedido.setStatus(rs.getString("status"));
                pedido.setIdCliente(rs.getLong("idCliente"));
                pedidos.add(pedido);
            }
            if(pedidos.isEmpty()) {
                throw new exceptions.ResourceNotFoundException();
            }
            return pedidos;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Pedido> buscarTodosPorCpfCliente(Long cpf) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT p.* FROM pedido p " +
                    "JOIN cliente c ON p.idCliente = c.idCliente " +
                    "WHERE c.cpf = ?");
            stmt.setLong(1, cpf);
            ResultSet rs = stmt.executeQuery();
            List<Pedido> pedidos = new ArrayList<>();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getLong("idPedido"));
                pedido.setData_hora(rs.getTimestamp("data_hora"));
                pedido.setValor_total((float) rs.getDouble("valor_total"));
                pedido.setStatus(rs.getString("status"));
                pedido.setIdCliente(rs.getLong("idCliente"));
                pedidos.add(pedido);
            }
            if(pedidos.isEmpty()) {
                throw new exceptions.ResourceNotFoundException();
            }
            return pedidos;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
