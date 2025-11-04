package dao;

import model.entities.Fornecedor;

import java.util.List;

public interface FornecedorDao {
    void create(Fornecedor fornecedor);
    void update(Fornecedor fornecedor);
    void delete(Long idFornecedor);
    List<Fornecedor> buscarTodos();
    Fornecedor buscarPorAlimento(String alimento);
    Fornecedor buscarPorCNPJ(String cnpj);
}
