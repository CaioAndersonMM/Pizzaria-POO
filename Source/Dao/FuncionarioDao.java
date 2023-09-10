package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Funcionario;

public class FuncionarioDao extends BaseDaoImp<Funcionario> {

    @Override
    public Long inserir(Funcionario entity) {

        String sql = "INSERT INTO Funcionario (cpf, nome, senha) VALUES (?, ?, ?)";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getCPF());
            stmt.setString(2, entity.getNome());
            stmt.setString(3, entity.getSenha());

            stmt.execute();
            stmt.close();

            sql = "SELECT * FROM Funcionario as e WHERE e.cpf=?";
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

        String sql = "DELETE FROM Funcionario as e WHERE e.cpf = ?";

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
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

        String sql = "UPDATE Funcionario\n" + //
                "SET nome = ?, cpf = ?\n" + //
                "WHERE id = ?;";

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getCPF());
            stmt.setLong(3, entity.getId());

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

        String sql = "SELECT * FROM Funcionario as e WHERE e.id = ?";

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();
            stmt.close();

            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                return funcionario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return entity;
    }

    @Override
    public List<Funcionario> listar() {

        String sql = "SELECT * FROM Funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getLong("id"));
                funcionario.setCPF(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));

                funcionarios.add(funcionario);
            }

            rs.close();
            stmt.close();
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