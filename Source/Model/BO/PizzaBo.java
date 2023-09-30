package Model.BO;


import java.util.List;
import Dao.PizzaDao;
import Model.Entity.Pizza;

public class PizzaBo {
    static private PizzaDao dao = new PizzaDao();

    public List<Pizza> listar(){
        return dao.listar(); // Recupere e retorne a lista de pizzas
	}
}
