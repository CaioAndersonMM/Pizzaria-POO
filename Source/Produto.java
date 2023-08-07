public class Produto {
    private final String nomeProduto;
    private final int quatidadeProduto;
    private final float valor;

    public Produto(String nomeProduto, int quatidadeProduto, float valor) {
        this.nomeProduto = nomeProduto;
        this.quatidadeProduto = quatidadeProduto;
        this.valor = valor;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuatidadeProduto() {
        return quatidadeProduto;
    }

    public Float getValor() {
        return valor;
    }
}
