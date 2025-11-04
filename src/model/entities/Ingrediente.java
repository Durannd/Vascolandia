package model.entities;

public class Ingrediente {
	private Long idIngrediente;
	private String nome;
	private Long quantidade;
	private Long idFornecedor;
	public Long getIdIngrediente() {
		return idIngrediente;
	}
	public void setIdIngrediente(Long idIngrediente) {
		this.idIngrediente = idIngrediente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	@Override
	public String toString() {
		return "Ingrediente [idIngrediente=" + idIngrediente + ", nome=" + nome + ", quantidade=" + quantidade
				+ ", idFornecedor=" + idFornecedor + "]";
	}
	
	
}
