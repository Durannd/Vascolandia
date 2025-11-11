package model.dto;

import java.sql.Timestamp;

public record PedidoDTO(Long idPedido,
                        Timestamp data_hora,
                        float valor_total,
                        Long idCliente,
                        String status) {
}
