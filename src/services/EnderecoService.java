package services;

import dao.EnderecoDao;
import dao.impl.EnderecoDaoImpl;
import exceptions.ApplicationException;
import exceptions.DataAccessException;
import exceptions.ResourceNotFoundException;
import model.dto.EnderecoDTO;
import model.entities.Endereco;

public class EnderecoService {
    private EnderecoDao enderecoDao;

    public EnderecoService() {
        enderecoDao = new EnderecoDaoImpl();
    }

    public Endereco toEntity(EnderecoDTO dto) {
        Endereco e = new Endereco();
        e.setIdEndereco(dto.idEndereco());
        e.setRua(dto.rua());
        e.setNumero(dto.numero());
        e.setCep(dto.cep());
        e.setComplemento(dto.complemento());
        e.setBairro(dto.bairro());
        e.setCidade(dto.cidade());
        e.setEstado(dto.estado());
        return e;
    }

    public EnderecoDTO toDTO(Endereco e) {
        return new EnderecoDTO(
                e.getIdEndereco(),
                e.getRua(),
                e.getNumero(),
                e.getCep(),
                e.getComplemento(),
                e.getBairro(),
                e.getCidade(),
                e.getEstado()
        );
    }

    public void validateDto(EnderecoDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Endereço inválido");
        if (dto.rua() == null || dto.rua().isBlank()) throw new IllegalArgumentException("Rua obrigatória");
        if (dto.numero() < 0 && dto.complemento().isBlank()) throw new IllegalArgumentException("Número inválido");
        if (dto.cep() == null || dto.cep().isBlank()) throw new IllegalArgumentException("CEP obrigatório");
        if (dto.bairro() == null || dto.bairro().isBlank()) throw new IllegalArgumentException("Bairro obrigatório");
        if (dto.cidade() == null || dto.cidade().isBlank()) throw new IllegalArgumentException("Cidade obrigatória");
        if (dto.estado() == null || dto.estado().isBlank()) throw new IllegalArgumentException("Estado obrigatório");
    }

    public void createEndereco(EnderecoDTO dto) {
        validateDto(dto);
        Endereco en = toEntity(dto);
        try {
            enderecoDao.create(en);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public EnderecoDTO getEnderecoById(Long id) {
        try {
            Endereco e = enderecoDao.findById(id);
            return toDTO(e);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public void updateEndereco(EnderecoDTO dto) {
        validateDto(dto);
        Endereco en = toEntity(dto);
        try {
            enderecoDao.update(en);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public void deleteEndereco(Long id) {
        try {
            enderecoDao.delete(id);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }


}
