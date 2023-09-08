package Model.Entity;

import Model.BO.UsuarioBo;

public class Usuario {
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

    public void setId(Long id) throws Exception {
        if (id > 0)
            this.id = id;
        else
            throw new Exception();
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
        if (senha != null && senha.length() == 8) {
            this.senha = senha;
        } else {
            //
        }
    }

    public void criarUsuario(String cpf, String nome, String senha) {
        // Verificar Funcionamento
        UsuarioBo usubo = new UsuarioBo();
        Usuario usu = new Usuario(cpf, nome, senha);
        usubo.criar(usu);
    }

    public void removerUsuario(Usuario usuario) {
        System.out.println("Usuário Removido");
    }

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