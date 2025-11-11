package dao.impl;

import dao.PratoDao;
import exceptions.DataAccessException;
import exceptions.ResourceNotFoundException;
import model.entities.Prato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PratoDaoImpl implements PratoDao {
    @Override
    public void create(Prato prato) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO prato (categoria, descricao, preco, disponivel) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, prato.getCategoria());
            stmt.setString(2, prato.getDescricao());
            stmt.setDouble(3, prato.getPreco());
            stmt.setBoolean(4, prato.isDisponivel());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new exceptions.DataAccessException();
            }

        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void update(Prato prato) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE prato SET categoria = ?, descricao = ?, preco = ?, disponivel = ? WHERE idPrato = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, prato.getCategoria());
            stmt.setString(2, prato.getDescricao());
            stmt.setDouble(3, prato.getPreco());
            stmt.setBoolean(4, prato.isDisponivel());
            stmt.setLong(5, prato.getIdPrato());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new ResourceNotFoundException();
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(Long idPrato) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM prato WHERE idPrato = ?", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, idPrato);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (!rs.next()) {
                throw new ResourceNotFoundException();
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Prato buscarPorId(Long idPrato) {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM prato WHERE idPrato = ?");
            stmt.setLong(1, idPrato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Prato prato = new Prato();
                prato.setIdPrato(rs.getLong("idPrato"));
                prato.setCategoria(rs.getString("categoria"));
                prato.setDescricao(rs.getString("descricao"));
                prato.setPreco((float) rs.getDouble("preco"));
                prato.setDisponivel(rs.getBoolean("disponivel"));
                return prato;
            } else {
                throw new ResourceNotFoundException();
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Prato> buscarTodos() {
        try(Connection conn = db.DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM prato");
            ResultSet rs = stmt.executeQuery();
            List<Prato> pratos = new java.util.ArrayList<>();
            while (rs.next()) {
                Prato prato = new Prato();
                prato.setIdPrato(rs.getLong("idPrato"));
                prato.setCategoria(rs.getString("categoria"));
                prato.setDescricao(rs.getString("descricao"));
                prato.setPreco((float) rs.getDouble("preco"));
                prato.setDisponivel(rs.getBoolean("disponivel"));
                pratos.add(prato);
            }
            if(pratos.isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return pratos;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
