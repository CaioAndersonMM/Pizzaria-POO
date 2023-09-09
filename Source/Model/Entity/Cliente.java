package Model.Entity;

public class Cliente extends Usuario {
    private Long id;
    private String endereco;
    private String nome;
    private String cpf;

    public Cliente() {
    }

    public Cliente(String cpf, String endereco, String nome) {
        setCPF(cpf);
        setEndereco(endereco);
        setNome(nome);
    }

    public void setEndereco(String endereco) {
        if (endereco != null && endereco.length() > 5) {
            this.endereco = endereco;
        } else {
            //
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return;
        }

        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.length() < 11) {
            return;
        }

        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setId(Long id) {
        if (id == null || id < 1) {
            return;
        }
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    // Esses métodos serão sobrescritos
    public void cadastrarCliente(String cpf, String senha, String nome) {
        System.out.println("Cliente Cadastrado");
    }

    public void removerCliente(Cliente cliente) {
        System.out.println("Cliente (X) removido!");
    }
}
