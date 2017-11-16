package Model;

import DAO.TelefoneDAO;
import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa {
    private int id;
    private String nome;

    public Pessoa() {
    }

    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
    
    public List<Telefone> getTelefones(){
        List<Telefone> telefones = new ArrayList();
        if(this instanceof Cliente){
            telefones = TelefoneDAO.findByCliente(id);
        }else if(this instanceof Funcionario){
            telefones = TelefoneDAO.findByFuncionario(id);
        }else if(this instanceof Fornecedor){
            telefones = TelefoneDAO.findByFornecedor(id);
        }
        return  telefones;
    }
}
