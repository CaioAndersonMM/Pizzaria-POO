package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RelatorioController{

    @FXML
    private Button adicionar;

    @FXML
    private Button cancel;

    @FXML
    void adicionar(ActionEvent event) {
        System.out.println("GERAR PDF");
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
