package Model;

public class Cliente extends Pessoa implements iFisica {

    private String cpf;
    private boolean estado;
    

    public Cliente(String cpf) {
        this.cpf = cpf;
    }

    public Cliente() {
    }

    public Cliente(String cpf, boolean estado) {
        this.cpf = cpf;
        this.estado = estado;
    }

    public Cliente(String cpf, boolean estado, int id, String nome) {
        super(id, nome);
        this.cpf = cpf;
        this.estado = estado;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
/*
    public double getTotalGasto() {
        double gastoTotal = 0;
        for (int i = 0; i < Banco.getPedidos().size(); i++) {
            if (Banco.getPedidos().get(i).getCliente().getId() == this.getId()) {
                gastoTotal += Banco.getPedidos().get(i).getPrecoTotal();
            }
        }
        return gastoTotal;
    }
*/

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
