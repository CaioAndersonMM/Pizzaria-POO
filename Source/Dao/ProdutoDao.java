package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Produto;

import javax.swing.text.html.parser.Entity;

public class ProdutoDao extends BaseDaoImp<Produto> {

    @Override
    public void alterar(Produto entity) {
        String sql = "UPDATE tb_produtos SET nome=?, fabricante =?, quantidade=?, valor=? , isadicional=? WHERE id=?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getNomeProduto());
            stmt.setString(2, entity.getNomeFabricante());
            stmt.setInt(3, entity.getQuantidadeProduto());
            stmt.setFloat(4, entity.getValor());
            stmt.setBoolean(5, entity.isAdicional());
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
    public Produto buscar(Produto entity) {
        String sql = "SELECT * FROM tb_produtos as e WHERE e.id = ? ";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Crie um objeto Produto a partir dos dados do ResultSet e retorne-o
                Produto produto = new Produto(rs.getLong("id") ,rs.getString("nome"), rs.getString("fabricante"), rs.getInt("quantidade"), rs.getFloat("valor"), rs.getBoolean("isAdicional"));
                return produto;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public void deletar(Produto entity) {

        String sql = "DELETE FROM tb_produtos as e WHERE e.id=?";
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
    public Long inserir(Produto entity) {
        String sql = "INSERT INTO tb_produtos (nome,fabricante,quantidade,valor,isAdicional) "
                + "values (?,?,?,?,?)";
        connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getNomeProduto());
            ps.setString(2, entity.getNomeFabricante());
            ps.setInt(3, entity.getQuantidadeProduto());
            ps.setFloat(4, entity.getValor());
            ps.setBoolean(5,entity.isAdicional());
            ps.execute();
            ps.close();

            sql = "SELECT * FROM tb_produtos as e WHERE e.nome=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getNomeProduto());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getLong("id");
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Produto> listar() {
        String sql = "SELECT * FROM tb_produtos";
        connection = getConnection();

        List<Produto> listProd = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto(resultado.getLong("id"), resultado.getString("nome"),resultado.getString("fabricante"), resultado.getInt("quantidade"),
                        resultado.getFloat("valor"), resultado.getBoolean("isAdicional"));

                listProd.add(produto);
            }
            return listProd;
        } catch (SQLException ex) {
            ex.printStackTrace(); // Considere um tratamento de exceção mais adequado
            return null;
        } finally {
            closeConnection();
        }
    }
}
