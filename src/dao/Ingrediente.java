package dao;

import java.util.List;

public interface Ingrediente {
    void create(model.entities.Ingrediente ingrediente);
    void update(model.entities.Ingrediente ingrediente);
    void delete(Long idIngrediente);
    List<Ingrediente> buscarTodos();
    List<Ingrediente> buscarPorNome(String nome);
}
