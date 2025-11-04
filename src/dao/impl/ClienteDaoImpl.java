package dao.impl;

import dao.ClienteDao;
import db.DB;
import model.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ClienteDaoImpl implements ClienteDao {
    @Override
    public Cliente procurarPorCPF(String cpf) {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from cliente where cpf = ?");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                return cliente;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Cliente> procurarPorNome(String nome) {
        return List.of();
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
