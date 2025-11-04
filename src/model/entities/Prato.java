package model.entities;

public class Prato {
	private Long idPrato;
	private String descricao;
	private String categoria;
	private float preco;
	private boolean disponivel;

	public Long getIdPrato() {
		return idPrato;
	}

	public void setIdPrato(Long idPrato) {
		this.idPrato = idPrato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "Prato [idPrato=" + idPrato + ", descricao=" + descricao + ", categoria=" + categoria + ", preco="
				+ preco + ", disponivel=" + disponivel + "]";
	}

}
