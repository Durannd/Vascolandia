package dao;

import model.entities.Prato;

import java.util.List;

public interface PratoDao {
    void create(Prato prato);
    void update(Prato prato);
    void delete(Long idPrato);
    Prato buscarPorId(Long idPrato);
    List<Prato> buscarTodos();

}
