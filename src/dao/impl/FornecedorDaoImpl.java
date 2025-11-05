package dao.impl;

import dao.FornecedorDao;
import db.DB;
import exceptions.DataAccessException;
import exceptions.ResourceNotFoundException;
import model.entities.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FornecedorDaoImpl implements FornecedorDao {
    @Override
    public void create(Fornecedor fornecedor) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO fornecedor (nome, cnpj, telefone) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCpnj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new DataAccessException();
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void update(Fornecedor fornecedor) {
        try(Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE fornecedor SET nome = ?, cnpj = ?, telefone = ? WHERE id_fornecedor = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCpnj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setLong(4, fornecedor.getIdFornecedor());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new DataAccessException();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Long idFornecedor) {
        try(Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM fornecedor WHERE id_fornecedor = ?");
            stmt.setLong(1, idFornecedor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Fornecedor> buscarTodos() {
        try(Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fornecedor");
            ResultSet rs = stmt.executeQuery();
            List<Fornecedor> fornecedores = new java.util.ArrayList<>();
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(rs.getLong("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCpnj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedores.add(fornecedor);
            }
            if(fornecedores.isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return fornecedores;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Fornecedor buscarPorAlimento(String alimento) {
        try(Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT *" +
                            "FROM fornecedor " +
                            "JOIN ingrediente ON ingrediente.idFornecedor = fornecedor.idFornecedor " +
                            "WHERE ingrediente.nome LIKE ?");
            stmt.setString(1, "%"+alimento+"%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(rs.getLong("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCpnj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                return fornecedor;
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Fornecedor buscarPorCNPJ(String cnpj) {
        try(Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fornecedor WHERE cnpj = ?");
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(rs.getLong("id_fornecedor"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCpnj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                return fornecedor;
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
