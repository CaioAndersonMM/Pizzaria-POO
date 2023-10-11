package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.BO.FuncionarioBo;
import Model.BO.TipoPizzaBo;
import Model.Entity.Funcionario;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FuncionariosController implements Initializable {

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
    private VBox tabela;

    @FXML
    private Button sair;

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
            Label tipoLabel = new Label(String.valueOf(dado[1])); //nome
            separator2.setVisible(false);
            HBox.setHgrow(separator2, Priority.ALWAYS);
            Label cpfLabel = new Label(String.valueOf(dado[2])); //cpf
            separator3.setVisible(false);
            HBox.setHgrow(separator3, Priority.ALWAYS);
            //Não se deve mostrar senha na tela

            Button button1 = new Button("Editar");
            Button button2 = new Button("Excluir");
            button1.setOnAction((ActionEvent event) -> {
                try {
                    edit(event, (Long) dado[0], String.valueOf(dado[1]), String.valueOf(dado[2]), String.valueOf(dado[3]));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            button2.setOnAction((ActionEvent event) -> {
                try {
                    delete(event, (Long) dado[0]);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            hboxContainer.getChildren().addAll(idLabel, separator, tipoLabel, separator2, cpfLabel, separator3, button1, button2);
            hboxContainer.setSpacing(20);
            Insets padding = new Insets(10, 10, 10, 10);
            hboxContainer.setPadding(padding);
            hboxContainer.prefHeight(80);
            hboxContainer.getStyleClass().add("table_row");
            
            tabela.getChildren().add(hboxContainer);
            }
    }

    private List<Object[]> recuperarDadosDoBanco() {
        FuncionarioBo bo = new FuncionarioBo();
        List<Funcionario> vo = bo.listar();

        List<Object[]> dados = new ArrayList<>();

        for (Funcionario funcionario : vo) {
            Object[] funcionarioInfo = new Object[6]; // Criar um array de objetos para armazenar as informações
            funcionarioInfo[0] = funcionario.getId();
            funcionarioInfo[1] = funcionario.getNome();
            funcionarioInfo[2] = funcionario.getCPF();
            funcionarioInfo[3] = funcionario.getSenha();
            dados.add(funcionarioInfo);
        }
        return dados;
    }

    @FXML
    void adicionar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("VE/dialogs/adicionar_funcionario.fxml"));
        Scene scene = new Scene(root);

        // Cria uma nova janela de diálogo
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Configura como uma janela de diálogo modal
        //dialogStage.initOwner().getScene().getWindow()); // Define a janela pai
        dialogStage.setScene(scene);

        // Exibe a janela de diálogo
        dialogStage.showAndWait();
    }

    @FXML
    void delete(ActionEvent event, Long id) throws IOException {
        ExcluirCadastroController.funcionario_id = id;
        Parent root = FXMLLoader.load(App.class.getResource("VE/dialogs/excluir_cadastro.fxml"));
        Scene scene = new Scene(root);

        // Cria uma nova janela de diálogo
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Configura como uma janela de diálogo modal
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    @FXML
    void edit(ActionEvent event, Long id, String nome, String cpf, String senha) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("VE/dialogs/editar_funcionario.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        EditarFuncionarioController editarFuncionario = loader.getController();

        editarFuncionario.setValoresEdicao(id, nome, cpf, senha);

        // Cria uma nova janela de diálogo
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Configura como uma janela de diálogo modal
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
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