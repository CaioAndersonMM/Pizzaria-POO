package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.BO.PizzaBo;
import Model.Entity.Pizza;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
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
    private ScrollPane tabela;

   public void initialize(URL url, ResourceBundle resourceBundle) {
        // Crie um HBox dinamicamente
      
        // Suponha que você tenha recuperado dados do banco de dados e armazenado em uma lista
        List<Object[]> dadosDoBanco = recuperarDadosDoBanco();

       
         // Crie um VBox para conter os HBox
        VBox vboxContainer = new VBox();

        // Para cada dado recuperado, crie um rótulo e adicione ao HBox
        for (Object[] dado : dadosDoBanco) {

            HBox hboxContainer = new HBox();

            // Crie rótulos para cada elemento e adicione ao 
            Label idLabel = new Label(String.valueOf(dado[0]));
            System.out.println(String.valueOf(dado[0]));
            Label tipoLabel = new Label(String.valueOf(dado[1]));
             System.out.println(String.valueOf(dado[1]));
            Label adicionaisLabel = new Label(String.valueOf(dado[2]));
             System.out.println(String.valueOf(dado[2]));
            Label valorLabel = new Label(String.valueOf(dado[3]));
             System.out.println(String.valueOf(dado[3]));
            Label tamanhoLabel = new Label(String.valueOf(dado[4]));
             System.out.println(String.valueOf(dado[4]));

            hboxContainer.getChildren().addAll(idLabel, tipoLabel, adicionaisLabel, valorLabel, tamanhoLabel);
            vboxContainer.getChildren().add(hboxContainer);


        }

        tabela.setContent(vboxContainer);


        tabela.setStyle("-fx-background-color: lightgray;");

    }

    // Simulação da recuperação de dados do banco de dados
    private List<Object[]> recuperarDadosDoBanco() {
        PizzaBo pizzabo = new PizzaBo();
        List<Pizza> vo = pizzabo.listar();

        // Crie uma lista para armazenar todas as informações das pizzas
        List<Object[]> dados = new ArrayList<>();

        // Itere sobre os objetos Pizza e obtenha seus tamanhos
        for (Pizza pizza : vo) {
            Object[] pizzaInfo = new Object[5]; // Criar um array de objetos para armazenar as informações
            pizzaInfo[0] = pizza.getId();
            pizzaInfo[1] = pizza.getTipo();
            pizzaInfo[2] = pizza.getAdicionais();
            pizzaInfo[3] = pizza.getValor();
            pizzaInfo[4] = pizza.getTamanho();

            dados.add(pizzaInfo); // Adicionar as informações ao array de dados
        }

        return dados;
    }


    @FXML
    void adicionar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("VE/dialogs/adicionar_pizza.fxml"));
        Scene scene = new Scene(root);

        // Cria uma nova janela de diálogo
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Configura como uma janela de diálogo modal
        //dialogStage.initOwner().getScene().getWindow()); // Define a janela pai
        dialogStage.setScene(scene);

        // Define um título para a janela de diálogo (opcional)
        dialogStage.setTitle("Minha Janela de Diálogo");

        // Exibe a janela de diálogo
        dialogStage.showAndWait();
    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void loggout(ActionEvent event) {

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
    void telaRelatorios(ActionEvent event) {

    }


}
