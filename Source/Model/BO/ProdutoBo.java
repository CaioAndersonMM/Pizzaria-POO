package Model.BO;

import Dao.ProdutoDao;
import Model.Entity.Cliente;
import Model.Entity.Produto;
import Model.Entity.TipoPizza;

import java.util.List;

public class ProdutoBo {
    static private ProdutoDao dao = new ProdutoDao();

    public List<Produto> listar(){
        return dao.listar(); // Recupere e retorne a lista de pizzas
    }
    public void criar(Produto produto){
        dao.inserir(produto);
    }

    public void editar(Produto produtoeditado) {
        Produto produto = new Produto();
        produto= dao.buscar(produto);
        dao.alterar(produtoeditado);
    }

    public void deletar(Long produtoId) {
        Produto produto = new Produto();
        produto.setId(produtoId);
        produto = dao.buscar(produto);
        dao.deletar(produto);
    }
}
