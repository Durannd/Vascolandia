package dao;

public interface EnderecoDao {
    void create(EnderecoDao endereco);
    void update(EnderecoDao endereco);
    void delete(Long idEndereco);
    void findById(Long idEndereco);

}
