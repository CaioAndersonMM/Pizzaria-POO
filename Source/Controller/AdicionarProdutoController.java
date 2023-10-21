package Controller;

import Model.BO.ProdutoBo;
import Model.Entity.Produto;
import View.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdicionarProdutoController {

    @FXML
    private Button adicionar;

    @FXML
    private Button cancel;

    @FXML
    private TextField fabricante;

    @FXML
    private TextField nome;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField valor;

    @FXML
    private CheckBox booladicional;
    @FXML
    void adicionar(ActionEvent event) throws Exception {
        Produto prod = new Produto();
        prod.setNome(nome.getText());

        try {
            int quantidadeProduto = Integer.parseInt(quantidade.getText());
            prod.setQuantidade(quantidadeProduto);
        } catch (NumberFormatException e) {
            // tratar o exception de qtd
            throw new IllegalArgumentException("A quantidade deve ser um valor numérico válido.");
        }
        try {
            float valorProduto = Float.parseFloat(valor.getText());
            prod.setValor(valorProduto);
        } catch (NumberFormatException e) {
            // tratar exception de valor
            throw new IllegalArgumentException("O valor deve ser um número válido.");
        }

        prod.setAdicional(booladicional.isSelected());

        ProdutoBo bo = new ProdutoBo();
        bo.criar(prod);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        // ver qual tela esta ...
        App.telaEstoque();
    }
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}


