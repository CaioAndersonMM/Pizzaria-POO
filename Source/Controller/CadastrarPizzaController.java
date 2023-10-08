package Controller;

import Model.BO.TipoPizzaBo;
import Model.Entity.TipoPizza;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarPizzaController {

    @FXML
    private Button adicionar;

    @FXML
    private Button cancel;

    @FXML
    private TextField precog;

    @FXML
    private TextField precom;

    @FXML
    private TextField precop;

    @FXML
    private TextField sabor;


    @FXML
    void adicionar(ActionEvent event) throws Exception {
        TipoPizza tipopizza = new TipoPizza();
        tipopizza.setNomeSabor(sabor.getText());
        float valores[] = {
            Float.parseFloat(precog.getText()), 
            Float.parseFloat(precom.getText()), 
            Float.parseFloat(precop.getText())
        };

        tipopizza.setValores(valores);

        TipoPizzaBo bo = new TipoPizzaBo();
        bo.criar(tipopizza);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        App.telaPizzas();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
