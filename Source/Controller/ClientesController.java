package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientesController {

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
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Criar Novo Cliente");

        // Inputs
        TextField nomeField = new TextField();
        TextField cpfField = new TextField();
        TextField enderecoField = new TextField();

        Label nomeLabel = new Label("Nome:");
        Label cpfLabel = new Label("CPF:");
        Label enderecoLabel = new Label("Endereço:");

         // Estilize os elementos conforme necessário
        nomeLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12;");
        cpfLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12;");
        enderecoLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12;");

        Button salvarButton = new Button("Salvar");
        salvarButton.setOnAction(e -> {
            // Aqui você pode acessar os valores dos campos de entrada (inputs)
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String endereco = enderecoField.getText();

            // Feche o modal
            dialog.close();
        }); 
        VBox modalLayout = new VBox(10);
        modalLayout.setPadding(new Insets(10));
        modalLayout.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        modalLayout.getChildren().addAll(
            nomeLabel, nomeField,
            cpfLabel, cpfField,
            enderecoLabel, enderecoField,
            salvarButton
        );

        Scene dialogScene = new Scene(modalLayout, 400, 300);
        dialog.setScene(dialogScene);
        dialog.showAndWait(); // Mostra o modal e aguarda até que seja fechado
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
