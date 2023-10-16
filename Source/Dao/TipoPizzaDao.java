package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.TipoPizza;
import Model.Entity.Produto;

public class TipoPizzaDao extends BaseDaoImp<TipoPizza> {
    private List<Produto> buscarIngredientes(long tipoPizzaId) {
        String sql = (
              "SELECT P.* \n"
              + "FROM tb_produtos as P INNER JOIN tb_tipos_pizzas_ingredientes as Q\n"
              + "ON Q.id_ingrediente = P.id\n"
              + "WHERE Q.id_tipo_pizza = ?;"
        );
        List<Produto> ingredientes = new ArrayList<Produto>();
        
        try {    
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, tipoPizzaId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValor(rs.getFloat("valor"));
                produto.setAdicional(rs.getBoolean("is_adicional"));

                ingredientes.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return ingredientes;
    }
    
    private TipoPizza getTipoPizza(ResultSet rs) throws SQLException {
        TipoPizza tipoPizza = new TipoPizza();
        
        tipoPizza.setId(rs.getLong("id"));
        tipoPizza.setNomeSabor(rs.getString("nome"));
        tipoPizza.setValores(new float[] {
            rs.getFloat("valor_p"),
            rs.getFloat("valor_m"),
            rs.getFloat("valor_g")
        });

        List<Produto> ingredientes = buscarIngredientes(tipoPizza.getId());
        tipoPizza.setIngredientes(ingredientes);

        return tipoPizza;
    }
    
    public List<TipoPizza> buscarPorNome(String nome) {
        String sql = "SELECT * FROM tb_tipos_pizzas as tp WHERE tp.nome = ?";
        List<TipoPizza> resultado = new ArrayList<TipoPizza>();
        connection = getConnection();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resultado.add(getTipoPizza(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return resultado;
    }

    @Override
    public Long inserir(TipoPizza entity) {
        String sql = "INSERT INTO tb_tipos_pizzas (nome, valor_p, valor_m, valor_g) VALUES (?, ?, ?, ?)";
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
            sql = "SELECT * FROM tb_tipos_pizzas as tp WHERE tp.nome=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getNomeSabor());
            ResultSet rs = stmt.executeQuery();
        
            if (rs.next()) {
                sql = "INSERT INTO tb_tipos_pizzas_ingredientes (id_tipo_pizza, id_ingrediente, quantidade) VALUES (?, ?, ?)";
                
                PreparedStatement stmt2 = connection.prepareStatement(sql);

                stmt2 = connection.prepareStatement(sql);
                stmt2.setLong(1, rs.getLong("id"));

                List<Produto> produtos = entity.getIngredientes();
                List<Long> idsDosProdutos = new ArrayList<>();

                for (Produto produto : produtos) {
                    idsDosProdutos.add(produto.getId());
                }

                stmt2.setLong(2, idsDosProdutos.get(0));
                stmt2.setLong(3, 10);
                stmt2.execute();
                stmt2.close();

                
                sql = "INSERT INTO tb_tipos_pizzas_ingredientes (id_tipo_pizza, id_ingrediente, quantidade) VALUES (?, ?, ?)";

                PreparedStatement stmt3 = connection.prepareStatement(sql);

                stmt3 = connection.prepareStatement(sql);
                stmt3.setLong(1, rs.getLong("id"));

                stmt3.setLong(2, idsDosProdutos.get(1));
                stmt3.setLong(3, 10);
                stmt3.execute();
                stmt3.close();

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
    public void deletar(TipoPizza entity){
        String sql = "DELETE FROM tb_tipos_pizzas as tp WHERE tp.id = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Já existe Pizza associada a esse Tipo Pizza");
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void alterar(TipoPizza entity) {
        String sql = "UPDATE tb_tipos_pizzas\n" +
                "SET nome = ?, valor_p = ?, valor_m = ?, valor_g = ?\n" +
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
        String sql = "SELECT * FROM tb_tipos_pizzas as tp WHERE tp.id = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return getTipoPizza(rs);
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
        String sql = "SELECT * FROM tb_tipos_pizzas";
        connection = getConnection();

        List<TipoPizza> tiposPizza = new ArrayList<TipoPizza>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tiposPizza.add(getTipoPizza(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return tiposPizza;
    }
}
