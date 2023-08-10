Class Funcionario{
    private final String cpf;
    private String senha;
    private boolean isAdmin;

    public Funcionario(){}
    //construtor
    public Funcionario(String cpf, String senha, boolean isAdmin) {
        setCPF(cpf);
        setSenha(senha);
        setAdmin(isAdmin);
    }

    public void setCPF(String cpf){
        if (cpf != null && cpf.length() == 11) {
            this.cpf = cpf;
        } else {
            //
        }
    }
    
    public String getCPF() {
        return cpf;
    }

    public void setSenha(String senha){
        if (senha != null && senha.length() == 8) {
            this.senha = senha;
        } else {
            //
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    //get
    public boolean isAdmin() {
        return isAdmin;
    }

    public void criarUsuario(String cpf, String senha){
        System.out.printl("Adicionado Novo Usuário");
    }
    public void removerUsuario(Usuario usuario){
        System.out.printl("Usuário Removido");
    }
    public boolean verificarLogin(Usuario usuario){
        //...
        return true;
    }
    public void alterarSenha(String novasenha){
        setSenha(novasenha);
    }
    public void buscar(String nomeusuario){

    }


}
