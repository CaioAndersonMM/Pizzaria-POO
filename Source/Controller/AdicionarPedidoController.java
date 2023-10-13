package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.util.List;

import Dao.ClienteDao;
import Model.Entity.Cliente;

public class AdicionarPedidoController {
    @FXML
    private TextField searchClientField;

    @FXML 
    private Button searchClientButton;

    @FXML
    private TableView<Cliente> clientTable;

    @FXML
    private TableColumn<Cliente, String> cfpCol;

    @FXML
    private TableColumn<Cliente, String> nomeCol;
    
    @FXML
    private TableColumn<Cliente, String> telefoneCol;
    
    @FXML
    void pesquisarCliente(ActionEvent event) {
        ClienteDao cliente_dao = new ClienteDao();
        
        Cliente cliente = new Cliente();
        String nome_cliente = this.searchClientField.getText();

        cliente.setNome(nome_cliente);
        List<Cliente> clientes = cliente_dao.buscarPorNome(cliente);

        if (clientes == null) {
            System.out.println("Cliente não cadastrado!");
        } else {
            nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
            cfpCol.setCellValueFactory(new PropertyValueFactory<>("cpf")); //Verificar isso aqui
            telefoneCol.setCellValueFactory(new PropertyValueFactory<>("endereco"));
            ObservableList<Cliente> data = FXCollections.observableArrayList(clientes);
            clientTable.setItems(data);

            for (int i = 0; i < clientes.size(); ++i) {
               System.out.println("Cliente " + (i + 1) + ": " + clientes.get(i).getNome());
            }
        }
    }

    @FXML
    void selecionarCliente(ActionEvent event) {

    }
}