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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PizzasController {

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
    private Button delete111111111111;

    @FXML
    private Button delete1111111111111;

    @FXML
    private Button delete11111111111111;

    @FXML
    private Button delete111111111111111;

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
    private Button edit111111111111;

    @FXML
    private Button edit1111111111111;

    @FXML
    private Button edit11111111111111;

    @FXML
    private Button edit111111111111111;

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
        dialog.setTitle("Adicionar Pizza");

// Inputs
        TextField saborField = new TextField();
        TextField ingredientesField = new TextField();
        TextField precoPField = new TextField();
        TextField precoMField = new TextField();
        TextField precoGField = new TextField();

        Label saborLabel = new Label("Sabor:");
        Label ingredientesLabel = new Label("Ingredientes:");
        Label precoPLabel = new Label("Preço P:");
        Label precoMLabel = new Label("Preço M:");
        Label precoGLabel = new Label("Preço G:");

// Estilo dos elementos
        saborLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12;");
        ingredientesLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12;");
        precoPLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12;");
        precoMLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12;");
        precoGLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12;");

        Button salvarButton = new Button("Salvar");
        salvarButton.setOnAction(e -> {
            // acesso aos valores de entrada (inputs)
            String sabor = saborField.getText();
            String ingredientes = ingredientesField.getText();
            String precoP = precoPField.getText();
            String precoM = precoMField.getText();
            String precoG = precoGField.getText();

            // implementar parte de salvar os valores

            dialog.close();
        });

        VBox modalLayout = new VBox(10);
        modalLayout.setPadding(new Insets(10));
        modalLayout.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        modalLayout.getChildren().addAll(
                saborLabel, saborField,
                ingredientesLabel, ingredientesField,
                precoPLabel, precoPField,
                precoMLabel, precoMField,
                precoGLabel, precoGField,
                salvarButton
        );

        Scene dialogScene = new Scene(modalLayout, 400, 400);
        dialog.setScene(dialogScene);
        dialog.showAndWait();

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
