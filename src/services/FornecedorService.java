package services;

import dao.FornecedorDao;
import dao.impl.FornecedorDaoImpl;
import exceptions.ApplicationException;
import exceptions.DataAccessException;
import exceptions.ResourceNotFoundException;
import model.dto.FornecedorDTO;
import model.entities.Fornecedor;

public class FornecedorService {

    private FornecedorDao fornecedorDao;

    public FornecedorService() {
        fornecedorDao = new FornecedorDaoImpl();
    }

    private FornecedorDTO toDto (Fornecedor f){
        return new FornecedorDTO(
                f.getIdFornecedor(),
                f.getNome(),
                f.getCnpj(),
                f.getTelefone(),
                f.getIdEndereco()
        );
    }
    private Fornecedor toEntity (FornecedorDTO dto){
        Fornecedor f = new Fornecedor();
        f.setIdFornecedor(dto.idFornecedor());
        f.setNome(dto.nome());
        f.setCnpj(dto.cnpj());
        f.setTelefone(dto.telefone());
        f.setIdEndereco(dto.idEndereco());
        return f;
    }

    private void validateFornecedorDTO(FornecedorDTO dto) {
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new ApplicationException("Nome do fornecedor é obrigatório.");
        }
        if (dto.cnpj() == null || dto.cnpj().isEmpty()) {
            throw new ApplicationException("CNPJ do fornecedor é obrigatório.");
        }
        if (dto.telefone() == null || dto.telefone().isEmpty()) {
            throw new ApplicationException("Telefone do fornecedor é obrigatório.");
        }
        if (dto.idEndereco() == null) {
            throw new ApplicationException("ID do endereço do fornecedor é obrigatório.");
        }
    }

    public void createFornecedor(FornecedorDTO fornecedorDTO) {
        validateFornecedorDTO(fornecedorDTO);
        Fornecedor fornecedor = toEntity(fornecedorDTO);
        try{
            fornecedorDao.create(fornecedor);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException();
        }
    }

    public FornecedorDTO procurarPorAlimento(String alimento) {
        try{
            Fornecedor fornecedor = fornecedorDao.buscarPorAlimento(alimento);
            return toDto(fornecedor);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException();
        }
    }

    public void atualizarFornecedor(FornecedorDTO fornecedorDTO) {
        validateFornecedorDTO(fornecedorDTO);
        Fornecedor fornecedor = toEntity(fornecedorDTO);
        try{
            fornecedorDao.update(fornecedor);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException();
        }
    }

    public void deletarFornecedor(Long idFornecedor) {
        try{
            fornecedorDao.delete(idFornecedor);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException();
        }
    }

    public FornecedorDTO procurarPorCNPJ(String cnpj) {
        try{
            Fornecedor fornecedor = fornecedorDao.buscarPorCNPJ(cnpj);
            return toDto(fornecedor);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        } catch (Exception e) {
            throw new ApplicationException();
        }
    }
}
