package View;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloFx extends Application{
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        HelloFx.primaryStage = primaryStage;
    }

    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        primaryStage.setTitle("Pizzaria do Michelangelo - Login");
        primaryStage.show();
        telaLogin();
    }

    public static void main(String[] args){
        launch();
    }
    public static void telaLogin() throws Exception{
        URL backgroundImageUrl = HelloFx.class.getResource("/View/Ve/background_transparent.png");
        Parent root = FXMLLoader.load(HelloFx.class.getResource("clientes_v2.fxml"));
        Scene scene = new Scene(root);

        if (backgroundImageUrl != null) {
            String backgroundImage = "url('" + backgroundImageUrl.toExternalForm() + "')";
            scene.getRoot().setStyle("-fx-background-image: " + backgroundImage);
        }

        primaryStage.setScene(scene);
    }

    public static void telaPrincipalGerente() throws Exception{
        Parent root = FXMLLoader.load(HelloFx.class.getResource("Cliente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaPrincipalFuncionario() throws Exception{
        Parent root = FXMLLoader.load(HelloFx.class.getResource("Pedidos_V1.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

}