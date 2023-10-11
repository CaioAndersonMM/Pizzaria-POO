package Model.BO;

import Dao.ProdutoDao;
import Model.Entity.Cliente;
import Model.Entity.Produto;

public class ProdutoBo {
    static private ProdutoDao dao = new ProdutoDao();


    public void criar(Produto produto){
        dao.inserir(produto);
    }

    public void editar(Produto produtoeditado) {
        Produto produto = new Produto();
        produto= dao.buscar(produto);
        dao.alterar(produtoeditado);
    }
}
