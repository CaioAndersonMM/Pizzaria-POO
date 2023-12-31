package Model.BO;

import java.util.List;

public interface BaseInterBO<VO> {
	public void cadastrar(VO vo);
	public void buscarPorId(VO vo);
	public List<VO> listar();
	public void alterar(VO vo);
	public void remover(VO vo);
}