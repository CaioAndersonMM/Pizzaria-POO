package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Cliente;
import Model.Entity.Pedido;
import Model.Entity.Pizza;

public class PedidoDao extends BaseDaoImp<Pedido> {

    @Override
    public Long inserir(Pedido entity) {
        String sql = "INSERT INTO tb_pedidos (cliente, pizzas, valor, data, status)) "
                + "values (?,?,?,?,?)";
        connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // gerará chave inserçao
            ps.setObject(1, entity.getCliente());
            ps.setObject(2, entity.getPizzas());
            ps.setFloat(3, entity.getValor());
            ps.setDate(4, java.sql.Date.valueOf(entity.getData())); // converte o LocalDate para o banco
            ps.setBoolean(5, false); // pedido começa como false
            ps.execute();
            ps.close();

            // Obtém o ID gerado pelo banco
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deletar(Pedido entity) {
        String sql = "DELETE FROM tb_pedidos WHERE id = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void alterar(Pedido entity) {
        String sql = "UPDATE tb_pedidos SET cliente=?, pizzas=?, valor=?, data=?, status=? WHERE id=?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setObject(1, entity.getCliente());
            stmt.setObject(2, entity.getPizzas());
            stmt.setFloat(3, entity.getValor());
            stmt.setDate(4, java.sql.Date.valueOf(entity.getData()));
            stmt.setBoolean(5, entity.getStatus());
            stmt.setLong(6, entity.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public Pedido buscar(Pedido entity) {
        String sql = "SELECT * FROM tb_pedidos WHERE id = ?";
        connection = getConnection();

        Pedido pedido = new Pedido();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();
            stmt.close();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("cliente"));
                Pizza[] pizza = new Pizza[10];
                // pizza.setNome(rs.getString("pizza"));
                pedido = new Pedido();
                pedido.setId(rs.getLong("id"));
                pedido.setCliente(cliente);
                pedido.setPizzas(pizza);
                pedido.setValor(rs.getFloat("valor"));
                pedido.setData(rs.getDate("data").toLocalDate());
                pedido.setStatus(rs.getBoolean("status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    @Override
    public List<Pedido> listar() {
        String sql = "SELECT * FROM tb_pedidos";
        connection = getConnection();
        List<Pedido> pedidos = new ArrayList<>();
        ClienteDao clientedao = new ClienteDao();
        PizzaDao pizzadao = new PizzaDao();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            while (rs.next()) {
                Pedido pedido = new Pedido();

                Pizza pizza = new Pizza();
                Cliente cliente = new Cliente();
                pizza.setId(rs.getLong("pizza"));
                pizza = pizzadao.buscar(pizza);

                cliente.setId(rs.getLong("cliente"));
                cliente = clientedao.buscar(cliente);

                pedido.setId(rs.getLong("id"));
                pedido.setValor(rs.getFloat("valor"));
                pedido.setStatus(rs.getBoolean("status"));
                pedido.setData(rs.getDate("data").toLocalDate());
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return pedidos;
    }
}
