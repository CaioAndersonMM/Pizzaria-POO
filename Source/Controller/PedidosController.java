package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;

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
