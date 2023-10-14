package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import Dao.ClienteDao;
import Dao.PizzaDao;

import Model.Entity.Cliente;
import Model.Entity.Pizza;
import Model.Entity.Produto;

import View.App;

public class AdicionarPedidoController {
    private Stage stage;
    private Cliente clienteSelecionado;
    private List<Pizza> pizzas;
    
    @FXML
    private TextField searchClientField;

    @FXML 
    private Button searchClientButton;
    
    @FXML
    private Button addAdicionalButton;

    @FXML
    private Button addPizzaButton;

    @FXML
    private ComboBox<Cliente> adicionalField;

    @FXML
    private Spinner<Double> adicionalQntField;

    @FXML
    private Button cancelarPedidoButton;

    @FXML
    private Button finalizarPedidoButton;

    @FXML
    private TableView<?> itemsPedidoTableView;

    @FXML
    private ComboBox<String> pizzaField;

    @FXML
    private ComboBox<?> pizzaTamField;

    // Tabela Clientes
    
    @FXML
    private TableView<Cliente> clientTable;

    @FXML
    private TableColumn<Cliente, String> cfpCol;

    @FXML
    private TableColumn<Cliente, String> nomeCol;
    
    @FXML
    private TableColumn<Cliente, String> telefoneCol;

    // Tabela adicionais

    @FXML
    private TableView<Produto> adicionalTable;

    @FXML
    private TableColumn<Cliente, String> tableAdicionalNomeCol;

    @FXML
    private TableColumn<Cliente, Integer> tableAdicionalQntCol;

    @FXML
    private TableColumn<Cliente, Float> tableAdicionalValorCol;

    @FXML
    private TableColumn<?, ?> tableAdicionalRemoverCol;    

    // Tabela pizzas

    @FXML
    private TableView<Pizza> tablePizzas;

    @FXML
    private TableColumn<?, ?> tablePizzasPizzaCol;

    @FXML
    private TableColumn<?, ?> tablePizzasTamCol;

    @FXML
    private TableColumn<?, ?> tablePizzasValorCol;

    @FXML
    private TableColumn<?, ?> tablePizzasAdicionaisCol;

    @FXML
    private TableColumn<?, ?> tablePizzasEditCol;

    @FXML
    private TableColumn<?, ?> tablePizzasRemoverCol;    

    public void setCliente(Cliente cliente) {
        this.clienteSelecionado = cliente;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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
    void selecionarCliente(ActionEvent event) throws Exception {
        telaFazerPedido();
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        telaSelecaoCliente();
    }

    @FXML
    void finalizar(ActionEvent event) {
    }

    void telaFazerPedido() throws IOException {
        this.stage.close();

        FXMLLoader loader = new FXMLLoader(App.class.getResource("VE/dialogs/fazer_pedido.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        // Passar valores para próximo formulário
        AdicionarPedidoController controller = loader.getController();
        controller.setCliente(this.clienteSelecionado);
        controller.setPizzas(this.pizzas);
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Realizar pedido");

        controller.setStage(stage);
        stage.show();
    }

    void telaSelecaoCliente() throws IOException {
        this.stage.close();

        FXMLLoader loader = new FXMLLoader(App.class.getResource("VE/dialogs/pesquisar_cliente.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        // Passar valores para próximo formulário
        AdicionarPedidoController controller = loader.getController();
        controller.setCliente(this.clienteSelecionado);
        controller.setPizzas(this.pizzas);
        
        Stage stage = new Stage();
        controller.setStage(stage);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Pesquisar cliente");
        stage.showAndWait();
    }
} 
