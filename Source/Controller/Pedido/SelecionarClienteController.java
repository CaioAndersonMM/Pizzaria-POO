package Controller.Pedido;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import Dao.ClienteDao;

import Model.Entity.Cliente;
import Model.Entity.Pizza;
import View.App;


public class SelecionarClienteController {
    // VARIABLES

    private Stage stage;
    private Cliente clienteSelecionado;
    private List<Pizza> pizzas;

    @FXML
    private Pane root;

    @FXML
    private Button searchClientButton;

    @FXML
    private TextField searchClientField;
    
    @FXML
    private TableView<Cliente> clientTable;

    @FXML
    private TableColumn<Cliente, String> cfpCol;

    @FXML
    private TableColumn<Cliente, String> nomeCol;
    
    @FXML
    private TableColumn<Cliente, String> telefoneCol;

    // METHODS
    public void initialize() {
        ClienteDao cliente_dao = new ClienteDao();
        List<Cliente> clientes = cliente_dao.listar();

        if (clientes == null) {
            System.out.println("Cliente não cadastrado!");
        } else {
            ObservableList<Cliente> data = FXCollections.observableArrayList(clientes);

            nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
            cfpCol.setCellValueFactory(new PropertyValueFactory<>("cpf")); //Verificar isso aqui
            telefoneCol.setCellValueFactory(new PropertyValueFactory<>("endereco"));
            
            clientTable.setItems(data);
        }
    }

    public void setCliente(Cliente cliente) {
        this.clienteSelecionado = cliente;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    private void telaFazerPedido() throws IOException {
        this.stage.close();
        
        FXMLLoader loader = new FXMLLoader(App.class.getResource("VE/dialogs/fazer_pedido.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        // Passar valores para próximo formulário
        CriarPedidoController controller = loader.getController();
        controller.setCliente(this.clienteSelecionado);
        controller.setPizzas(this.pizzas);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Realizar pedido");

        controller.setStage(stage);
        stage.show();
    }

    // ACTIONS

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
        try {
            this.telaFazerPedido();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML 
    void cancelar(ActionEvent event) {
        this.stage.close();
    }
}
