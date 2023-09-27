package Controller;

import java.io.IOException;

import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PizzasController {

    @FXML
    private Button adicionar;

    @FXML
    private Button clientes;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button estoque;

    @FXML
    private Button funcionarios;

    @FXML
    private Button pedidos;

    @FXML
    private Button pizzas;

    @FXML
    private Button relatorios;

    @FXML
    private Button sair;

    @FXML
    void adicionar(ActionEvent event) {
     
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void loggout(ActionEvent event) {

    }

   @FXML
    void telaClientes(ActionEvent event) throws Exception {
        App.telaClientes();
    }

    @FXML
    void telaEstoque(ActionEvent event) throws Exception {
         App.telaEstoque();
    }

    @FXML
    void telaFuncionarios(ActionEvent event) throws Exception {
         App.telaFuncionarios();
    }

    @FXML
    void telaPedidos(ActionEvent event) throws IOException {
        App.telaPedidos();
    }

    @FXML
    void telaPizzas(ActionEvent event) throws Exception {
        App.telaPizzas();
    }

    @FXML
    void telaRelatorios(ActionEvent event) {

    }


}
