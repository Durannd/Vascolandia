package dao;

import model.entities.Cliente;

import java.util.List;

public interface ClienteDao {
    Cliente procurarPorCPF(String cpf);
    List<Cliente> procurarPorNome(String nome);
    void inserir(Cliente cliente);
    void atualizar(Cliente cliente);
    void deletarPorId(Long id);
    List<Cliente> buscarTodos();
}
