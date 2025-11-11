package model.dto;

public record FornecedorDTO(Long idFornecedor,
                            String nome,
                            String cnpj,
                            String telefone,
                            Long idEndereco) {
}
