package Model.Entity;

public class DetalhesPedido {
    private int idPedido;
    private String sabor;
    private double valor;

    // private String cpfCliente;
    private String nomeCliente;

    // private String nomeFuncionario;
    private boolean status;
    private String dataPedido;

    public DetalhesPedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    // public String getCpfCliente() {
    //    return cpfCliente;
    // }

    // public void setCpfCliente(String cpfCliente) {
    //     this.cpfCliente = cpfCliente;
    // }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    // public String getNomeFuncionario() {
    //     return nomeFuncionario;
    // }

    //  public void setNomeFuncionario(String nomeFuncionario) {
    //     this.nomeFuncionario = nomeFuncionario;
    // }

    public String getDataPedido() {
        return dataPedido;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public boolean getStatus() {
        return status;
    }
}
