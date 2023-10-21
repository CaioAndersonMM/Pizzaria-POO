package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Dao.FuncionarioDao;
import Exception.AutenticationException;
import Model.Entity.Funcionario;

public class FuncionarioBo {
 	static private FuncionarioDao dao = new FuncionarioDao();
	static public boolean isAdminLogado;
	public Funcionario autenticar(Funcionario vo) throws AutenticationException{
		ResultSet rs = dao.buscarPorCPF(vo);
		try {
			if(rs.next()) {//encontrou funcionário
				if(rs.getString("senha").equals(vo.getSenha())) {
					Funcionario res = new Funcionario();
					res.setId(rs.getLong("id"));
					res = dao.buscar(res);
						if(res != null) {//cumprir o protocolo por desencargo de consciência
							isAdminLogado = res.isAdmin();
							return res;
						}
						else throw new AutenticationException();
					}
			    else throw new AutenticationException();
			} else throw new AutenticationException();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new AutenticationException();
		}
	}

	public void criar(Funcionario funcionario){
		dao.inserir(funcionario);
	}

	public List<Funcionario> listar(){
    	return dao.listar();
  	}

  public void editar(Long id, Funcionario funcionarionovo){
    Funcionario funcionario = new Funcionario();
    funcionario.setId(id);
    funcionario = dao.buscar(funcionario);
    dao.alterar(funcionarionovo);
  }

  public void deletar(Long id){
    Funcionario funcionario = new Funcionario();
    funcionario.setId(id);
	System.out.println(funcionario.getId());
    funcionario = dao.buscar(funcionario);
	System.out.println(funcionario.getCPF());
    dao.deletar(funcionario);
  }
}
