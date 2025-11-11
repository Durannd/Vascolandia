package model.dto;

public record PratoDTO(Long idPrato,
                       String descricao,
                       String categoria,
                       float preco,
                       boolean disponivel) {
}
