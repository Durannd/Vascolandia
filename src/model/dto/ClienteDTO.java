package model.dto;

public record ClienteDTO(Long idCliente, String nome,
                         String cpf,
                         String email,
                         String telefone,
                         Long idEndereco) {
}
