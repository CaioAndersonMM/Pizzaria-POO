package View;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloFx extends Application{
    private static Stage primaryStage;
    private static URL backgroundImageUrl = HelloFx.class.getResource("/View/images/background_transparent.png");

    public static void setPrimaryStage(Stage primaryStage) {
        HelloFx.primaryStage = primaryStage;
    }

    public static void style(Scene cene){
        String backgroundImage = "url('" + backgroundImageUrl.toExternalForm() + "')";
        cene.getRoot().setStyle("-fx-background-image: " + backgroundImage);
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
        Parent root = FXMLLoader.load(HelloFx.class.getResource("VE/Pedidos.fxml"));
        Scene scene = new Scene(root);
        style(scene);

        primaryStage.setScene(scene);
    }

    public static void telaPrincipalGerente() throws Exception{
        Parent root = FXMLLoader.load(HelloFx.class.getResource("VE/Pizzas_v2.fxml"));
        Scene scene = new Scene(root);
        style(scene);
        primaryStage.setScene(scene);
    }

    public static void telaPrincipalFuncionario() throws Exception{
        Parent root = FXMLLoader.load(HelloFx.class.getResource("VE/Pizzas.fxml"));
        Scene scene = new Scene(root);
        style(scene);
        primaryStage.setScene(scene);
    }

    public static void telaClientee() throws Exception{
        Parent root = FXMLLoader.load(HelloFx.class.getResource("VE/Cliente.fxml"));
        Scene scene = new Scene(root);
        style(scene);
        primaryStage.setScene(scene);
    }

}