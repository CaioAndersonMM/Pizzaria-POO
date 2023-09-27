package Controller;

import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField cpf;
    @FXML private PasswordField senha;

    @FXML
    void login(ActionEvent event) throws Exception{
        String cpfString = cpf.getText();
        String senhaString = senha.getText();
        
        System.out.println("CPF " + cpfString);
        System.out.println("Senha " + senhaString);

        if (cpfString.equals("12345678910") && senhaString.equals("admin")) {
            App.telaPedidos();
        }
    }
}
