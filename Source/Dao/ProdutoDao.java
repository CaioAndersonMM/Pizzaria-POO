package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Produto;

public class ProdutoDao extends BaseDaoImp<Produto> {

    @Override
    public void alterar(Produto entity) {
        String sql = "UPDATE Produto SET nome=?, quantidade=?, valor=? WHERE id=?";
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, entity.getNomeProduto());
            stmt.setInt(2, entity.getQuatidadeProduto());
            stmt.setFloat(3, entity.getValor());
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
    public Produto buscar(Produto entity) {
        String sql = "SELECT * FROM produto as e WHERE e.nome = ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getNomeProduto());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Crie um objeto Produto a partir dos dados do ResultSet e retorne-o
                Produto produto = new Produto(rs.getString("nome"), rs.getInt("quantidade"), rs.getFloat("valor"));
                return produto;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deletar(Produto entity) {

        String sql = "DELETE FROM Produto WHERE id=?";
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
        String sql = "INSERT INTO Produto (nome, quatidade, valor) "
                + "values (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getNomeProduto());
            ps.setInt(2, entity.getQuatidadeProduto());
            ps.setFloat(3, entity.getValor());
            ps.execute();
            ps.close();

            sql = "SELECT * FROM Produto as e WHERE e.nomeProduto=?";
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
        String sql = "SELECT * FROM Produtos";
        List<Produto> listProd = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto(resultado.getString("nome"), resultado.getInt("quantidade"),
                        resultado.getFloat("valor"));

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
