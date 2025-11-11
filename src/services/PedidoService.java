package services;

import dao.PedidoDao;
import dao.impl.PedidoDaoImpl;
import exceptions.ApplicationException;
import exceptions.DataAccessException;
import exceptions.ResourceNotFoundException;
import model.dto.PedidoDTO;
import model.dto.PratoDTO;
import model.entities.Pedido;
import model.entities.Prato;

import java.util.List;

public class PedidoService {
    private final PedidoDao pedidoDao;

    public PedidoService() {
        this.pedidoDao = new PedidoDaoImpl();
    }

    private void validateDto(PedidoDTO dto) {
        if (dto == null) throw new ApplicationException("Pedido inválido");
        if (dto.idCliente() == null) throw new ApplicationException("Cliente obrigatório");
        if (dto.valor_total() < 0) throw new ApplicationException("Valor total inválido");
        if (dto.status() == null || dto.status().isBlank()) throw new ApplicationException("Status obrigatório");
    }

    private Pedido toEntity(PedidoDTO dto) {
        Pedido p = new Pedido();
        p.setIdCliente(dto.idCliente());
        p.setValor_total(dto.valor_total());
        p.setStatus(dto.status());
        p.setData_hora(dto.data_hora());
        p.setIdPedido(dto.idPedido());
        return p;
    }

    private PedidoDTO toDto(Pedido p) {
        return new PedidoDTO(
                p.getIdPedido(),
                p.getData_hora(),
                p.getValor_total(),
                p.getIdCliente(),
                p.getStatus()
        );
    }

    private Prato toEntity(PratoDTO dto) {
        Prato p = new Prato();
        p.setIdPrato(dto.idPrato());
        p.setDisponivel(dto.disponivel());
        p.setDescricao(dto.descricao());
        p.setPreco(dto.preco());
        p.setCategoria(dto.categoria());
        return p;
    }

    public void createPedido(PedidoDTO dto, List<PratoDTO> pratos) {
        validateDto(dto);
        if(pratos == null || pratos.isEmpty()){
            throw new ResourceNotFoundException("Lista de pratos não pode ser vazia");
        }
        Pedido p = toEntity(dto);
        try {
            pedidoDao.create(p, pratos
                    .stream()
                    .map(prato -> toEntity(prato))
                    .toList());
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao criar pedido: " + e.getMessage(), e);
        }
    }

    public void updatePedido(PedidoDTO dto, List<PratoDTO> pratos) {
        validateDto(dto);
        if(pratos == null || pratos.isEmpty()){
            throw new ResourceNotFoundException("Lista de pratos não pode ser vazia");
        }
        Pedido p = toEntity(dto);
        try {
            pedidoDao.update(p, pratos
                    .stream()
                    .map(prato -> toEntity(prato))
                    .toList());
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao atualizar pedido: " + e.getMessage(), e);
        }
    }

    public void deletePedido(Long idPedido){
        try {
            pedidoDao.delete(idPedido);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao deletar pedido: " + e.getMessage(), e);
        }
    }

    public PedidoDTO buscarPorId(Long idPedido){
        try {
            Pedido p = pedidoDao.buscarPorId(idPedido);
            return toDto(p);
        }catch (ResourceNotFoundException e){
            throw e;
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao buscar pedido: " + e.getMessage(), e);
        }
    }

    public List<PedidoDTO> buscarTodos(){
        try {
            List<Pedido> pedidos = pedidoDao.buscarTodos();
            return pedidos
                    .stream()
                    .map(this::toDto)
                    .toList();
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao buscar pedidos: " + e.getMessage(), e);
        }
    }

    public List<PedidoDTO> buscarTodosPorCpfCliente(Long cpf){
        try {
            List<Pedido> pedidos = pedidoDao.buscarTodosPorCpfCliente(cpf);
            return pedidos
                    .stream()
                    .map(this::toDto)
                    .toList();
        }catch (DataAccessException e){
            throw e;
        }catch (Exception e){
            throw new ApplicationException("Erro ao buscar pedidos do cliente: " + e.getMessage(), e);
        }
    }
}
