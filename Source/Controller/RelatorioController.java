package Controller;

import Model.BO.ClienteBo;
import Model.BO.ProdutoBo;
import Model.Entity.Cliente;
import Model.Entity.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class RelatorioController implements Initializable {

    @FXML
    private TextField NomeCliente;

    @FXML
    private Button cancel;

    @FXML
    private TextField dataFinal;

    @FXML
    private TextField dataInicial;

    @FXML
    private Button pdf;

    @FXML
    private TextField saborPizza;

    @FXML
    private CheckBox status;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public static List<Cliente> filtrados;

    @FXML
    void gerarPdf(ActionEvent event) {
        System.out.println("GERAR PDF");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
