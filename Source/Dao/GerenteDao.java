package Dao;

import java.util.List;
import Model.Entity.Gerente;

public class GerenteDao extends BaseDaoImp<Gerente> {

    @Override
    public void alterar(Gerente entity) {

        String sql = "UPDATE Gerente\n" + 
                    "SET nome = ?, endereco = ?, cpf = ?\n" + 
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
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
    
    @Override
    public Gerente buscar(Gerente entity) {
    String sql = "SELECT * FROM Gerente as e WHERE e.id = ?";

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, entity.getId());

            ResultSet rs = stmt.executeQuery();
            stmt.close();

            if (rs.next()) {
                Gerente gerente = new Gerente();
                return gerente;
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    
        return null;
    }

    @Override
    public void deletar(Gerente entity) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM Gerente as e WHERE e.cpf = ?";

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, entity.getCpf());
            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    

    @Override
    public Long inserir(Gerente entity) {
        String sql = "UPDATE Gerente\n" + //
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
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
        

    @Override
     public List<Gerente> listar() {
            String sql = "SELECT * FROM Gerente";
            List<Gerente > gerente = new ArrayList<Gerente >();
    
            try {
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                stmt.close();

                while (rs.next()) {
                    Gerente gerente = new Gerente();
                    try {
                        gerente.setId(rs.getLong("id"));
                        gerente.setCpf(rs.getString("cpf"));
                        gerente.setEndereco(rs.getString("endereco"));
                        gerente.setNome(rs.getString("nome"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    gerente.add(gerente);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }

            return gerente;
        }
   
