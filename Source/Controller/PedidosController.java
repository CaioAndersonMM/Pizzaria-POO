package Controller;

import java.beans.EventHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PedidosController {
    @FXML private DialogPane modal;

    public void adicionarPedido(ActionEvent event){
        System.out.println("Clicou");
        System.out.println(modal);
        modal.setVisible(true);
    }
    public void fecharModal(ActionEvent event){
        System.out.println("Clicou P/fechar");
        modal.setVisible(false);
    }

    public void ChamarModalCliente(ActionEvent event){
        System.out.println("Proximo Modal...");
        modal.setVisible(false);
    }

}
