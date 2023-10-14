package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Dao.TipoPizzaDao;
import Model.BO.TipoPizzaBo;
import Model.Entity.TipoPizza;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PizzasController implements  Initializable{

    @FXML
    private Button adicionar;

    @FXML
    private Button clientes;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button estoque;

    @FXML
    private Button funcionarios;

    @FXML
    private Button pedidos;

    @FXML
    private Button pizzas;

    @FXML
    private Button relatorios;

    @FXML
    private Button sair;

    @FXML
    private VBox tabela;

    @FXML
    private ImageView buscar;

    @FXML
    private TextField searchField;

    public static List<TipoPizza> filtrados;

    @FXML
    void buscar(MouseEvent event) throws Exception {
         if(this.searchField.getText().isEmpty()){
            filtrados = null;
            App.telaPizzas();
        } else{
            TipoPizzaDao dao = new TipoPizzaDao();
            
            TipoPizza pizza = new TipoPizza();
            String nome = this.searchField.getText();

            pizza.setNomeSabor(nome);
            List<TipoPizza> pizzas = dao.buscarPorNome(pizza);

            if (pizzas == null) {
            System.out.println("Não encontrado");
            } else {
                filtrados = pizzas;
                App.telaPizzas();
            }
        }
    }
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Object[]> dadosDoBanco = recuperarDadosDoBanco();

        for (Object[] dado : dadosDoBanco) {
            HBox hboxContainer = new HBox();

            Separator separator = new Separator();
            Separator separator2 = new Separator();
            Separator separator3 = new Separator();
            
            Hyperlink idLabel = new Hyperlink(String.valueOf(dado[0]));
            separator.setVisible(false);
            HBox.setHgrow(separator, Priority.ALWAYS);
            Label tipoLabel = new Label(String.valueOf(dado[1]));
            separator2.setVisible(false);
            HBox.setHgrow(separator2, Priority.ALWAYS);
            Label valorpLabel = new Label(String.valueOf(dado[2]));
            Label valormLabel = new Label(String.valueOf(dado[3]));
            Label valorgLabel = new Label(String.valueOf(dado[4]));
            separator3.setVisible(false);
            HBox.setHgrow(separator3, Priority.ALWAYS);
            Button button1 = new Button("Editar");
            Button button2 = new Button("Excluir");
            button1.setOnAction((ActionEvent event) -> {
                try {
                    edit(event, (Long) dado[0], String.valueOf(dado[1]), String.valueOf(dado[2]), String.valueOf(dado[3]), String.valueOf(dado[4]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            button2.setOnAction((ActionEvent event) -> {
                try {
                    delete(event, (Long) dado[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            hboxContainer.getChildren().addAll(idLabel, separator, tipoLabel, separator2, valorpLabel, valormLabel, valorgLabel, separator3, button1, button2);
            hboxContainer.setSpacing(20);
            Insets padding = new Insets(10, 10, 10, 10);
            hboxContainer.setPadding(padding);
            hboxContainer.prefHeight(80);
            hboxContainer.getStyleClass().add("table_row");
            
            tabela.getChildren().add(hboxContainer);
        }
    }

    private List<Object[]> recuperarDadosDoBanco() {
        TipoPizzaBo pizzabo = new TipoPizzaBo();
        List<TipoPizza> vo = pizzabo.listar();

        // Crie uma lista para armazenar todas as informações das pizzas
        List<Object[]> dados = new ArrayList<>();

        if (filtrados == null) {
            for (TipoPizza pizza : vo) {
            Object[] pizzaInfo = new Object[6]; // Criar um array de objetos para armazenar as informações
            pizzaInfo[0] = pizza.getId();
            pizzaInfo[1] = pizza.getNomeSabor();
            float valores[] = pizza.getValores();
            pizzaInfo[2] = valores[2]; //g
            pizzaInfo[3] = valores[1]; //m
            pizzaInfo[4] = valores[0]; //p
            //pizzaInfo[5] = pizza.getIngredientes();
            dados.add(pizzaInfo);
            }
        } else {
            for (TipoPizza pizza : filtrados) {
            Object[] pizzaInfo = new Object[6];
            pizzaInfo[0] = pizza.getId();
            pizzaInfo[1] = pizza.getNomeSabor();
            float valores[] = pizza.getValores();
            pizzaInfo[2] = valores[2]; //g
            pizzaInfo[3] = valores[1]; //m
            pizzaInfo[4] = valores[0]; //p
            //pizzaInfo[5] = pizza.getIngredientes();
            dados.add(pizzaInfo);
            }
        }
        return dados;
    }


    @FXML
    void adicionar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("VE/dialogs/adicionar_pizza.fxml"));
        Scene scene = new Scene(root);

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    @FXML
    void delete(ActionEvent event, Long id) throws IOException {
        System.out.println("ID que será apagado é: "+ id);
        ExcluirCadastroController.tipopizza_id = id;
        Parent root = FXMLLoader.load(App.class.getResource("VE/dialogs/excluir_cadastro.fxml"));
        Scene scene = new Scene(root);

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    @FXML
    void edit(ActionEvent event, Long id, String sabor, String precop, String precom, String precog) throws IOException  {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("VE/dialogs/editar_pizza.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        EditarPizzaController editarPizzaController = loader.getController();

        editarPizzaController.setValoresEdicao(sabor, precop, precom, precog);
        EditarPizzaController.id = id;

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    @FXML
    void loggout(ActionEvent event) throws IOException {
        App.sair();
    }

   @FXML
    void telaClientes(ActionEvent event) throws Exception {
        App.telaClientes();
    }

    @FXML
    void telaEstoque(ActionEvent event) throws Exception {
        App.telaEstoque();
    }

    @FXML
    void telaFuncionarios(ActionEvent event) throws Exception {
        App.telaFuncionarios();
    }

    @FXML
    void telaPedidos(ActionEvent event) throws IOException {
        App.telaPedidos();
    }

    @FXML
    void telaPizzas(ActionEvent event) throws Exception {
        App.telaPizzas();
    }

    @FXML
    void telaRelatorios(ActionEvent event) throws Exception {
        App.telaRelatorio();
    }
}
