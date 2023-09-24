package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.GerenteDao;
import Exception.AutenticationException;
import Exception.InsertException;
import Model.Entity.Gerente;

public class GerenteBo {
    static private GerenteDao dao = new GerenteDao();

    public Gerente autenticar(Gerente vo) throws AutenticationException{
		ResultSet rs = dao.buscarPorCPF(vo);
		try {
			if(rs.next()) {//encontrou gerente
				if(rs.getString("senha").equals(vo.getSenha())) {
					Gerente res = new Gerente();
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

    public void cadastrar(Gerente vo) throws InsertException {
		try {
            ResultSet rs = dao.buscarPorCPF(vo); //ID
			if (rs.next()) {
				throw new InsertException("Impossível cadastrar, pois já existe responsável com este CPF");
			}
			else {
				dao.inserir(vo);
			}
		}
		catch(SQLException e) {
			throw new InsertException(e.getMessage());
		}
	}
}
