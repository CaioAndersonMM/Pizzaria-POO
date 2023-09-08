package Model.Entity;

public class Gerente extends Usuario {

    public Gerente() {
    }

    // construtor
    public Gerente(String cpf, String senha) {
        setCPF(cpf);
        setSenha(senha);
    }

    public void criarGerente(String cpf, String senha) {
        System.out.println("Adicionado Novo Usuário");
    }

    public void removerGerente(Gerente usuario) {
        System.out.println("Usuário Removido");
    }

    public boolean verificarLogin(Gerente usuario) {
        // ...
        return true;
    }

    public void buscar(String nomeusuario) {
        // Chamara método de Usuário
    }

}
