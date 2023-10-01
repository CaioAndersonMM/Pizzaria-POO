package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.TipoPizza;

public class TipoPizzaDao extends BaseDaoImp<TipoPizza> {
    @Override
    public Long inserir(TipoPizza entity) {
        String sql = "INSERT INTO tb_tipo_pizzas (nome_sabor, valor_p, valor_m, valor_g) VALUES (?, ?, ?, ?)";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getNomeSabor());
            stmt.setFloat(2, entity.getValores()[0]);
            stmt.setFloat(3, entity.getValores()[1]);
            stmt.setFloat(4, entity.getValores()[2]);
            stmt.execute();
            stmt.close();

            // Buscar tipo de pizza criado e retornar id
            sql = "SELECT * FROM tb_tipo_pizzas as tp WHERE tp.nome_sabor=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getNomeSabor());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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
    public void deletar(TipoPizza entity) {
        String sql = "DELETE FROM tb_tipo_pizzas as tp WHERE tp.id = ?";
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
    public void alterar(TipoPizza entity) {
        String sql = "UPDATE tb_tipo_pizzas\n" +
                "SET nome_sabor = ?, valor_p = ?, valor_m = ?, valor_g = ?\n" +
                "WHERE id = ?;";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, entity.getNomeSabor());
            stmt.setFloat(2, entity.getValores()[0]);
            stmt.setFloat(3, entity.getValores()[1]);
            stmt.setFloat(4, entity.getValores()[2]);
            stmt.setLong(5, entity.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public TipoPizza buscar(TipoPizza entity) {
        String sql = "SELECT * FROM tb_tipo_pizzas as tp WHERE tp.id = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                TipoPizza tipoPizza = new TipoPizza();
                float[] valores = new float[3];

                valores[0] = rs.getFloat("valor_p");
                valores[1] = rs.getFloat("valor_m");
                valores[2] = rs.getFloat("valor_g");

                tipoPizza.setId(rs.getLong("id"));
                tipoPizza.setNomeSabor(rs.getString("nomeSabor"));
                tipoPizza.setValores(valores);
                                
                return tipoPizza;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    @Override
    public List<TipoPizza> listar() {
        String sql = "SELECT * FROM tb_tipo_pizzas";
        connection = getConnection();

        List<TipoPizza> tiposPizza = new ArrayList<TipoPizza>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TipoPizza tipoPizza = new TipoPizza();
                float[] valores = new float[3];
                
                valores[0] = rs.getFloat("valor_p");
                valores[1] = rs.getFloat("valor_m");
                valores[2] = rs.getFloat("valor_g");

                tipoPizza.setId(rs.getLong("id"));
                tipoPizza.setNomeSabor(rs.getString("nomeSabor"));
                tipoPizza.setValores(valores);
                
                //tipoPizza.setIngredientes(rs.getString("id_produtos"));
                
                tiposPizza.add(tipoPizza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return tiposPizza;
    }

}
