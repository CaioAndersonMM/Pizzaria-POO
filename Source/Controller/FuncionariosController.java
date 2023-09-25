package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;

public class FuncionariosController {
    @FXML private DialogPane modal;

    public void adicionarFuncionario(ActionEvent event){
        System.out.println("Clicou");
        System.out.println(modal);
        modal.setVisible(true);
    }
    public void fecharModal(ActionEvent event){
        System.out.println("Clicou P/fechar");
        modal.setVisible(false);
    }
}
