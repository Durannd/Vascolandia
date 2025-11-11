package model.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Pedido {
	private Long idPedido;
	private Timestamp data_hora;
	private float valor_total;
	private Long idCliente;
	private String status;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Timestamp getData_hora() {
		return data_hora;
	}

	public void setData_hora(Timestamp data_hora) {
		this.data_hora = data_hora;
	}

	public float getValor_total() {
		return valor_total;
	}

	public void setValor_total(float valor_total) {
		this.valor_total = valor_total;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", data_hora=" + data_hora + ", valor_total=" + valor_total
				+ ", idCliente=" + idCliente + ", status=" + status + "]";
	}

}
