package Controller.Pedido;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Dao.PedidoDao;
import Dao.ProdutoDao;
import Dao.TipoPizzaDao;

import Model.Entity.Cliente;
import Model.Entity.Pedido;
import Model.Entity.Pizza;
import Model.Entity.Produto;
import Model.Entity.TipoPizza;
import Utils.ProdutoStringConverter;
import Utils.TipoPizzaStringConverter;
import Model.BO.PizzaBo;

import View.App;

public class CriarPedidoController {
    // VARIABLES
    private Stage stage;
    private Cliente clienteSelecionado;
    private PedidoDao pedidoDAO = new PedidoDao();
    private List<Pizza> pizzas = new ArrayList<Pizza>();
    private List<Produto> adicionais  = new ArrayList<Produto>();
    private Pedido pedido = new Pedido();

    @FXML
    private Button addAdicionalButton;

    @FXML
    private Button addPizzaButton;

    @FXML
    private ComboBox<Produto> adicionalField;

    @FXML
    private Button cancelarPedidoButton;

    @FXML
    private Button finalizarPedidoButton;

    @FXML
    private TableView<?> itemsPedidoTableView;

    @FXML
    private ComboBox<TipoPizza> pizzaField;

    @FXML
    private ComboBox<String> pizzaTamField;

    @FXML
    private TableView<Produto> adicionalTable;

    @FXML
    private TableColumn<Cliente, String> tableAdicionalNomeCol;

    @FXML
    private TableColumn<Cliente, Float> tableAdicionalValorCol;

    @FXML
    private TableColumn<?, ?> tableAdicionalRemoverCol;    

    @FXML
    private TableView<Pizza> tablePizzas;

    @FXML
    private TableColumn<Pizza, String> tablePizzasPizzaCol;

    @FXML
    private TableColumn<Pizza, String> tablePizzasTamCol;

    @FXML
    private TableColumn<Pizza, Float> tablePizzasValorCol;

    @FXML
    private TableColumn<Pizza, String> tablePizzasAdicionaisCol;

    @FXML
    private TableColumn<?, ?> tablePizzasRemoverCol;    

    // METHODS 

    public void setCliente(Cliente cliente) {
        this.clienteSelecionado = cliente;
    }

    public void setPizzas(List<Pizza> pizzas) {
        if (pizzas != null) {
            this.pizzas = pizzas;    
        } else {
            System.out.println("WARNING: Pizzas = null");
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        ObservableList<TipoPizza> tiposPizza = FXCollections.observableArrayList();
        ObservableList<String> tamanhos = FXCollections.observableArrayList("Pequena", "Média", "Grande");
        ObservableList<Produto> adicionais = FXCollections.observableArrayList();
    
        TipoPizzaDao tipoPizzaDAO = new TipoPizzaDao();
        for (TipoPizza tipoPizza: tipoPizzaDAO.listar()) {
            tiposPizza.add(tipoPizza);
        }

        ProdutoDao produtoDAO = new ProdutoDao();
        for (Produto produto: produtoDAO.listar()) {
            if (produto.isAdicional()) {
                adicionais.add(produto);
            }
        }

        pizzaTamField.setItems(tamanhos);
        pizzaTamField.setEditable(false);

        pizzaField.setItems(tiposPizza);
        pizzaField.setConverter(new TipoPizzaStringConverter());
        pizzaField.setEditable(false);

        adicionalField.setItems(adicionais);
        adicionalField.setConverter(new ProdutoStringConverter());
        adicionalField.setEditable(false);

        // Inicializar tabela de pizzas
        
        tablePizzasPizzaCol.setCellValueFactory(d -> {
            return new SimpleStringProperty(d.getValue().getTipo().getNomeSabor());
        });

        tablePizzasAdicionaisCol.setCellValueFactory(d -> {
            String ingredientes = "";
            for (Produto ingrediente: d.getValue().getAdicionais()) {
                ingredientes += ingrediente.getNome() + " ";
            }
            return new SimpleStringProperty(ingredientes);
        });
        
        tablePizzasValorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tablePizzasTamCol.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        // private TableColumn<?, ?> tablePizzasEditCol;
        // private TableColumn<?, ?> tablePizzasRemoverCol;

        // Inicializar tabela de adicionais
        tableAdicionalNomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableAdicionalValorCol.setCellValueFactory(new PropertyValueFactory<>("valor"));
        // tableAdicionalQntCol.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        // tableAdicionalRemoverCol;
        

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
    
    public float calcularValorTotal() {
        float total = 0;
        for (Pizza pizza: pizzas) {
            total += pizza.getValor();
        }
        return total;
    }

    void addPizzaToTable(Pizza pizza) {
        pizzas.add(pizza);
        ObservableList<Pizza> data = FXCollections.observableArrayList(pizzas);
        tablePizzas.setItems(data);
    }

    void addAdicionalToTable() {
        ObservableList<Produto> data = FXCollections.observableArrayList(adicionais);
        adicionalTable.setItems(data);
    }

    void clearAdicionalTable() {
        ObservableList<Produto> data = FXCollections.observableArrayList(new ArrayList<Produto>());
        adicionalTable.setItems(data);
    }

    void limparCampos() {
        pizzaField.setValue(null);
        pizzaTamField.setValue(null);
        adicionalField.setValue(null);
    }

    // ACTIONS 

    @FXML
    void addAdicional(ActionEvent event) {
        Produto adicional = adicionalField.getSelectionModel().getSelectedItem();
        adicionais.add(adicional);
        addAdicionalToTable();
    }

    @FXML
    void addPizza(ActionEvent event) {
        Pizza pizza = new Pizza();
        String tamanhoString = pizzaTamField.getValue();
        TipoPizza tipoPizza = pizzaField.getValue();
        
        if (tipoPizza == null) {
            // TODO: Mostrar esse aviso na interface
            System.out.println("WARNING: O sabor da pizza não foi definido");
            return;
        }

        if (tamanhoString == null) {
            // TODO: criar uma forma de mostrar esse aviso na interface.
            System.out.println("WARNING: O campo de tamanho da pizza está vazio.");
            return;
        }

        pizza.setTipo(tipoPizza);
        pizza.setAdicionais(adicionais);
        adicionais = new ArrayList<Produto>();

        if (tamanhoString.equals("Pequena")) {
            pizza.setTamanho('p');
        } else if (tamanhoString.equals("Média")) {
            pizza.setTamanho('m');
        } else if (tamanhoString.equals("Grande")) {
            pizza.setTamanho('g');
        }
        
        limparCampos();
        addPizzaToTable(pizza);
        clearAdicionalTable();
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        telaSelecaoCliente();
    }

    @FXML
    void finalizar(ActionEvent event) {
        stage.close();
    }
} 
