package Model.Entity;

public class Cliente extends Usuario {
    private String endereco;

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

    public void alterarEndereco(String novoendereco) {
        setEndereco(novoendereco);
    }

    // Esses métodos serão sobrescritos
    public void cadastrarCliente(String cpf, String senha, String nome) {
        System.out.println("Cliente Cadastrado");
    }

    public void removerCliente(Cliente cliente) {
        System.out.println("Cliente (X) removido!");
    }
}
