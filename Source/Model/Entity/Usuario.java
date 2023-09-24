package Model.Entity;

public abstract class Usuario {
    private Long id;
    private String cpf;
    private String nome;
    private String senha;

    public Usuario() {
    }

    public Usuario(String cpf, String nome, String senha) {
        setCPF(cpf);
        setNome(nome);
        setSenha(senha);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        if (id > 0)
            this.id = id;
        //lse
            //throw new Exception();
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        if (cpf != null && cpf.length() == 11) {
            this.cpf = cpf;
        } else {
            //
        }
    }

    public void setNome(String nome) {
        if (nome != null && nome.length() > 2) {
            this.nome = nome;
        } else {
            //
        }
    }

    public String getNome() {
        return this.nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && senha.length() > 3) {
            this.senha = senha;
        } else {
            //
        }
    }

    public void criarUsuario(String cpf, String nome, String senha) {}

    public void removerUsuario(Usuario usuario) {}

    public boolean verificarLogin(Usuario usuario) {
        // ...
        return true;
    }

    public void alterarSenha(String novasenha) {
        setSenha(novasenha);
    }

    public void alterarCPF(String novocpf) {
        setCPF(novocpf);
    }

    public void alterarNome(String novonome) {
        setNome(novonome);
    }

    public void buscar(String nomeusuario) {

    }
}