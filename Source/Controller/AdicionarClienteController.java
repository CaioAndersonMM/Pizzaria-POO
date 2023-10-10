package Controller;
import Model.BO.ClienteBo;
import Model.Entity.Cliente;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdicionarClienteController {

    @FXML
    private Button adicionar;

    @FXML
    private Button cancel;

    @FXML
    private TextField cpf;

    @FXML
    private TextField endereco;

    @FXML
    private TextField nome;

    @FXML
    void adicionar(ActionEvent event) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome(nome.getText());
        cliente.setCPF(cpf.getText());
        cliente.setEndereco(endereco.getText());

        ClienteBo bo = new ClienteBo();
        bo.criar(cliente);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        App.telaClientes();
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
