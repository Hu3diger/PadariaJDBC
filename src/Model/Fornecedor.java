package Model;
public class Fornecedor extends Pessoa implements iJuridica{
    private String cnpj;

    public Fornecedor(String cnpj) {
        this.cnpj = cnpj;
    }

    public Fornecedor() {
    }
    
    

    public Fornecedor(String cnpj, int id, String nome) {
        super(id, nome);
        this.cnpj = cnpj;
    }
    
    
    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
}
