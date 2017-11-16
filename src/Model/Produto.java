package Model;
public class Produto {
    private int id;
    private String nome;
    private double preco;
    private Fornecedor fornecedor;
    private String descricao;
    private int quantidade;
    private boolean estado;

    public Produto() {
    }

    public Produto(String nome, double preco, Fornecedor fornecedor, String descricao, int quantidade, boolean estado) {
        this.nome = nome;
        this.preco = preco;
        this.fornecedor = fornecedor;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.estado = estado;
    }



    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
