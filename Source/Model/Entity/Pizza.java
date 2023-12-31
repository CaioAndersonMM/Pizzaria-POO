package Model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private Long id;
    private TipoPizza tipo;
    private List<Produto> adicionais = new ArrayList<>();
    private float valor;
    private char tamanho;

    public Pizza() {
    }

    public Pizza(TipoPizza tipo, List<Produto> adicionais, float valor, char tamanho) {
        setTipo(tipo);
        setAdicionais(adicionais);
        setValor(valor);
        setTamanho(tamanho);
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

    public List<Produto> getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(List<Produto> adicionais) {
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
        if (tamanho == 'p' || tamanho == 'm' || tamanho == 'g') {
            this.tamanho = tamanho;
        } else {
            // throw new IllegalArgumentException("Tamanho da pizza inválido.");
        }
    }
}
