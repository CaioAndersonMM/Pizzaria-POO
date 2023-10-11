package Controller;
import Model.BO.FuncionarioBo;
import Model.Entity.Funcionario;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdicionarFuncionarioController {
    @FXML
    private Button adicionar;

    @FXML
    private Button cancel;

    @FXML
    private TextField cpf;

    @FXML
    private TextField nome;

    @FXML
    private PasswordField senha;

    @FXML
    void adicionar(ActionEvent event) throws Exception {
        System.out.println("INDO ADICIONAR FUNCIONARIO");
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome.getText());
        funcionario.setCPF(cpf.getText());
        funcionario.setSenha(senha.getText());

        FuncionarioBo bo = new FuncionarioBo();
        bo.criar(funcionario);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        App.telaFuncionarios();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
