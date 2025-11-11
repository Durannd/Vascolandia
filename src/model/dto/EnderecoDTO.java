package model.dto;

public record EnderecoDTO(Long idEndereco,
                          String rua,
                          int numero,
                          String cep,
                          String complemento,
                          String bairro,
                          String cidade,
                          String estado) {
}
