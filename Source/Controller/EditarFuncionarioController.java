package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditarFuncionarioController {
    @FXML
    private Button adicionar;

    @FXML
    private Button salvar;

    @FXML
    private TextField confirm_senha;

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
        confirm_senha.setText(senhafuncionario);
        cpf.setText(cpffuncionario);
    }

    @FXML
    void salvar(ActionEvent event) {
        
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

}
