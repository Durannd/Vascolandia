package dao.impl;

import dao.FuncionarioDao;
import db.DB;
import exceptions.DataAccessException;
import exceptions.ResourceNotFoundException;
import model.entities.Funcionario;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDaoImpl implements FuncionarioDao {
    @Override
    public void create(Funcionario funcionario) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO funcionario (nome, cpf, telefone, salario, cargo, email, idEndereco) VALUES (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setDouble(4, funcionario.getSalario());
            stmt.setString(5, funcionario.getCargo());
            stmt.setString(6, funcionario.getEmail());
            stmt.setLong(7, funcionario.getIdEndereco());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new ResourceNotFoundException();
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void update(Funcionario funcionario) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE funcionario SET nome = ?, cpf = ?, telefone = ?, salario = ?, cargo = ?, email = ?, idEndereco = ? WHERE idFuncionario = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setDouble(4, funcionario.getSalario());
            stmt.setString(5, funcionario.getCargo());
            stmt.setString(6, funcionario.getEmail());
            stmt.setLong(7, funcionario.getIdEndereco());
            stmt.setLong(8, funcionario.getIdFuncionario());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new ResourceNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Long idFuncionario) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM funcionario WHERE idFuncionario = ?");
            stmt.setLong(1, idFuncionario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Funcionario buscarPorCPF(String cpf) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM funcionario WHERE cpf = ?");
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setSalario(rs.getFloat("salario"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setIdEndereco(rs.getLong("idEndereco"));
                return funcionario;
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Funcionario> buscarTodos() {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM funcionario");
            ResultSet rs = stmt.executeQuery();
            List<Funcionario> funcionarios = new java.util.ArrayList<>();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getLong("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setSalario(rs.getFloat("salario"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setIdEndereco(rs.getLong("idEndereco"));
                funcionarios.add(funcionario);
            }
            if (funcionarios.isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return funcionarios;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}