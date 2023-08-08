import java.util.Map;
import java.util.Objects;

public class TipoPizza {
    private String nomeSabor;
    private Map<Produto, Integer> ingredientes;
    private float valores;

    public TipoPizza(String nomeSabor, Map<Produto, Integer> ingredientes, float valores) {
        this.nomeSabor = nomeSabor;
        this.ingredientes = ingredientes;
        this.valores = valores;
    }

    public float getValores() {
        return valores;
    }

    public Map<Produto, Integer> getIngredientes() {
        return ingredientes;
    }

    public String getNomeSabor() {
        return nomeSabor;
    }
}
