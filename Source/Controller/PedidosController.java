
package Controller;

        import View.HelloFx;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PedidosController {

    @FXML
    private Button adicionar;

    @FXML
    private Button clientes;

    @FXML
    private Button delete;

    @FXML
    private Button delete1;

    @FXML
    private Button delete11;

    @FXML
    private Button delete111;

    @FXML
    private Button delete1111;

    @FXML
    private Button delete11111;

    @FXML
    private Button delete111111;

    @FXML
    private Button delete1111111;

    @FXML
    private Button delete11111111;

    @FXML
    private Button delete111111111;

    @FXML
    private Button delete1111111111;

    @FXML
    private Button delete11111111111;

    @FXML
    private Button edit;

    @FXML
    private Button edit1;

    @FXML
    private Button edit11;

    @FXML
    private Button edit111;

    @FXML
    private Button edit1111;

    @FXML
    private Button edit11111;

    @FXML
    private Button edit111111;

    @FXML
    private Button edit1111111;

    @FXML
    private Button edit11111111;

    @FXML
    private Button edit111111111;

    @FXML
    private Button edit1111111111;

    @FXML
    private Button edit11111111111;

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
    void adicionar(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(HelloFx.class.getResource("dialogs/fazer_pedido_1.fxml"));
        Scene scene = new Scene(root);

        // Cria uma nova janela de diálogo
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Configura como uma janela de diálogo modal
        //dialogStage.initOwner().getScene().getWindow()); // Define a janela pai
        dialogStage.setScene(scene);

        // Define um título para a janela de diálogo (opcional)
        dialogStage.setTitle("Minha Janela de Diálogo");

        // Exibe a janela de diálogo
        dialogStage.showAndWait();
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
    void telaClientes(ActionEvent event) {

    }

    @FXML
    void telaEstoque(ActionEvent event) {

    }

    @FXML
    void telaFuncionarios(ActionEvent event) {

    }

    @FXML
    void telaPedidos(ActionEvent event) {

    }

    @FXML
    void telaPizzas(ActionEvent event) {

    }

    @FXML
    void telaRelatorios(ActionEvent event) {

    }

}
