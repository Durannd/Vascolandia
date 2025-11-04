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
import java.util.ArrayList;
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
            }else{
                throw new ResourceNotFoundException();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Cliente> procurarPorNome(String nome) {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from cliente where nome like ?");
            stmt.setString(1, "%"+nome+"%");
            ResultSet rs = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<>();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                lista.add(cliente);
            }
            if(lista.isEmpty()){
                throw new ResourceNotFoundException();
            }else{
                return lista;
            }
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void inserir(Cliente cliente) {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cliente (nome, cpf, telefone, email) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(!rs.next()){
                throw new DataAccessException();
            }
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage());
        }
        
    }

    @Override
    public void atualizar(Cliente cliente) {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, email = ? WHERE id = ?",  PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setLong(5, cliente.getIdCliente());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(!rs.next()){
                throw new DataAccessException();
            }
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void deletarPorId(Long id) {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Cliente> buscarTodos() {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<>();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                lista.add(cliente);
            }
            if(lista.isEmpty()){
                throw new ResourceNotFoundException();
            }
            return lista;
        }catch (SQLException e){
            throw new DataAccessException(e.getMessage());
        }
    }
}
