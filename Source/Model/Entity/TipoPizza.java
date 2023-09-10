package Model.Entity;

import java.util.ArrayList;
import java.util.List;

public class TipoPizza {
    private Long id;
    private String nomeSabor;
    private List<Produto> ingredientes;
    private float[] valores;

    public TipoPizza() {
    }
    
    public TipoPizza(String nomeSabor, List<Produto> ingredientes, float[] valores) {
        setNomeSabor(nomeSabor);
        setIngredientes(ingredientes);
        setValores(valores);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            return;
        }
        this.id = id;
    }

    public float[] getValores() {
        return valores;
    }

    public void setValores(float[] valores) {
        // Verificar se foram informados três valores
        if (valores == null || valores.length != 3) {
            throw new IllegalArgumentException("Preços inválidos.");
        }

        // Verificar se há algum valor negativo
        for (int i = 0; i < 3; i++) {
            if (valores[i] < 0) {
                throw new IllegalArgumentException("Não é possível definir um preço negativo.");
            }
        }

        // Copiar array de float
        this.valores = new float[valores.length];
        System.arraycopy(valores, 0, this.valores, 0, valores.length);
    }

    public List<Produto> getIngredientes() {
        return new ArrayList<Produto>(this.ingredientes);
    }

    public void setIngredientes(List<Produto> ingredientes) {
        if (ingredientes == null) {
            return;
        }
        this.ingredientes = new ArrayList<Produto>(ingredientes);
    }

    public String getNomeSabor() {
        return nomeSabor;
    }

    public void setNomeSabor(String nomeSabor) {
        if (nomeSabor == null || nomeSabor.isEmpty()) {
            throw new IllegalArgumentException("O nome do sabor não pode ser nulo ou vazio.");
        }
        this.nomeSabor = nomeSabor;
    }
}
