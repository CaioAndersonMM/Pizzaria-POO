package Model.BO;

import java.util.List;

import Dao.TipoPizzaDao;
import Model.Entity.TipoPizza;

public class TipoPizzaBo {
      static private TipoPizzaDao dao = new TipoPizzaDao();

    public List<TipoPizza> listar(){
        return dao.listar(); // Recupere e retorne a lista de pizzas
	}
}
