package model.dto;

public record IngredienteDTO(Long idIngrediente,
                             String nome,
                             Long quantidade,
                             Long idFornecedor) {
}
