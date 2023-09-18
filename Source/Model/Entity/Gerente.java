package Model.Entity;

public class Gerente extends Usuario {

    public Gerente() {
    }

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

    public void buscar(String nomeusuario) {
    }

}
