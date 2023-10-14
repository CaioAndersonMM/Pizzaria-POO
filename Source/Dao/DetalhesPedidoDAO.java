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
            String consultaSQL = "SELECT * FROM vw_detalhes_pedidos5";
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
}
