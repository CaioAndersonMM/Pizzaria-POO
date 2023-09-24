package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Entity.Gerente;
import Model.VO.GerenteVO;

public class GerenteDao extends BaseDaoImp<GerenteVO> {

    @Override
    public void alterar(GerenteVO entity) {

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
    public GerenteVO buscar(GerenteVO entity) {
        String sql = "SELECT * FROM tb_gerentes WHERE id = ?";
        connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();
            stmt.close();

            if (rs.next()) {
                GerenteVO gerente = new GerenteVO();
                return gerente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public ResultSet buscarPorCPF(GerenteVO vo){
		String sql = "select * from tb_gerentes where cpf = ?";
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

    public ResultSet buscarPorNome(Gerente vo){
		String sql = "select * from tb_gerentes where name = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
				
 		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getNome());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

    @Override
    public void deletar(GerenteVO entity) {
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
    public Long inserir(GerenteVO entity) {
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
    public List<GerenteVO> listar() {
            List<GerenteVO> listaGerentes = new ArrayList<>();
            String sql = "SELECT * FROM tb_gerentes";
            connection = getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    GerenteVO gerente = new GerenteVO();
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
