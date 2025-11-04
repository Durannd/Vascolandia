package dao;

import model.entities.Funcionario;

import java.util.List;

public interface FuncionarioDao {
    void create(Funcionario funcionario);
    void update(Funcionario funcionario);
    void delete(Long idFuncionario);
    Funcionario buscarPorCPF(String cpf);
    List<Funcionario> buscarTodos();
}
