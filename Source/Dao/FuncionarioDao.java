package Dao;

import java.util.List;

import Model.Entity.Funcionario;

public class FuncionarioDao extends BaseDaoImp<Funcionario> {

    @Override
    public Long inserir(Funcionario entity) {
        
       String sql = "INSERT INTO Funcionario (cpf, endereco, nome, senha) VALUES (?, ?, ?, ?)";

        try {
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, entity.getCPF());
            stmt.setString(2, entity.getEndereco());
            stmt.setString(3, entity.getNome());
            stmt.setString(3, entity.getSenha());
            
            stmt.execute();
            stmt.close();

            sql = "SELECT * FROM Funcionario as e WHERE e.cpf=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, entity.getCpf());
            ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
                return rs.getLong("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deletar(Funcionario entity) {
        
        String sql = "DELETE FROM Funcionario as e WHERE e.cpf = ?";

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
    public void alterar(Funcionario entity) {
      
        String sql = "UPDATE Funcionario\n" + //
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

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Funcionario> listar() {

        String sql = "SELECT * FROM Funcionario";
        List<Funcionario> funcionario = new ArrayList<Funcionario>();

        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                try {
                    cliente.setId(rs.getLong("id"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setNome(rs.getString("nome"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                funcionario.add(funcionario);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return funcionario;
    }
}
   
