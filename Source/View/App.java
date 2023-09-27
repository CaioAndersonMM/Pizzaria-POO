package View;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    private static Stage primaryStage;
    
    public static void setPrimaryStage(Stage stage) {
        App.primaryStage = stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        primaryStage.show();
        telaLogin();
    }

    public static void telaLogin() throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("VE/Login.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Pizzaria - Login");
        primaryStage.setScene(scene);
    }

    public static void telaPedidos() throws IOException{
        Parent root = FXMLLoader.load(App.class.getResource("VE/Pedidos.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Pizzaria - Pedidos");
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}