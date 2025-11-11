package dao;

import model.entities.Endereco;

public interface EnderecoDao {
    void create(Endereco endereco);
    void update(Endereco endereco);
    void delete(Long idEndereco);
    Endereco findById(Long idEndereco);

}
