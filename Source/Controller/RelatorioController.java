package Controller;

import Model.BO.ClienteBo;
import Model.BO.ProdutoBo;
import Model.Entity.Cliente;
import Model.Entity.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;

import Dao.DetalhesPedidoDAO;
import Model.Entity.DetalhesPedido;

public class RelatorioController{

    @FXML
    private TextField NomeCliente;

    @FXML
    private Button cancel;

    @FXML
    private TextField dataFinal;

    @FXML
    private TextField dataInicial;

    @FXML
    private Button pdf;

    @FXML
    private TextField saborPizza;

    @FXML
    private CheckBox status;

    public static List<Cliente> filtrados;

    @FXML
    void gerarPDF(ActionEvent event) {
        String nomeDoArquivo = "exemplo.pdf";
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nomeDoArquivo));
            documento.open();

            DetalhesPedidoDAO detalhesPedidoDAO = new DetalhesPedidoDAO();
            List<DetalhesPedido> detalhesPedidos = detalhesPedidoDAO.obterDetalhesPedidos();

            Paragraph titulo = new Paragraph("Detalhe de Todos os Pedidos");
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setSpacingAfter(20f);
            documento.add(titulo);
            for (DetalhesPedido detalhes : detalhesPedidos) {
                documento.add(new Paragraph("ID do Pedido: " + detalhes.getIdPedido()));
                documento.add(new Paragraph("Valor: " + detalhes.getValor()));
                documento.add(new Paragraph("CPF do Cliente: " + detalhes.getCpfCliente()));
                documento.add(new Paragraph("Nome do Cliente: " + detalhes.getNomeCliente()));
                documento.add(new Paragraph("Nome do Funcionário: " + detalhes.getNomeFuncionario()));
                documento.add(new Paragraph("-------------------------------------------------"));
            }

            documento.close();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            System.out.println("PDF gerado com sucesso!");

        } catch (Exception e) {
            System.out.println(e);
            documento.close();
        }
    }
    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
