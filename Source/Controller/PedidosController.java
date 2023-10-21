package Controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import Controller.Pedido.SelecionarClienteController;
import Dao.PedidoDao;
import Model.Entity.Pedido;
import View.App;

public class PedidosController {
    private PedidoDao pedidoDAO = new PedidoDao();

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

    public void initialize() {
        List<Pedido> pedidos = pedidoDAO.listar();

        for (Pedido pedido : pedidos) {
            System.out.println(pedido);

            HBox hboxContainer = new HBox();
            
            // id, Nome do cliente, valor, status
            String id = String.valueOf(pedido.getId());
            String nomeCliente;
            if (pedido.getCliente() != null) {
                nomeCliente = pedido.getCliente().getNome();
            } else{
                nomeCliente = "Cliente Deletado";
            }
         
            String valor = "R$: " + pedido.getValor();
            String status = pedido.getStatus() ? "Concluído" : "Em andamento";
            
            Label idLabel = new Label(id);
            Label nomeClienteLabel = new Label("Cliente: " + nomeCliente);
            Label valorLabel = new Label(valor);
            Label statusLabel = new Label(status);
            Button button1 = new Button("Finalizar");
            //Button button2 = new Button("Excluir");

            nomeClienteLabel.setPrefWidth(200);
            valorLabel.setPrefWidth(100);
            statusLabel.setPrefWidth(100);

            Separator separator = new Separator();            
            separator.setVisible(false);
            HBox.setHgrow(separator, Priority.ALWAYS);
            
            button1.setOnAction((ActionEvent event) -> {
                // try {
                //     edit(event, (Long) dado[0], String.valueOf(dado[1]), String.valueOf(dado[2]), String.valueOf(dado[3]), String.valueOf(dado[4]));
                // } catch (IOException e) {
                //     e.printStackTrace();
                // }

                try {
                    edit(event, pedido.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
            
            //button2.setOnAction((ActionEvent event) -> {
            //     try {
            //          delete(event, (Long) pedido.getId());
            //     } catch (IOException e) {
            //         e.printStackTrace();
            //     }
            //});
            
            hboxContainer.getChildren().addAll(idLabel, nomeClienteLabel, valorLabel, statusLabel, separator, button1);
            hboxContainer.setSpacing(20);
            
            Insets padding = new Insets(10, 10, 10, 10);
            hboxContainer.setPadding(padding);
            hboxContainer.prefHeight(80);
            hboxContainer.getStyleClass().add("table_row");
            
            tabela.getChildren().add(hboxContainer);
        }
    }

    @FXML
    void adicionar(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("VE/dialogs/pesquisar_cliente.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Cria uma nova janela de diálogo
        Stage stage = new Stage();
        SelecionarClienteController controller = loader.getController();
        controller.setStage(stage);

        stage.initModality(Modality.APPLICATION_MODAL); // Configura como uma janela de diálogo modal
        stage.setScene(scene);
        stage.setTitle("Minha Janela de Diálogo");
        stage.show();
    }   

    @FXML
    void delete(ActionEvent event, Long id) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("VE/dialogs/excluir_cadastro.fxml"));
        Scene scene = new Scene(root);

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    @FXML
    void edit(ActionEvent event, Long id) throws IOException {
        PedidoDao finalizar = new PedidoDao();
        Pedido pedido = new Pedido();
        pedido.setId(id);
        finalizar.finalizarPedido(pedido);
        App.telaPedidos();
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
