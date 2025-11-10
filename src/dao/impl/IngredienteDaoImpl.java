package dao.impl;

import dao.IngredienteDao;
import exceptions.DataAccessException;
import model.entities.Ingrediente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class IngredienteDaoImpl implements IngredienteDao {
    @Override
    public void create(model.entities.Ingrediente ingrediente) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO ingrediente (nome, qtd, idFornecedor) " +
                    "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ingrediente.getNome());
            stmt.setDouble(2, ingrediente.getQuantidade());
            stmt.setLong(3, ingrediente.getIdFornecedor());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new exceptions.ResourceNotFoundException();
            }

        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void update(model.entities.Ingrediente ingrediente) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE ingrediente SET nome = ?, qtd = ?, idFornecedor = ? " +
                    "WHERE idIngrediente = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ingrediente.getNome());
            stmt.setDouble(2, ingrediente.getQuantidade());
            stmt.setLong(3, ingrediente.getIdFornecedor());
            stmt.setLong(4, ingrediente.getIdIngrediente());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new exceptions.ResourceNotFoundException();
            }

        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Long idIngrediente) {

        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM ingrediente WHERE idIngrediente = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, idIngrediente);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new exceptions.ResourceNotFoundException();
            }

        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }

    }

    @Override
    public List<Ingrediente> buscarTodos() {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ingrediente");
            ResultSet rs = stmt.executeQuery();
            List<Ingrediente> ingredientes = new java.util.ArrayList<>();
            while (rs.next()) {
                model.entities.Ingrediente ingrediente = new model.entities.Ingrediente();
                ingrediente.setIdIngrediente(rs.getLong("idIngrediente"));
                ingrediente.setNome(rs.getString("nome"));
                ingrediente.setQuantidade(rs.getLong("qtd"));
                ingrediente.setIdFornecedor(rs.getLong("idFornecedor"));
                ingredientes.add(ingrediente);
            }
            if(ingredientes.isEmpty()) {
                throw new exceptions.ResourceNotFoundException();
            }
            return ingredientes;

        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Ingrediente> buscarPorNome(String nome) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ingrediente WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            List<Ingrediente> ingredientes = new java.util.ArrayList<>();
            while (rs.next()) {
                model.entities.Ingrediente ingrediente = new model.entities.Ingrediente();
                ingrediente.setIdIngrediente(rs.getLong("idIngrediente"));
                ingrediente.setNome(rs.getString("nome"));
                ingrediente.setQuantidade(rs.getLong("qtd"));
                ingrediente.setIdFornecedor(rs.getLong("idFornecedor"));
                ingredientes.add(ingrediente);
            }
            if(ingredientes.isEmpty()) {
                throw new exceptions.ResourceNotFoundException();
            }
            return ingredientes;

        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
