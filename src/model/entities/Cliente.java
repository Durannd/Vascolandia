package model.entities;

public class Cliente extends Pessoa{
	private Long idCliente;


	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}


	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + getNome() + ", cpf=" + getCpf() + ", telefone=" + getTelefone()
				+ ", email=" + getEmail() + ", idEndereco=" + getIdEndereco() + "]";
	}

}
