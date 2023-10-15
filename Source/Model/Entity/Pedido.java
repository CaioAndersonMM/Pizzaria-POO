package Model.Entity;

import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private long id;
    private Cliente cliente;
    private List<Pizza> pizzas;
    private float valor;
    private LocalDate data;
    private boolean status;

    public Pedido() {
    }

    public Pedido(Cliente cliente, List<Pizza> pizzas, float valor, LocalDate data, boolean status) {
        setCliente(cliente);
        setPizzas(pizzas);
        setValor(valor);
        setData(data);
        setStatus(status);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente != null) {
            this.cliente = cliente;
        } else {
            // throw new IllegalArgumentException("Deve ter um cliente");
        }
    }

    public List<Pizza> getPizzas() {
        return this.pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        if (pizzas != null) {
            this.pizzas = pizzas;
        } else {
            // throw new IllegalArgumentException("Deve haver pizza!");
        }
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        if (valor > 0) {
            this.valor = valor;
        } else {
            // throw new IllegalArgumentException("O valor do pedido está inválido");
        }
    }

    public LocalDate getData() {
        return data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0)
            this.id = id;
    }

    public void setData(LocalDate data) {
        if (data != null)
            this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void adicionarCliente(Cliente cliente) {
        setCliente(cliente);
    }

    public void alterarCliente(Cliente novoCliente) {
        setCliente(novoCliente);
    }

    public void alterarStatus(boolean novoStatus) {
        setStatus(novoStatus);
    }

    public void adicionarPizza(Pizza novaPizza) {
        List<Pizza> pizzas = getPizzas();
        pizzas.add(novaPizza);
        setPizzas(pizzas);
        System.out.println("Pizza Adicionada no Pedido");
    }

    public void alterarPizza(Pizza pizza) {
        // setPizzas(pizza); -> deve ter uma implementação diferente
    }

    public void removerPedido(Pedido pedido) {
        System.out.println("Pedido Removido");
    }

    public float calcularValor() {
        return valor; // alterar
    }

    public void buscarPizza(Pizza pizza) {

    }

    public void buscarCliente(Cliente cliente) {

    }

    public void buscarStatus(boolean status) {

    }
}
