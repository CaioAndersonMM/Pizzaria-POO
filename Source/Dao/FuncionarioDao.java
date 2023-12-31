package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Funcionario;

public class FuncionarioDao extends BaseDaoImp<Funcionario> {

    @Override
    public Long inserir(Funcionario entity) {
        String sql = "INSERT INTO tb_funcionarios (cpf, nome, senha, id_admin) VALUES (?, ?, ?, ?)";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getCPF());
            stmt.setString(2, entity.getNome());
            stmt.setString(3, entity.getSenha());
            stmt.setBoolean(4, entity.isAdmin());

            stmt.execute();
            stmt.close();

            sql = "SELECT * FROM tb_funcionarios as e WHERE e.cpf=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getCPF());
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
    public void deletar(Funcionario entity) {

        String sql = "DELETE FROM tb_funcionarios as e WHERE e.cpf = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getCPF());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void alterar(Funcionario entity) {

        String sql = "UPDATE tb_funcionarios\n" + //
        "SET nome = ?, cpf = ?, senha = ?\n" + //
        "WHERE id = ?;";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getCPF());
            stmt.setString(3, entity.getSenha());
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
    public Funcionario buscar(Funcionario entity) {

        String sql = "SELECT * FROM tb_funcionarios WHERE id = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getLong("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCPF(rs.getString("cpf"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setAdmin(rs.getBoolean("id_admin"));
                
                return funcionario;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return entity;
    }

    public ResultSet buscarPorCPF(Funcionario vo){
		String sql = "select * from tb_funcionarios where cpf = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
 		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCPF());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

    public List<Funcionario> buscarPorNome(Funcionario entity){
        String sql = "SELECT * FROM tb_funcionarios WHERE nome LIKE ?";
        String padrao = "%" + entity.getNome() + "%";

        connection = getConnection();
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
				
 		try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, padrao);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getLong("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCPF(rs.getString("cpf"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setAdmin(rs.getBoolean("id_admin"));
    
                
               funcionarios.add(funcionario);
            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}


    @Override
    public List<Funcionario> listar() {

        String sql = "SELECT * FROM tb_funcionarios";
        List<Funcionario> funcionarios = new ArrayList<>();
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getLong("id"));
                funcionario.setCPF(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setAdmin(rs.getBoolean("id_admin"));


                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return funcionarios;
    }
}