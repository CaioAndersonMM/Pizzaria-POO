package Model.BO;

import Dao.ProdutoDao;
import Model.Entity.Produto;

import java.util.List;

public class ProdutoBo {
    static private ProdutoDao dao = new ProdutoDao();

    public List<Produto> listar(){
        return dao.listar();
    }
    public void criar(Produto produto){
        dao.inserir(produto);
    }

    public void editar(Produto produtoEditado) {
        Produto produto = new Produto();
        produto = dao.buscar(produto);
        dao.alterar(produtoEditado);
    }

    public void deletar(Long produtoId) {
        Produto produto = new Produto();
        produto.setId(produtoId);
        produto = dao.buscar(produto);
        dao.deletar(produto);
    }
}
