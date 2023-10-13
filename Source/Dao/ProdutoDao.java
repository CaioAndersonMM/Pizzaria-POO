package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Produto;

public class ProdutoDao extends BaseDaoImp<Produto> {

    @Override
    public void alterar(Produto entity) {
        String sql = "UPDATE tb_produtos SET nome=?, quantidade=?, valor=? , is_adicional=? WHERE id=?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getNome());
            stmt.setInt(2, entity.getQuantidade());
            stmt.setFloat(3, entity.getValor());
            stmt.setBoolean(4, entity.isAdicional());
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
    public Produto buscar(Produto entity) {
        String sql = "SELECT * FROM tb_produtos as e WHERE e.id = ? ";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Crie um objeto Produto a partir dos dados do ResultSet e retorne-o
                Produto produto = new Produto(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getFloat("valor"),
                    rs.getBoolean("is_adicional")
                );
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

     public List<Produto> buscarPorNome(Produto entity) {
        String sql = "SELECT * FROM tb_produtos WHERE nome LIKE ?";
        String padrao = "%" + entity.getNome() + "%";

        connection = getConnection();
        List<Produto> produtos = new ArrayList<Produto>();
        

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, padrao);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getFloat("valor"),
                    rs.getBoolean("is_adicional")
                );
                
                produtos.add(produto);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return produtos;
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
        String sql = "INSERT INTO tb_produtos (nome, quantidade, valor, is_adicional) VALUES (?,?,?,?)";
        connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getNome());
            ps.setInt(2, entity.getQuantidade());
            ps.setFloat(3, entity.getValor());
            ps.setBoolean(4,entity.isAdicional());
            ps.execute();
            ps.close();

            sql = "SELECT * FROM tb_produtos as e WHERE e.nome=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getNome());
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
        List<Produto> produtos = new ArrayList<Produto>();
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Produto produto = new Produto(
                    resultado.getLong("id"),
                    resultado.getString("nome"),
                    resultado.getInt("quantidade"),
                    resultado.getFloat("valor"), 
                    resultado.getBoolean("is_adicional")
                );
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Considere um tratamento de exceção mais adequado
        } finally {
            closeConnection();
        }

        return produtos;
    }
}
