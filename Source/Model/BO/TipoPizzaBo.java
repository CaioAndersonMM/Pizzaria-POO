package Model.BO;

import java.util.List;

import Dao.TipoPizzaDao;
import Model.Entity.TipoPizza;

public class TipoPizzaBo {
  static private TipoPizzaDao dao = new TipoPizzaDao();

  public List<TipoPizza> listar(){
    return dao.listar(); // Recupere e retorne a lista de pizzas
  }

  public void editar(Long id, TipoPizza pizzanova){
    TipoPizza pizza = new TipoPizza();
    pizza.setId(id);
    pizza = dao.buscar(pizza);
    dao.alterar(pizzanova);
  }

  public void deletar(Long id){
    TipoPizza pizza = new TipoPizza();
    pizza.setId(id);
    pizza = dao.buscar(pizza);
    dao.deletar(pizza);
  }

   public void criar(TipoPizza pizza){
    dao.inserir(pizza);
  }

}
