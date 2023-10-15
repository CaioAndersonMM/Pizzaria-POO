package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Dao.ProdutoDao;
import Model.BO.ProdutoBo;
import Model.Entity.Produto;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EstoqueController implements Initializable {

    @FXML
    private Button adicionar;

    @FXML
    private Button clientes;

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

    public static Long produto_id;

    @FXML
    private TextField searchField;

    public static List<Produto> filtrados;

    @FXML
    void buscar(MouseEvent event) throws Exception {
        if(this.searchField.getText().isEmpty()){
            filtrados = null;
            App.telaEstoque();
        } else{
            ProdutoDao dao = new ProdutoDao();
            String nome_produto = this.searchField.getText();
            
            // Produto produto = new Produto();
            // produto.setNome(nome_produto);
            List<Produto> produtos = dao.buscarPorNome(nome_produto);

            if (produtos == null) {
            } else {
                filtrados = produtos;
                App.telaEstoque();
            }
        }
    }

    private List<Object[]> recuperarDadosDoBanco() {
        
        List<Object[]> dados = new ArrayList<>();

        if(filtrados == null){
            ProdutoBo produtoBo = new ProdutoBo();
            List<Produto> vo = produtoBo.listar();


            for (Produto produto : vo) {
                Object[] produtoInfo = new Object[6];
                produtoInfo[0] = produto.getId();
                produtoInfo[1] = produto.getNome();
                produtoInfo[2] = produto.getQuantidade();
                produtoInfo[3] = produto.getValor();
                produtoInfo[4] = produto.isAdicional();
                dados.add(produtoInfo);
            }// Criar um array de objetos para armazenar as informações

        } else{
            for (Produto produto : filtrados) {
                Object[] produtoInfo = new Object[6];
                produtoInfo[0] = produto.getId();
                produtoInfo[1] = produto.getNome();
                produtoInfo[2] = produto.getQuantidade();
                produtoInfo[3] = produto.getValor();
                produtoInfo[4] = produto.isAdicional();
                dados.add(produtoInfo);
            }// Criar um array de objetos para armazenar as informações
        }
        
        return dados;
    }

    @FXML
    void adicionar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource("VE/dialogs/adicionar_produto.fxml"));
        Scene scene = new Scene(root);

        // Cria uma nova janela de diálogo
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Configura como uma janela de diálogo modal
        //dialogStage.initOwner().getScene().getWindow()); // Define a janela pai
        dialogStage.setScene(scene);

        // Define um título para a janela de diálogo (opcional)
        //dialogStage.setTitle("addpedido");
        // Exibe a janela de diálogo
        dialogStage.showAndWait();
    }


    @FXML
    void delete(ActionEvent event, Long id) throws IOException {
        System.out.println("ID que será apagado é: "+ id);
        ExcluirCadastroController.produto_id = id;
        Parent root = FXMLLoader.load(App.class.getResource("VE/dialogs/excluir_cadastro.fxml"));
        Scene scene = new Scene(root);

        // Cria uma nova janela de diálogo
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Configura como uma janela de diálogo modal
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    @FXML
    void edit(ActionEvent event, Long id, String nome, String quantidade, String valor, Boolean isAdicional) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("VE/dialogs/editar_produto .fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        EditarProdutoController editarProduto = loader.getController();
        editarProduto.id = id;
        editarProduto.setValoresEdicao(nome, quantidade, valor, isAdicional);

        // Cria uma nova janela de diálogo
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Object[]> dadosDoBanco = recuperarDadosDoBanco();

        for (Object[] dado : dadosDoBanco) {
            HBox hboxContainer = new HBox();

            Separator separator1 = new Separator();
            Separator separator2 = new Separator();
            Separator separator3 = new Separator();
            Separator separator4 = new Separator();
            
            HBox.setHgrow(separator1, Priority.ALWAYS);
            HBox.setHgrow(separator2, Priority.ALWAYS);
            HBox.setHgrow(separator4, Priority.ALWAYS);
            
            separator1.setVisible(false);
            separator2.setVisible(false);
            separator3.setVisible(false);
            separator4.setVisible(false);

            Hyperlink idLabel = new Hyperlink(String.valueOf(dado[0])); //id
            Label nomeLabel = new Label(String.valueOf(dado[1])); //nome
            Label quantidadeLabel = new Label(String.valueOf(dado[2])); //quantidade
            Label valorLabel = new Label(String.valueOf(dado[3])); //valor
            
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected((Boolean) dado[4]);
            Label booleanLabel = new Label("Adicional");
            
            Button button1 = new Button("Editar");
            Button button2 = new Button("Excluir");
            
            button1.setOnAction((ActionEvent event) -> {
                try {
                    edit(
                        event,
                        (Long)dado[0],
                        String.valueOf(dado[1]),
                        String.valueOf(dado[2]),
                        String.valueOf(dado[3]),
                        (Boolean) dado[4]
                    );
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

            hboxContainer.getChildren().addAll(
                idLabel,
                separator1,
                nomeLabel,
                separator2, 
                quantidadeLabel,
                separator3,
                valorLabel,
                separator4,
                booleanLabel,
                checkBox,
                button1,
                button2
            );

            hboxContainer.setSpacing(5);
            Insets padding = new Insets(10, 10, 10, 10);
            hboxContainer.setPadding(padding);
            hboxContainer.prefHeight(80);
            hboxContainer.getStyleClass().add("table_row");

            tabela.getChildren().add(hboxContainer);
        }
    }
}