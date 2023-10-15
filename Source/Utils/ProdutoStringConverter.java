package Utils;

import javafx.util.StringConverter;
import java.util.List;

import Dao.ProdutoDao;
import Model.Entity.Produto;

public class ProdutoStringConverter extends StringConverter<Produto> {
    static private ProdutoDao produtoDAO = new ProdutoDao();

    @Override
    public String toString(Produto produto) {
        if (produto != null) {
            return produto.getNome();
        }
        return null;
    }

    @Override
    public Produto fromString(String string) {
        List<Produto> produtos = produtoDAO.buscarPorNome(string);

        if (produtos.size() > 0) {
            return produtos.get(0);
        }

        return null;
    }
}