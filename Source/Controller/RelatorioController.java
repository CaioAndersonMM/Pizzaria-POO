package Controller;

import Dao.DetalhesPedidoDAO;
import Model.Entity.DetalhesPedido;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.util.List;

public class RelatorioController {

    @FXML
    private TextField NomeCliente;

    @FXML
    private TextField dataFinal;

    @FXML
    private TextField dataInicial;

    @FXML
    private TextField saborPizza;

    @FXML
    private CheckBox status;

    @FXML
    private Button cancel;

    @FXML
    private Button financeiros;

    @FXML
    private Button pdf;

    @FXML
    void gerarDadosFinanceiros(ActionEvent event) {

    }

    @FXML
    void gerarPDF(ActionEvent event) {
        String nomeDoArquivo = "relatorio.pdf";
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nomeDoArquivo));
            documento.open();

            DetalhesPedidoDAO detalhesPedidoDAO = new DetalhesPedidoDAO();
            List<DetalhesPedido> detalhesPedidos = detalhesPedidoDAO.obterDetalhesPedidos(
                    getOrNull(NomeCliente.getText()),
                    getOrNull(saborPizza.getText()),
                    getOrNull(dataInicial.getText()),
                    getOrNull(dataFinal.getText()),
                    status.isSelected()
            );

            Paragraph titulo1 = new Paragraph("Relatorio");
            titulo1.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph titulo = new Paragraph("Detalhe de Pedidos com Base nos Critérios Selecionados");
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setSpacingAfter(20f);

            documento.add(titulo1);
            documento.add(titulo);
            documento.add(new Paragraph("----------------------------------------------------------------------"));

            for (DetalhesPedido detalhes : detalhesPedidos) {
                documento.add(new Paragraph("ID do Pedido: " + detalhes.getIdPedido()));
                documento.add(new Paragraph("Valor: " + detalhes.getValor()));
                documento.add(new Paragraph("Status: " + detalhes.getStatus()));
                documento.add(new Paragraph("Data: " + detalhes.getDataPedido()));
                documento.add(new Paragraph("Nome do Cliente: " + detalhes.getNomeCliente()));
                documento.add(new Paragraph("Sabor: " + detalhes.getSabor()));

              //  documento.add(new Paragraph("CPF do Cliente: " + detalhes.getCpfCliente()));
              //  documento.add(new Paragraph("Nome do Funcionário: " + detalhes.getNomeFuncionario()));

                documento.add(new Paragraph("----------------------------------------------------------------------"));
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

    private static String getOrNull(String value) { // VERIFICA SE O ARGUMENTO É VALIDO
        return value.isBlank() ? null : value;
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
