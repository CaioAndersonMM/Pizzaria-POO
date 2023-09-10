package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Pizza;
import Model.Entity.TipoPizza;
import Dao.TipoPizzaDao;

public class PizzaDao extends BaseDaoImp<Pizza> {
    @Override
    public Long inserir(Pizza entity) {
        String sql = "INSERT INTO tb_pizza (tipo, valor, tamanho) VALUES (?, ?, ?)";

        try {
            Connection con = getConnection();

            // Inserir ao banco
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, entity.getTipo().getId());
            stmt.setFloat(2, entity.getValor());
            stmt.setString(3, String.valueOf(entity.getTamanho()));
            stmt.execute();
            stmt.close();

            // Buscar pizza criada e retornar id
            sql = "SELECT * FROM tb_pizza as p WHERE p.tipo=?";
            stmt = con.prepareStatement(sql);
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
        String sql = "DELETE FROM tb_pizza as p WHERE p.id = ?";

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
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
        String sql = "UPDATE tb_pizza\n" +
                "SET tipo = ?, valor = ?, tamanho = ?\n" +
                "WHERE id = ?;";

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
        String sql = "SELECT * FROM tb_pizza as p WHERE p.id = ?";
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
        String sql = "SELECT * FROM tb_pizza";
        List<Pizza> pizzas = new ArrayList<Pizza>();
        TipoPizzaDao tipoPizzaDao = new TipoPizzaDao();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();

            while (rs.next()) {
                Pizza pizza = new Pizza();

                TipoPizza tipoPizza = new TipoPizza();
                tipoPizza.setId(rs.getLong("tipo"));
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
