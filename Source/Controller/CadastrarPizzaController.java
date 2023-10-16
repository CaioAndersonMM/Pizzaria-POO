package Controller;

import java.util.ArrayList;
import java.util.List;

import Dao.ProdutoDao;
import Model.BO.TipoPizzaBo;
import Model.Entity.Produto;
import Model.Entity.TipoPizza;
import Utils.ProdutoStringConverter;
import View.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<Produto> ingredientesField;

    @FXML
    private ComboBox<Produto> ingredientesField2;



  public void initialize() {
         ObservableList<Produto> produtopizza = FXCollections.observableArrayList();
        
        ProdutoDao produtodao = new ProdutoDao();
        for (Produto produto: produtodao.listar()) {
            produtopizza.add(produto);
        }

        ingredientesField.setItems(produtopizza);
        ingredientesField.setConverter(new ProdutoStringConverter());
        ingredientesField.setEditable(false);

        ingredientesField2.setItems(produtopizza);
        ingredientesField2.setConverter(new ProdutoStringConverter());
        ingredientesField2.setEditable(false);


    }


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

        List<Produto> ingredientes = new ArrayList<>();

        Produto ingrediente1 = ingredientesField.getValue();
        Produto ingrediente2 = ingredientesField2.getValue();

        ingredientes.add(ingrediente1);
        ingredientes.add(ingrediente2);

        tipopizza.setIngredientes(ingredientes);


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
