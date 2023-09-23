package Controller;

import Exception.AutenticationException;
import Model.BO.FuncionarioBo;
import Model.Entity.Funcionario;
import Model.Entity.Gerente;
import Model.VO.UsuarioVO;
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

    public void autenticar(ActionEvent event){
        UsuarioVO vo = new UsuarioVO();
        vo.setCPF(cpf.getText());
        vo.setSenha(senha.getText());

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
