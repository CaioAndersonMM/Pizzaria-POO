package View;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    private static Stage primaryStage;
    private static URL backgroundImageUrl = App.class.getResource("/View/images/background_transparent.png");

    public static void setPrimaryStage(Stage stage) {
        App.primaryStage = stage;
    }

    public static void style(Scene cene){
        String backgroundImage = "url('" + backgroundImageUrl.toExternalForm() + "')";
        cene.getRoot().setStyle("-fx-background-image: " + backgroundImage);
    }
    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        primaryStage.show();
        telaLogin();
    }

    public static void telaLogin() throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("VE/LoginGrande.fxml"));
        Scene scene = new Scene(root);
        style(scene);
        primaryStage.setTitle("Pizzaria - Login");
        primaryStage.setScene(scene);
    }

    public static void telaPedidos() throws IOException{
        Parent root = FXMLLoader.load(App.class.getResource("VE/Pedidos.fxml"));
        Scene scene = new Scene(root);
        style(scene);
        primaryStage.setTitle("Pizzaria - Pedidos");
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }

    public static void telaClientes() throws Exception{
        Parent root = FXMLLoader.load(App.class.getResource("VE/Clientes.fxml"));
        Scene scene = new Scene(root);
        style(scene);
        primaryStage.setScene(scene);
    }

    public static void telaFuncionarios() throws Exception{
        Parent root = FXMLLoader.load(App.class.getResource("VE/Funcionarios.fxml"));
        Scene scene = new Scene(root);
        style(scene);
        primaryStage.setScene(scene);
    }

    public static void telaPizzas() throws Exception{
        Parent root = FXMLLoader.load(App.class.getResource("VE/Pizzas.fxml"));
        Scene scene = new Scene(root);
        style(scene);
        primaryStage.setScene(scene);
    }

    public static void telaEstoque() throws Exception{
        Parent root = FXMLLoader.load(App.class.getResource("VE/Estoque.fxml"));
        Scene scene = new Scene(root);
        style(scene);
        primaryStage.setScene(scene);
    }

}