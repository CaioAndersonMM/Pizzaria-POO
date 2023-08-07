import java.util.Map;
import java.util.Objects;

public class TipoPizza {
    private final String nomeSabor;
    private final Map<Produto, Integer> adicionais;
    private final float valores;

    public TipoPizza(String nomeSabor, Map<Produto, Integer> adicionais, float valores) {
        this.nomeSabor = nomeSabor;
        this.adicionais = adicionais;
        this.valores = valores;
    }

    public float getValores() {
        return valores;
    }

    public Map<Produto, Integer> getAdicionais() {
        return adicionais;
    }

    public String getNomeSabor() {
        return nomeSabor;
    }
}
