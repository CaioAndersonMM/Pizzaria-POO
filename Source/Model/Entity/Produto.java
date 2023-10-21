package Model.Entity;

public class Produto {
    private long id;
    private String nome;
    private int quantidade;
    private float valor;
    private boolean isAdicional = false;
    
    public Produto(Long id,String nome, int quantidade, float valor, boolean isAdicional) {
        setId(id);
        setNome(nome);
        setQuantidade(quantidade);
        setValor(valor);
        setAdicional(isAdicional);
    }

    public Produto() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou uma string vazia.");
        }
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Não é possível criar um produto com quantidade negativa.");
        }
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do produto não pode ser negativo.");
        }
        this.valor = valor;
    }

    public long getId() {
        return this.id;
    }
    
    public boolean isAdicional() {
        return isAdicional;
    }

    public void setAdicional(boolean adicional) {
        isAdicional = adicional;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            return;
        }
        this.id = id;
    }
}
