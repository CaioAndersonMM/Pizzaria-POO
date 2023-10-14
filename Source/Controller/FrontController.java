package Controller;

import Exception.AutenticationEmptyException;
import Exception.AutenticationException;
import Model.BO.FuncionarioBo;
import Model.Entity.Funcionario;
import Model.Entity.Gerente;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FrontController {
    @FXML private TextField cpf;
    @FXML private Label erroautenticacao;
    @FXML private PasswordField senha;

    public void autenticar(ActionEvent event) throws AutenticationEmptyException, Exception{
        String cpfText = cpf.getText();
        String passwordText = senha.getText();

        if (cpfText.equals("123") && passwordText.equals("123")) {
            System.out.println("Seu Usuário é: "+ cpf.getText() + " e sua senha: "+ senha.getText());
            FuncionarioBo.isAdminLogado = true; //Dando permissão de ADM
            App.telaPedidos();
        }

        if((cpfText != null && !cpfText.isEmpty()) && (passwordText != null && !passwordText.isEmpty())){
            Gerente vo = new Gerente();
            vo.setCPF(cpfText);
            vo.setSenha(passwordText);
            Funcionario vo2 = new Funcionario();
            vo2.setCPF(cpfText);
            vo2.setSenha(passwordText);

            // CUIDADO ---- AO USAR ENTITY AGORA ESTAMOS VALIDANDO 
            // (SetCPF) - Não existe CPF com menos de 11 caracteres
            // (SetSenha) - Senha maior que 3 caracteres

            try {
                //Gerente foi excluido do DB
                FuncionarioBo funBo = new FuncionarioBo();
                Funcionario autenticado = funBo.autenticar(vo2);
                if (autenticado != null) {
                    App.telaPedidos();
                }
            } catch (AutenticationException e) {
                System.out.println("Recomendo utilizar: CPF: 123 SENHA: 123");
                erroautenticacao.setText("CPF OU SENHA NÃO ENCONTRADOS!");
                erroautenticacao.setVisible(true);
            }
        } else{
            erroautenticacao.setText("É NECESSÁRIO PREENCHER TODOS OS CAMPOS");
            System.out.println("Recomendo utilizar: CPF: 123 SENHA: 123");
            erroautenticacao.setVisible(true);
            new AutenticationEmptyException();
        } 
    }
}
