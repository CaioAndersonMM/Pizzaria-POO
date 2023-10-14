package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import Controller.Pedido.SelecionarClienteController;
import View.App;

public class PedidosController {

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
    void adicionar(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("VE/dialogs/pesquisar_cliente.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Cria uma nova janela de diálogo
        Stage stage = new Stage();
        SelecionarClienteController controller = loader.getController();
        controller.setStage(stage);

        stage.initModality(Modality.APPLICATION_MODAL); // Configura como uma janela de diálogo modal
        stage.setScene(scene);
        stage.setTitle("Minha Janela de Diálogo");
        stage.show();
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
    void telaRelatorios(ActionEvent event) throws Exception {
        App.telaRelatorio();
    }

}
