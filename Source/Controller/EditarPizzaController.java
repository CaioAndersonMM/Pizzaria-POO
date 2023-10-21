package Controller;

import javafx.scene.Node;
import Model.BO.TipoPizzaBo;
import Model.Entity.TipoPizza;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarPizzaController {
    @FXML
    private Button cancel;

    @FXML
    private Button editar;

    @FXML
    private TextField ingredientes;

    @FXML
    private TextField precog;

    @FXML
    private TextField precom;

    @FXML
    private TextField precop;

    @FXML
    private TextField sabor;

    public static Long id;

    
    public void setValoresEdicao(String s_sabor, String s_precop, String s_precom, String s_precog) {
        sabor.setText(s_sabor);
        precop.setText(s_precop);
        precom.setText(s_precom);
        precog.setText(s_precog);
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void editar(ActionEvent event) throws Exception {

        TipoPizza tipopizza = new TipoPizza();
        tipopizza.setNomeSabor(sabor.getText());
        float valores[] = {
            Float.parseFloat(precog.getText()), 
            Float.parseFloat(precom.getText()), 
            Float.parseFloat(precop.getText())
        };

        tipopizza.setValores(valores);

        tipopizza.setId(id);

        TipoPizzaBo bo = new TipoPizzaBo();
        bo.editar(id, tipopizza);

        // Obter o Stage do modal e fechá-lo.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        //Melhor chamar a tela denovo para recarregar!
        App.telaPizzas();

    }

}
