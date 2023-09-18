package Model.Entity;

public class Funcionario extends Usuario {

    public Funcionario() {
    }

    public Funcionario(String cpf, String senha) {
        setCPF(cpf);
        setSenha(senha);
    }

    public void criarFuncionario(String cpf, String senha) {
        System.out.println("Adicionado Novo Usuário");
    }

    public void removerFuncionario(Funcionario usuario) {
        System.out.println("Usuário Removido");
    }

    public boolean verificarLogin(Funcionario usuario) {
        // ...
        return true;
    }

    public void buscar(String nomeusuario) {
        // Chamara método de Usuário
    }

}
