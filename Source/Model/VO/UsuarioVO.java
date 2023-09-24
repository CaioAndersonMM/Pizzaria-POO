package Model.VO;

public class UsuarioVO{
	private String nome;
    private String cpf;
	private String senha;
	//private Long idUsu;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public UsuarioVO() {
		
	}
}
