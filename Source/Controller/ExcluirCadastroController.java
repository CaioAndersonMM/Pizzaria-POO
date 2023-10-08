package Controller;

import Model.BO.TipoPizzaBo;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ExcluirCadastroController {

    @FXML
    private Button cancel;

    @FXML
    private Button excluir;

    public static Long tipopizza_id;

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void deletar(ActionEvent event) throws Exception {
        if(tipopizza_id != null){
            TipoPizzaBo bo = new TipoPizzaBo();
            bo.deletar(tipopizza_id);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            App.telaPizzas();
        }
    }

}
