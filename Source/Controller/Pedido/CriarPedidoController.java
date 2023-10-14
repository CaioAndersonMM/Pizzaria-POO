package Controller.Pedido;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import Controller.AdicionarPedidoController;
import Dao.PizzaDao;
import Dao.TipoPizzaDao;
import Model.Entity.Cliente;
import Model.Entity.Pizza;
import Model.Entity.Produto;
import Model.Entity.TipoPizza;
import Model.BO.PizzaBo;

import View.App;

public class CriarPedidoController {
    // VARIABLES

    private Stage stage;
    private Cliente clienteSelecionado;
    private List<Pizza> pizzas;
    
    @FXML
    private Button addAdicionalButton;

    @FXML
    private Button addPizzaButton;

    @FXML
    private ComboBox<Produto> adicionalField;

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
    private ComboBox<String> pizzaTamField;

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

    // METHODS 

    public void setCliente(Cliente cliente) {
        this.clienteSelecionado = cliente;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void initialize() {
        ObservableList<String> tiposPizza = FXCollections.observableArrayList();
        ObservableList<String> tamanhos = FXCollections.observableArrayList("Pequena", "Média", "Grande");

        TipoPizzaDao tipoPizzaDAO = new TipoPizzaDao();
        for (TipoPizza tipoPizza: tipoPizzaDAO.listar()) {
            tiposPizza.add(tipoPizza.getNomeSabor());
        }

        pizzaTamField.setItems(tamanhos);
        pizzaField.setItems(tiposPizza);
    }
    
    private void telaSelecaoCliente() throws IOException {
        this.stage.close();

        FXMLLoader loader = new FXMLLoader(App.class.getResource("VE/dialogs/pesquisar_cliente.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        // Passar valores para próximo formulário
        SelecionarClienteController controller = loader.getController();
        controller.setCliente(this.clienteSelecionado);
        controller.setPizzas(this.pizzas);
        
        Stage stage = new Stage();
        controller.setStage(stage);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Pesquisar cliente");
        stage.show();
    }

    // ACTIONS 
    @FXML
    void cancel(ActionEvent event) throws IOException {
        telaSelecaoCliente();
    }

    @FXML
    void finalizar(ActionEvent event) {
        stage.close();
    }
} 
