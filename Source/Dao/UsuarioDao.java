package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Model.Entity.Usuario;

public class UsuarioDao extends BaseDaoImp<Usuario> {

    @Override
    public void alterar(Usuario entity) {
     String sql = "UPDATE Usuario SET nome=?, cpf=?, telefone=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, Usuario.getNome());
            stmt.setString(2, Usuario.getCpf());
            stmt.setString(3, Usuario.getTelefone());
            stmt.setInt(4, Usuario.getId());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    @Override
    public Usuario buscar(Usuario entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletar(Usuario entity) {
        
            String sql = "DELETE FROM Usuario WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    public Long inserir(Usuario usu) {
        Connection con = getConnection();
        String sql = "INSERT INTO tb_users (nome,cpf,senha) "
                + "values (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usu.getNome());
            // ps.setString(2, usu.getEmail());
            ps.setString(2, usu.getCPF());
            ps.setString(3, usu.getSenha());
            ps.execute();
            ps.close();

            sql = "SELECT * FROM tb_users as e WHERE e.cpf=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, usu.getCPF());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getLong("id");
            else
                return null;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }

    @Override
    public List listar() { 
      String sql = "SELECT * FROM Usuario";
        List<Cliente> listUsu = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                Usuario.setId(resultado.getInt("id"));
                Usuario.setNome(resultado.getString("nome"));
                Usuario.setCpf(resultado.getString("cpf"));
                Usuario.setTelefone(resultado.getString("telefone"));
                retorno.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsu;
    }finally {
       closeConnection();
    }
}
