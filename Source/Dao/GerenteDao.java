package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Entity.Gerente;

public class GerenteDao extends BaseDaoImp<Gerente> {

    @Override
    public void alterar(Gerente entity) {

        String sql = "UPDATE tb_gerentes\n" +
                "SET nome = ?, cpf = ?, senha=?\n" +
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
    public Gerente buscar(Gerente entity) {
        String sql = "SELECT * FROM tb_gerentes as e WHERE e.id = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();
            stmt.close();

            if (rs.next()) {
                Gerente gerente = new Gerente();
                return gerente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return null;
    }


    @Override
    public void deletar(Gerente entity) {
        String sql = "DELETE FROM tb_gerentes as e WHERE e.cpf = ?";
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
    public Long inserir(Gerente entity) {
        String sql = "UPDATE Gerente\n" + //
                "SET nome = ?, cpf = ?\n" + //
                "WHERE id = ?;";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, entity.getNome());
            stmt.setString(2, entity.getCPF());
            stmt.setString(3, entity.getSenha());
            // stmt.setLong(4, entity.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }



    @Override
    public List<Gerente> listar() {
            List<Gerente> listaGerentes = new ArrayList<>();
            String sql = "SELECT * FROM tb_gerentes";
            connection = getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Gerente gerente = new Gerente();
                    gerente.setId(rs.getLong("id"));
                    gerente.setCPF(rs.getString("cpf"));
                    gerente.setNome(rs.getString("nome"));
                    // Adicione outros atributos de Gerente conforme necessário
                    listaGerentes.add(gerente);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Considere um tratamento de exceção mais adequado
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                closeConnection();
            }

            return listaGerentes;
        }


    }
