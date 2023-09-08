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
        // TODO Auto-generated method stub

    }

    @Override
    public Usuario buscar(Usuario entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletar(Usuario entity) {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub
        return null;
    }

}
