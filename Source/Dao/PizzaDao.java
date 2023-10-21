package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Pizza;
import Model.Entity.TipoPizza;

public class PizzaDao extends BaseDaoImp<Pizza> {
    public void associarPizzaAoPedido(long pedidoId, long pizzaId) {
        String sql = "INSERT INTO tb_pedidos_pizzas (id_pedido, id_pizza) VALUES (?, ?)";
        connection = getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, pedidoId);
            ps.setLong(2, pizzaId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    
    @Override
    public Long inserir(Pizza entity) {
        String sql = "INSERT INTO tb_pizzas (id_tipo_pizza, valor, tamanho) VALUES (?, ?, ?)";
        connection = getConnection();
        try {
            // Inserir ao banco
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getTipo().getId());
            stmt.setFloat(2, entity.getValor());
            stmt.setString(3, String.valueOf(entity.getTamanho()));
            stmt.execute();
            stmt.close();

            // Buscar pizza criada e retornar id
            sql = "SELECT * FROM tb_pizzas as p WHERE p.id_tipo_pizza=?";
            stmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setLong(1, entity.getTipo().getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.last()) {
                return rs.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    @Override
    public void deletar(Pizza entity) {
        String sql = "DELETE FROM tb_pizzas as p WHERE p.id = ?";
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
    public void alterar(Pizza entity) {
        String sql = "UPDATE tb_pizzas\n" +
                "SET tipo = ?, valor = ?, tamanho = ?\n" +
                "WHERE id = ?;";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getTipo().getId());
            stmt.setFloat(2, entity.getValor());
            stmt.setString(3, String.valueOf(entity.getTamanho()));
            stmt.setLong(4, entity.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public Pizza buscar(Pizza entity) {
        String sql = "SELECT * FROM tb_pizzas as p WHERE p.id = ?";
        connection = getConnection();

        TipoPizzaDao tipoPizzaDao = new TipoPizzaDao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());
            ResultSet rs = stmt.executeQuery();
            stmt.close();

            if (rs.next()) {
                Pizza pizza = new Pizza();
                TipoPizza tipoPizza = new TipoPizza();
                tipoPizza.setId(rs.getLong("tipo"));
                tipoPizza = tipoPizzaDao.buscar(tipoPizza);

                pizza.setId(rs.getLong("id"));
                pizza.setTipo(tipoPizza);
                pizza.setValor(rs.getFloat("valor"));
                pizza.setTamanho(rs.getString("tamanho").charAt(0));
                return pizza;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    @Override
    public List<Pizza> listar() {
        String sql = "SELECT * FROM tb_pizzas";
        connection = getConnection();

        List<Pizza> pizzas = new ArrayList<Pizza>();
        TipoPizzaDao tipoPizzaDao = new TipoPizzaDao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pizza pizza = new Pizza();

                TipoPizza tipoPizza = new TipoPizza();
                tipoPizza.setId(rs.getLong("tipo_id"));
                tipoPizza = tipoPizzaDao.buscar(tipoPizza);

                pizza.setId(rs.getLong("id"));
                pizza.setValor(rs.getFloat("valor"));
                pizza.setTipo(tipoPizza);
                pizza.setTamanho(rs.getString("tamanho").charAt(0));
                pizzas.add(pizza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return pizzas;
    }
}
