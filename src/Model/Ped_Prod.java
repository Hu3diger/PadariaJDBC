package Model;

public class Ped_Prod {

    private int id;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;

    public Ped_Prod() {
    }

    public Ped_Prod(Pedido pedido, Produto produto, int quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quatidade) {
        this.quantidade = quatidade;
    }

    public double getPreco() {
        return this.quantidade * produto.getPreco();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
