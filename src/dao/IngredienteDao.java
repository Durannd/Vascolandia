package dao;

import model.entities.Ingrediente;

import java.util.List;

public interface IngredienteDao {
    void create(Ingrediente ingrediente);
    void update(Ingrediente ingrediente);
    void delete(Long idIngrediente);
    List<Ingrediente> buscarTodos();
    List<Ingrediente> buscarPorNome(String nome);
}
