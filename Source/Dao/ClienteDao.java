package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import Model.Entity.Cliente;

public class ClienteDao extends BaseDaoImp<Cliente> {
    @Override
    public Long inserir(Cliente entity) {
        String sql = "INSERT INTO tb_clientes (cpf, endereco, nome) VALUES (?, ?, ?)";
        connection = getConnection();

        try {
             // Verificar se já existe um cliente com o mesmo CPF
            String checkSql = "SELECT id FROM tb_clientes WHERE cpf=?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            checkStmt.setString(1, entity.getCPF());
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                // Se já existe um cliente com o mesmo CPF
                return checkRs.getLong("id");
            } else {

            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, entity.getCPF());
            stmt.setString(2, entity.getEndereco());
            stmt.setString(3, entity.getNome());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                return null;
            }
        }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    @Override
    public void deletar(Cliente entity) {
        String sql = "DELETE FROM tb_clientes WHERE cpf = ?";
        connection = getConnection();
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
    public void alterar(Cliente entity) {
        String sql = "UPDATE tb_clientes\n" + //
                "SET nome = ?, endereco = ?, cpf = ?\n" + //
                "WHERE id = ?;";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEndereco());
            stmt.setString(3, entity.getCPF());
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
    public Cliente buscar(Cliente entity) {
        String sql = "SELECT * FROM tb_clientes WHERE id = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }

    @Override
    public List<Cliente> listar() {
        String sql = "SELECT * FROM tb_clientes";
        List<Cliente> clientes = new ArrayList<Cliente>();
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                try {
                    cliente.setId(rs.getLong("id"));
                    cliente.setCPF(rs.getString("cpf"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setNome(rs.getString("nome"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return clientes;
    }
}
