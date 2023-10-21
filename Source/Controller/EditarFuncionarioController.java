package Controller;

import Model.BO.FuncionarioBo;
import Model.Entity.Funcionario;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarFuncionarioController {
    @FXML
    private Button adicionar;

    @FXML
    private Button salvar;

    @FXML
    private TextField nome;

    @FXML
    private TextField senha;

    @FXML
    private TextField cpf;

    public Long id;

    public void setValoresEdicao(Long id, String nomefuncionario, String cpffuncionario, String senhafuncionario) {
        this.id = id;
        nome.setText(nomefuncionario);
        senha.setText(senhafuncionario);
        cpf.setText(cpffuncionario);
    }

    @FXML
    void salvar(ActionEvent event) throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setCPF(cpf.getText());
        funcionario.setNome(nome.getText());
        funcionario.setSenha(senha.getText());
        funcionario.setId(id);

        FuncionarioBo bo = new FuncionarioBo();
        bo.editar(id, funcionario);

        // Obter o Stage do modal e fechá-lo.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        //Melhor chamar a tela denovo para recarregar!
        App.telaFuncionarios();
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
