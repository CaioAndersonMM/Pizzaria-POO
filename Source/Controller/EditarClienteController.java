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

public class EditarClienteController {

    @FXML
    private Button cancel;

    @FXML
    private TextField cpf;

    @FXML
    private TextField endereco;

    @FXML
    private TextField nome;

    @FXML
    private Button salvar;

    public static Long id;

    public void setValoresEdicao(String nomecliente, String cpfcliente, String enderecocliente) {
        nome.setText(nomecliente);;
        cpf.setText(cpfcliente);
        endereco.setText(enderecocliente);
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void salvar(ActionEvent event) throws Exception {

        Cliente cliente = new Cliente();
        cliente.setCPF(cpf.getText());
        cliente.setNome(nome.getText());
        cliente.setEndereco(endereco.getText());
        cliente.setId(id);

        ClienteBo bo = new ClienteBo();
        bo.editar(id, cliente);

        // Obter o Stage do modal e fechá-lo.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        //Melhor chamar a tela denovo para recarregar!
        App.telaClientes();
    }

}
