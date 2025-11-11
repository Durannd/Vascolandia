package dao.impl;

import dao.EnderecoDao;
import db.DB;
import exceptions.DataAccessException;
import exceptions.ResourceNotFoundException;
import model.entities.Endereco;

import java.sql.*;

public class EnderecoDaoImpl implements EnderecoDao {
    @Override
    public void create(Endereco endereco) {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("insert into endereco (rua, numero, cidade, estado, cep, complemento, bairro) values (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, endereco.getRua());
            stmt.setInt(2, endereco.getNumero());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getEstado());
            stmt.setString(5, endereco.getCep());
            stmt.setString(6, endereco.getComplemento());
            stmt.setString(7, endereco.getBairro());
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    Long id = rs.getLong(1);
                    endereco.setIdEndereco(id);
                }
                rs.close();
            }else{
                throw new ResourceNotFoundException();
            }
            stmt.close();
        } catch (SQLException e) {
            throw new DataAccessException( e.getMessage());
        }
    }

    @Override
    public void update(Endereco endereco) {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("update endereco set rua = ?, numero = ?, cidade = ?, estado = ?, cep = ?, complemento = ?, bairro = ? where id_endereco = ?");
            stmt.setString(1, endereco.getRua());
            stmt.setInt(2, endereco.getNumero());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getEstado());
            stmt.setString(5, endereco.getCep());
            stmt.setString(6, endereco.getComplemento());
            stmt.setString(7, endereco.getBairro());
            stmt.setLong(8, endereco.getIdEndereco());
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected == 0){
                throw new ResourceNotFoundException();
            }
            stmt.close();
        } catch (SQLException e) {
            throw new DataAccessException( e.getMessage());
        }
    }

    @Override
    public void delete(Long idEndereco) {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("delete from endereco where id_endereco = ?");
            stmt.setLong(1, idEndereco);
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected == 0){
                throw new ResourceNotFoundException();
            }
            stmt.close();
        } catch (SQLException e) {
            throw new DataAccessException( e.getMessage());
        }
    }

    @Override
    public Endereco findById(Long idEndereco) {
        try(Connection conn = DB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement("select * from endereco where id_endereco = ?");
            stmt.setLong(1, idEndereco);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(rs.getLong("id_endereco"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCep(rs.getString("cep"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setBairro(rs.getString("bairro"));
                stmt.close();
                rs.close();
                return endereco;
            }else{
                throw new ResourceNotFoundException();
            }
        } catch (SQLException e) {
            throw new DataAccessException( e.getMessage());
        }
    }
}
