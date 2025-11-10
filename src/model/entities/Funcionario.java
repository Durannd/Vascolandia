package model.entities;

public class Funcionario extends Pessoa {
	private Long idFuncionario;
    private String cargo;
	private float salario;


	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
	public String toString() {
		return "Funcionario [idFuncionario=" + idFuncionario + ", nome=" + getNome() + ", salario=" + salario + ", cpf="
				+ getCpf() + ", email=" + getEmail() + ", telefone=" + getTelefone() + ", idEndereco=" + getIdEndereco() + "]";
	}

}
