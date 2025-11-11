package model.dto;

public record FuncionarioDTO(Long idFuncionario, String cargo, float salario, String nome, String cpf, String email,
                             String telefone, Long idEndereco) {
}
