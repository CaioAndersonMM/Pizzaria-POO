package Utils;

import javafx.util.StringConverter;
import java.util.List;

import Dao.TipoPizzaDao;
import Model.Entity.TipoPizza;

public class TipoPizzaStringConverter extends StringConverter<TipoPizza> {
    static private TipoPizzaDao tipoPizzaDAO = new TipoPizzaDao();

    @Override
    public String toString(TipoPizza tipoPizza) {
        if (tipoPizza != null) {
            return tipoPizza.getNomeSabor();
        }
        return null;
    }

    @Override
    public TipoPizza fromString(String string) {
        List<TipoPizza> tiposPizzas = tipoPizzaDAO.buscarPorNome(string);
        
        if (tiposPizzas.size() > 0) {
            return tiposPizzas.get(0);
        }
        
        return null;
    }
}