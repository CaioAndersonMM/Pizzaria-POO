package Dao;

import Model.Entity.DetalhesPedido;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Dao.BaseDaoImp.connection;

public class DetalhesPedidoDAO {
    public List<DetalhesPedido> obterDetalhesPedidos() {
        List<DetalhesPedido> detalhesPedidos = new ArrayList<>();
        try {
            BaseDaoImp.connection = BaseDaoImp.getConnection();
            String consultaSQL = "SELECT * FROM vw_detalhes_pedidos";
            try (PreparedStatement stmt = connection.prepareStatement(consultaSQL);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    DetalhesPedido detalhes = new DetalhesPedido();
                    detalhes.setIdPedido(rs.getInt("id_pedido"));
                    detalhes.setValor(rs.getDouble("valor"));
                    detalhes.setStatus(rs.getBoolean("Status"));
                    detalhes.setDataPedido(rs.getString("data"));

                    detalhes.setNomeCliente(rs.getString("nome_cliente"));
                    detalhes.setSabor(rs.getString("sabor"));
                    // detalhes.setCpfCliente(rs.getString("cpf_cliente"));
                    // detalhes.setNomeFuncionario(rs.getString("nome_funcionario"));
                    detalhesPedidos.add(detalhes);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalhesPedidos;
    }

    public List<DetalhesPedido> obterDetalhesPedidos(String cliente, String sabor, String dataInicio, String dataFinal, Boolean status) {
        List<DetalhesPedido> detalhesPedidos = new ArrayList<>();
        try {

            BaseDaoImp.connection = BaseDaoImp.getConnection();

            try (PreparedStatement stmt = DetalhesFiltro.com(cliente, sabor, dataInicio, dataFinal, status).gerarQuery(connection);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    DetalhesPedido detalhes = new DetalhesPedido();
                    detalhes.setIdPedido(rs.getInt("id_pedido"));
                    detalhes.setValor(rs.getDouble("valor"));
                    detalhes.setStatus(rs.getBoolean("Status"));
                    detalhes.setDataPedido(rs.getString("data"));
                    detalhes.setNomeCliente(rs.getString("nome_cliente"));
                    detalhes.setSabor(rs.getString("sabor"));

                    detalhesPedidos.add(detalhes);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalhesPedidos;
    }

    public List<DetalhesPedido> obterDetalhesFinanceiros() {
        List<DetalhesPedido> detalhesPedidos = new ArrayList<>();
        try {
            BaseDaoImp.connection = BaseDaoImp.getConnection();
            String consultaSQL = "SELECT * FROM vw_detalhes_financeiro";
            try (PreparedStatement stmt = connection.prepareStatement(consultaSQL);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    DetalhesPedido detalhes = new DetalhesPedido();

                    detalhes.setGanho(rs.getDouble("ganhos"));
                    detalhes.setGasto(rs.getDouble("gastos"));

                    detalhesPedidos.add(detalhes);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalhesPedidos;
    }
    
    public double lucro() {
        double lucro = 0.0;
        try {
            BaseDaoImp.connection = BaseDaoImp.getConnection();
            String consultaSQL = "SELECT calcular_lucro() as lucro";
            try (PreparedStatement stmt = connection.prepareStatement(consultaSQL);
                ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    lucro = rs.getDouble("lucro");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lucro;
    }
    
}
