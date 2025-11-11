package services;

import dao.ClienteDao;
import dao.impl.ClienteDaoImpl;
import exceptions.ApplicationException;
import exceptions.DataAccessException;
import exceptions.ResourceNotFoundException;
import model.dto.ClienteDTO;
import model.entities.Cliente;

import java.util.List;

public class ClienteService {
    private ClienteDao clienteDao;

    public ClienteService() {
        this.clienteDao = new ClienteDaoImpl();
    }

    private ClienteDTO toDto(Cliente c) {
        ClienteDTO dto = new ClienteDTO(
                c.getIdCliente(),
                c.getNome(),
                c.getCpf(),
                c.getEmail(),
                c.getTelefone(),
                c.getIdEndereco()
        );
        return dto;
    }

    private Cliente toEntity(ClienteDTO dto) {
        Cliente c = new Cliente();
        c.setIdCliente(dto.idCliente());
        c.setNome(dto.nome());
        c.setCpf(dto.cpf());
        c.setEmail(dto.email());
        c.setTelefone(dto.telefone());
        c.setIdEndereco(dto.idEndereco());
        return c;
    }

    private void validateDto(ClienteDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Cliente inválido");
        if (dto.nome() == null || dto.nome().isBlank()) throw new IllegalArgumentException("Nome obrigatório");
        if (dto.cpf() == null || dto.cpf().isBlank()) throw new IllegalArgumentException("CPF obrigatório");
        if (dto.email() == null || dto.email().isBlank()) throw new IllegalArgumentException("Email obrigatório");
        if (dto.telefone() == null || dto.telefone().isBlank()) throw new IllegalArgumentException("Telefone obrigatório");
    }

    public void createCliente(ClienteDTO dto) {
        validateDto(dto);
        Cliente c = toEntity(dto);
        try{
            clienteDao.inserir(c);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao criar cliente: " + e.getMessage(), e);
        }
    }

    public void updateCliente(ClienteDTO dto) {
        validateDto(dto);
        Cliente c = toEntity(dto);
        try{
            clienteDao.atualizar(c);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    public void deleteCliente(Long idCliente) {
        try{
            clienteDao.deletarPorId(idCliente);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao deletar cliente: " + e.getMessage(), e);
        }
    }

    public ClienteDTO buscarPorId(String cpf) {
        try{
            Cliente c = clienteDao.procurarPorCPF(cpf);
            return toDto(c);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao buscar cliente: " + e.getMessage(), e);
        }
    }

    public List<ClienteDTO> buscarTodos(){
        try{
            List<Cliente> clientes = clienteDao.buscarTodos();
            return clientes
                    .stream()
                    .map(this::toDto)
                    .toList();
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao buscar clientes: " + e.getMessage(), e);
        }
    }
}
