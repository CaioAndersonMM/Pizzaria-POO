package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarClienteController {

    @FXML
    private Button cancel;

    @FXML
    private TextField cpf;

    @FXML
    private TextField endereco;

    @FXML
    private TextField nome;

    @FXML
    private Button salvar;

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void salvar(ActionEvent event) {

    }

}
