package Model.Entity;

public class Produto {
    private long id;
    private String nomeProduto;
    private String nomeFabricante;
    private int quatidadeProduto;
    private float valor;
    private boolean isAdicional = false;

    public Produto(Long id,String nomeProduto, String fabricante, int quatidadeProduto, float valor, boolean isAdicional) {
        setId(id);
        setNomeProduto(nomeProduto);
        setNomeFabricante(fabricante);
        setQuantidadeProduto(quatidadeProduto);
        setValor(valor);
        setAdicional(isAdicional);
    }

    public Produto() {

    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou uma string vazia.");
        }
        this.nomeProduto = nome;
    }

    public int getQuantidadeProduto() {
        return quatidadeProduto;
    }

    public void setQuantidadeProduto(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Não é possível criar um produto com quantidade negativa.");
        }
        this.quatidadeProduto = quantidade;
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

    public String getNomeFabricante() {
        return nomeFabricante;
    }

    public void setNomeFabricante(String nomeFabricante) {
        if (nomeFabricante == null || nomeFabricante.isEmpty()) {
            throw new IllegalArgumentException("O fabricante não pode ser nulo ou uma string vazia.");
        }
       this.nomeFabricante = nomeFabricante;

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
