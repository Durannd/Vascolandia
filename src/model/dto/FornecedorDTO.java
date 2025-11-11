package model.dto;

public record FornecedorDTO(Long idFornecedor,
                            String nome,
                            String cpnj,
                            String telefone,
                            Long idEndereco) {
}
