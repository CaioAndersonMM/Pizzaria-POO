package Model.Entity;

import java.util.Map;

public class Pizza {
    private TipoPizza tipo;
    private Map<Produto, Integer> adicionais;
    private float valor;
    private char tamanho;

    public Pizza() {
    }

    // construtor
    public Pizza(TipoPizza tipo, Map<Produto, Integer> adicionais, float valor, char tamanho) {
        setTipo(tipo);
        setAdicionais(adicionais);
        setValor(valor);
        setTamanho(tamanho);
    }

    public TipoPizza getTipo() {
        return tipo;
    }

    public void setTipo(TipoPizza novotipo) {
        if (novotipo != null) {
            this.tipo = novotipo;
        } else {
            //
        }
    }

    public Map<Produto, Integer> getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(Map<Produto, Integer> adicionais) {
        if (adicionais != null) {
            this.adicionais = adicionais;
        } else {
            // throw new IllegalArgumentException("A lista de adicionais não pode ser
            // nula.");
        }
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        if (valor > 0) {
            this.valor = valor;
        } else {
            // throw new IllegalArgumentException("O valor da pizza está inválido");
        }
    }

    public char getTamanho() {
        return tamanho;
    }

    public void setTamanho(char tamanho) {
        if (tamanho == 'P' || tamanho == 'M' || tamanho == 'G') {
            this.tamanho = tamanho;
        } else {
            // throw new IllegalArgumentException("Tamanho da pizza inválido.");
        }
    }

    public void alterarValor(float novoValor) {
        setValor(novoValor);
    }

    public void alterarTipo(TipoPizza novoTipo) {
        setTipo(novoTipo);
    }

    public void alterarTamanho(char novoTamanho) {
        setTamanho(novoTamanho);
    }

    public float calcularValor() {
        return valor;
    }

    // public Pizza buscar(String nome) {
    // return Pizza;
    // }

    public void adicionarAdicional(Produto adicional, int quantidade) {
        System.out.println("Adicionado");
    }

    public void removerAdicional(Produto adicional) {
        System.out.println("Removido");
    }

}
