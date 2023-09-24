package Controller;

import Exception.AutenticationEmptyException;
import Exception.AutenticationException;
import Model.BO.FuncionarioBo;
import Model.BO.GerenteBo;
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
            GerenteVO vo = new GerenteVO();
            vo.setCPF(cpfText);
            vo.setSenha(passwordText);
            FuncionarioVO vo2 = new FuncionarioVO();
            vo2.setCPF(cpfText);
            vo2.setSenha(passwordText);

            try {
                GerenteBo gerbo = new GerenteBo();
                GerenteVO autenticado = gerbo.autenticar(vo);
                if (autenticado != null) {
                    //Abrir Janela do Gerente
                } else{ //Pode ser Funcionario
                    //Abrir Janela do Funcionario
                    FuncionarioBo funBo = new FuncionarioBo();
                    FuncionarioVO autenticado2 = funBo.autenticar(vo2);
                    if (autenticado2 != null) {
                        //abrir janela do Funcionario
                    }
                    else throw new AutenticationException(); //nem o Funcionário foi entrado
                }
            } catch (AutenticationException e) {
                erroautenticacao.setText("CPF OU SENHA NÃO ENCONTRADOS!");
                erroautenticacao.setVisible(true);
            }
        } else{
            erroautenticacao.setText("É NECESSÁRIO PREENCHER TODOS OS CAMPOS");
            erroautenticacao.setVisible(true);
            new AutenticationEmptyException();
        } 

        /*
        try {
            UsuarioVO autenticado = UsuarioBo.autenticar(vo);
            if (autenticado instanceof FuncionarioVO) {
                //Abrir Janela do Funcionário
            } else{//É gerente
                //Abrir Janela do Gerente
            }
        } catch (AutenticationException e) {
            erroautenticacao.setText("CPF OU SENHA NÃO ENCONTRADOS!");
            erroautenticacao.setVisible(true);
        }

         */
    }
}
