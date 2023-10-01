package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.FuncionarioDao;
import Exception.AutenticationException;
import Model.Entity.Funcionario;

public class FuncionarioBo {
 static private FuncionarioDao dao = new FuncionarioDao();

 public Funcionario autenticar(Funcionario vo) throws AutenticationException{
		ResultSet rs = dao.buscarPorCPF(vo);
		try {
			if(rs.next()) {//encontrou funcionário
				if(rs.getString("senha").equals(vo.getSenha())) {
					Funcionario res = new Funcionario();
					res.setId(rs.getLong("id"));
					res = dao.buscar(res);
						if(res != null) {//cumprir o protocolo por desencargo de consciência
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

}
