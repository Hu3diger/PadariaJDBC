package Model;

import java.sql.Date;
import java.util.List;

public class Pedido{
    private int id;
    private Date dataPedido;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Ped_Prod> produtos;
    private FormaPagamento formapagamento;

    public Pedido() {
    }

    public Pedido(int id) {
        this.id = id;
    }
    
    

    public Pedido(Date dataPedido, Cliente cliente, Funcionario funcionario, FormaPagamento formapagamento) {
        this.dataPedido = dataPedido;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.formapagamento = formapagamento;
    }
    

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Ped_Prod> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Ped_Prod> produtos) {
        this.produtos = produtos;
    }

    public double getPrecoTotal(){
       double preco = 0;
        for (int i = 0; i < produtos.size(); i++) {
            preco += produtos.get(i).getPreco();
        }
        if(this.formapagamento == FormaPagamento.DINHEIRO){
            preco -= preco*0.05;
        }
        return preco;
    }
    
    

    public FormaPagamento getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(FormaPagamento formapagamento) {
        this.formapagamento = formapagamento;
    }
    
    
}
