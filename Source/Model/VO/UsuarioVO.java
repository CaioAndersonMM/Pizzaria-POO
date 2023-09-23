package Model.VO;

public class UsuarioVO{
    private String cpf;
	private String senha;
	private Long idUsu;

    public Long getIdUsu() {
		return idUsu;
	}

	public void setIdUsu(Long idUsu) {
		this.idUsu = idUsu;
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
