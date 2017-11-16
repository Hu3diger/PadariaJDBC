package Model;
public class Telefone {
    private int id;
    private String numero;
    private Pessoa dono;

    public Telefone() {
    }

    public Telefone(int id, String numero, Pessoa dono) {
        this.id = id;
        this.numero = numero;
        this.dono = dono;
    }

    public Pessoa getDono() {
        return dono;
    }

    public void setDono(Pessoa dono) {
        this.dono = dono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
}
