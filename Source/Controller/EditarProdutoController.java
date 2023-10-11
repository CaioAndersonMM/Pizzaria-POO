package Controller;

import Model.BO.ClienteBo;
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

public class EditarProdutoController {

    @FXML
    private Button Salvar;

    @FXML
    private CheckBox booladicional;

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
    private Object produto;

    public void initialize(Produto produto) {

        nome.setText(produto.getNomeProduto());
        fabricante.setText(produto.getNomeFabricante());
        quantidade.setText(Integer.toString(produto.getQuantidadeProduto()));
        valor.setText(Float.toString(produto.getValor()));
        booladicional.setSelected(produto.isAdicional());

    }

    @FXML
    void salvar(ActionEvent event) throws Exception {

        Produto produto = new Produto();

        produto.setNomeProduto(nome.getText());
        produto.setNomeFabricante(fabricante.getText());

        try {
            int quantidadeProduto = Integer.parseInt(quantidade.getText());
            produto.setQuantidadeProduto(quantidadeProduto);
        } catch (NumberFormatException e) {
            // Tratar exceção se a quantidade não for um número válido
            throw new IllegalArgumentException("A quantidade deve ser um valor numérico válido.");
        }

        try {
            float valorProduto = Float.parseFloat(valor.getText());
            produto.setValor(valorProduto);
        } catch (NumberFormatException e) {
            // Tratar exceção se o valor não for um número válido
            throw new IllegalArgumentException("O valor deve ser um número válido.");
        }

        produto.setAdicional(booladicional.isSelected());

        ProdutoBo bo = new ProdutoBo();
        bo.editar(produto);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        App.telaEstoque();
    }
    @FXML
    void cancelar(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}



