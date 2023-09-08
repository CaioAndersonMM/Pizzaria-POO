package Model.Entity;

import java.util.Map;

public class TipoPizza {
    private String nomeSabor;
    private Map<Produto, Integer> ingredientes;
    private float[] valores;

    public TipoPizza(String nomeSabor, Map<Produto, Integer> ingredientes, float[] valores) {
        setNomeSabor(nomeSabor);
        setIngredientes(ingredientes);
        setValores(valores);
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

    public Map<Produto, Integer> getIngredientes() {
        return Map.copyOf(this.ingredientes);
    }

    public void setIngredientes(Map<Produto, Integer> ingredientes) {
        this.ingredientes = Map.copyOf(ingredientes);
    }

    public void addIngrediente(Produto ingrediente, Integer quantidade) {
        // Verificar se os valores informados são válidos
        if (ingrediente == null) {
            throw new IllegalArgumentException("O ingrediente não pode ser nulo.");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("Não é possível adicionar um produto com quantidade negativa.");
        }

        // Adicionar ingrediente
        this.ingredientes.put(ingrediente, quantidade);
    }

    public void removeIngrediente(Produto ingrediente) {
        this.ingredientes.remove(ingrediente);
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
