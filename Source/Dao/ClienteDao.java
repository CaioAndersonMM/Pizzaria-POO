package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Entity.Cliente;

public class ClienteDao extends BaseDaoImp<Cliente> {
    @Override
    public Long inserir(Cliente entity) {
        String sql = "INSERT INTO tb_cliente (cpf, endereco, nome) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getCPF());
            stmt.setString(2, entity.getEndereco());
            stmt.setString(3, entity.getNome());
            stmt.execute();
            stmt.close();

            sql = "SELECT * FROM tb_cliente as e WHERE e.cpf=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, entity.getCpf());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("id");
            } else {
                return null;
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
        String sql = "DELETE FROM tb_cliente as e WHERE e.cpf = ?";

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, entity.getCpf());
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
        String sql = "UPDATE tb_cliente\n" + //
                "SET nome = ?, endereco = ?, cpf = ?\n" + //
                "WHERE id = ?;";

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getEndereco());
            stmt.setString(3, entity.getCpf());
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
        String sql = "SELECT * FROM tb_cliente as e WHERE e.id = ?";

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();
            stmt.close();

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
        String sql = "SELECT * FROM tb_cliente";
        List<Cliente> clientes = new ArrayList<Cliente>();

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                try {
                    cliente.setId(rs.getLong("id"));
                    cliente.setCpf(rs.getString("cpf"));
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
