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

public class EditarProdutoController {

    @FXML
    private Button Salvar;

    @FXML
    private CheckBox booladicional;

    @FXML
    private Button cancel;

    @FXML
    private TextField nome;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField valor;

    public static Long id;

    public void initialize(Produto produto) {
        nome.setText(produto.getNome());
        quantidade.setText(Integer.toString(produto.getQuantidade()));
        valor.setText(Float.toString(produto.getValor()));
        booladicional.setSelected(produto.isAdicional());
    }

    @FXML
    void salvar(ActionEvent event) throws Exception {
        Produto produto = new Produto();
        produto.setNome(nome.getText());

        try {
            int quantidadeProduto = Integer.parseInt(quantidade.getText());
            produto.setQuantidade(quantidadeProduto);
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

        produto.setId(id);

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

    public void setValoresEdicao(String nomeE, String quantidadeE, String valorE, Boolean s) {
        nome.setText(nomeE);
        quantidade.setText(quantidadeE);
        valor.setText(valorE);
        booladicional.setSelected(s);
    }
}



