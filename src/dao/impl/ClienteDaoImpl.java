package dao.impl;

import dao.ClienteDao;
import db.DB;
import exceptions.DataAccessException;
import exceptions.ResourceNotFoundException;
import model.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao {
    @Override
    public Cliente procurarPorCPF(String cpf) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("select * from cliente where cpf = ?");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return tabelaParaObjetoCliente(rs);
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<Cliente> procurarPorNome(String nome) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("select * from cliente where nome like ?");
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            List<Cliente> clientes = new java.util.ArrayList<>();
            while (rs.next()) {
                clientes.add(tabelaParaObjetoCliente(rs));
            }
            if (clientes.isEmpty()) {
                throw new ResourceNotFoundException();
            } else {
                return clientes;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private Cliente tabelaParaObjetoCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getLong("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        return cliente;
    }

    @Override
    public void inserir(Cliente cliente) {

    }

    @Override
    public void atualizar(Cliente cliente) {

    }

    @Override
    public void deletarPorId(Long id) {

    }

    @Override
    public List<Cliente> buscarTodos() {
        return List.of();
    }
}
