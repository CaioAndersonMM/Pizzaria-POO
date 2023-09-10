package Dao;

import Model.Entity.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends BaseDaoImp<Usuario> {

    @Override
    public void alterar(Usuario entity) {
        String sql = "UPDATE Usuario SET nome=?, cpf=?, senha=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getCPF());
            stmt.setString(3, entity.getSenha());
            stmt.setLong(4, entity.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public Usuario buscar(Usuario entity) {
        String sql = "SELECT * FROM tb_users as e where e.nome =? or e.cpf = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getCPF());
            ResultSet resultado = stmt.executeQuery();

            // Verifica se foi encontrado
            if (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultado.getLong("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setCPF(resultado.getString("cpf"));
                // Defina outros atributos do usuário conforme necessário
                return usuario;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deletar(Usuario entity) {

        String sql = "DELETE FROM Usuario WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public Long inserir(Usuario usu) {
        String sql = "INSERT INTO tb_users (nome,cpf,senha) "
                + "values (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usu.getNome());
            // ps.setString(2, usu.getEmail());
            ps.setString(2, usu.getCPF());
            ps.setString(3, usu.getSenha());
            ps.execute();

            sql = "SELECT * FROM tb_users as e WHERE e.cpf=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, usu.getCPF());
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
    public List<Usuario> listar() {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> listUsu = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                try {
                    usuario.setId(resultado.getLong("id"));
                    usuario.setNome(resultado.getString("nome"));
                    usuario.setCPF(resultado.getString("cpf"));
                    listUsu.add(usuario);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return listUsu;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }
}