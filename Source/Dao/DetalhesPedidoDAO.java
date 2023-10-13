package Dao;

import static Dao.BaseDaoImp.connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Entity.DetalhesPedido;

public class DetalhesPedidoDAO{
    public List<DetalhesPedido> obterDetalhesPedidos() {
        List<DetalhesPedido> detalhesPedidos = new ArrayList<>();
        try{
            BaseDaoImp.connection = BaseDaoImp.getConnection();
            String consultaSQL = "SELECT * FROM vw_detalhes_pedidos";
            try (PreparedStatement stmt = connection.prepareStatement(consultaSQL);
                ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DetalhesPedido detalhes = new DetalhesPedido();
                    detalhes.setIdPedido(rs.getInt("id_pedido"));
                    detalhes.setValor(rs.getDouble("valor"));
                    detalhes.setCpfCliente(rs.getString("cpf_cliente"));
                    detalhes.setNomeCliente(rs.getString("nome_cliente"));
                    detalhes.setNomeFuncionario(rs.getString("nome_funcionario"));
                    detalhesPedidos.add(detalhes);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalhesPedidos;
    }
}
