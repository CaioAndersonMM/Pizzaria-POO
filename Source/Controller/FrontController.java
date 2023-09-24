package Controller;

import Exception.AutenticationEmptyException;
import Exception.AutenticationException;
import Model.BO.FuncionarioBo;
import Model.BO.GerenteBo;
import Model.Entity.Funcionario;
import Model.Entity.Gerente;
import Model.VO.FuncionarioVO;
import Model.VO.GerenteVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FrontController {
    @FXML private TextField cpf;
    @FXML private Label erroautenticacao;
    @FXML private PasswordField senha;

    //private static UsuarioInterBO<UsuarioVO> usuBO = new UsuarioBO<UsuarioVO>();

    public void autenticar(ActionEvent event) throws AutenticationEmptyException{
        String cpfText = cpf.getText();
        String passwordText = senha.getText();

        if((cpfText != null && !cpfText.isEmpty()) && (passwordText != null && !passwordText.isEmpty())){
            Gerente vo = new Gerente();
            vo.setCPF(cpfText);
            vo.setSenha(passwordText);
            Funcionario vo2 = new Funcionario();
            vo2.setCPF(cpfText);
            vo2.setSenha(passwordText);

            //AO USAR ENTITY AGORA ESTOU VALIDANDO O CPF 
            // (SetCPF) não existe CPF com menos de 11 caracteres

            try {
                GerenteBo gerbo = new GerenteBo();
                System.out.println("Entrou");
                Gerente autenticado = gerbo.autenticar(vo);
                if (autenticado != null) {
                    //Abrir Janela do Gerente
                    System.out.println("GERENTE AUTENTICADO");
                } 
            } catch (AutenticationException e) {
                System.out.println("catch gerente");
                //verificar Funcionário
                    try {
                    FuncionarioBo funBo = new FuncionarioBo();
                    Funcionario autenticado2 = funBo.autenticar(vo2);
                     if (autenticado2 != null) {
                        System.out.println("Funcionário AUTENTICADO");
                        //abrir janela do Funcionario
                    } else throw new AutenticationException();
                    } catch (AutenticationException e2) {
                        erroautenticacao.setText("CPF OU SENHA NÃO ENCONTRADOS!");
                        erroautenticacao.setVisible(true);
                    }
            }
        } else{
            erroautenticacao.setText("É NECESSÁRIO PREENCHER TODOS OS CAMPOS");
            erroautenticacao.setVisible(true);
            new AutenticationEmptyException();
        } 
    }
}
