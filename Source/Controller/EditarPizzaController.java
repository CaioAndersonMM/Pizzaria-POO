package Controller;
import javafx.scene.Node; // Importe a classe Node do pacote correto

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

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void editar(ActionEvent event) throws Exception {
        //System.out.println(id);
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
