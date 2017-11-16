package Model;
public class Funcionario extends Pessoa implements iFisica{
    private String cpf;
    private CargoF cargo;
    private double Salario;
    private boolean estado;


    public Funcionario() {
    }

    public Funcionario(String cpf, CargoF cargo, double Salario, boolean estado) {
        this.cpf = cpf;
        this.cargo = cargo;
        this.Salario = Salario;
        this.estado = estado;
    }

    public Funcionario(String cpf, CargoF cargo, double Salario, boolean estado, int id, String nome) {
        super(id, nome);
        this.cpf = cpf;
        this.cargo = cargo;
        this.Salario = Salario;
        this.estado = estado;
    }

    public double getSalario() {
        return Salario;
    }

    public void setSalario(double Salario) {
        this.Salario = Salario;
    }
    public CargoF getCargo() {
        return cargo;
    }

    public void setCargo(CargoF cargo) {
        this.cargo = cargo;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    void setCargo(String Cargo) {
        this.cargo = cargo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
