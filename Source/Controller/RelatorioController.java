package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    private Button adicionar;

    @FXML
    private Button cancel;

    @FXML
    void adicionar(ActionEvent event) {
        String nomeDoArquivo = "exemplo.pdf";
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nomeDoArquivo));
            documento.open();

            DetalhesPedidoDAO detalhesPedidoDAO = new DetalhesPedidoDAO();
            List<DetalhesPedido> detalhesPedidos = detalhesPedidoDAO.obterDetalhesPedidos();

            Paragraph titulo = new Paragraph("Detalhe de Todos os Pedidos");
            titulo.setAlignment(Paragraph.ALIGN_CENTER); // Alinhe o título ao centro
            titulo.setSpacingAfter(20f); // Adicione espaço após o título
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
