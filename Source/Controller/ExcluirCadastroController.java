package Controller;

import Model.BO.ClienteBo;
import Model.BO.FuncionarioBo;
import Model.BO.TipoPizzaBo;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ExcluirCadastroController {

    @FXML
    private Button cancel;

    @FXML
    private Button excluir;

    public static Long tipopizza_id;
    public static Long cliente_id;
    public static Long funcionario_id;

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void deletar(ActionEvent event) throws Exception {
        if(tipopizza_id != null){
            TipoPizzaBo bo = new TipoPizzaBo();
            bo.deletar(tipopizza_id);

            tipopizza_id = null; //importante anular o id que foi usado

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            App.telaPizzas();
        }

         if(cliente_id != null){
            ClienteBo bo = new ClienteBo();
            System.out.println("Indo Excluir ID: "+ cliente_id);
            bo.deletar(cliente_id);

            cliente_id = null;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            App.telaClientes();
        }
        if (funcionario_id != null) {
            System.out.println("ENTROU AQUI");
            FuncionarioBo bo = new FuncionarioBo();
            bo.deletar(funcionario_id);

            funcionario_id = null;

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            App.telaFuncionarios();
        }

    }

}
