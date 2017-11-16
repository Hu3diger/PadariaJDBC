package Business;

import DAO.TelefoneDAO;
import Model.Pessoa;
import Model.Telefone;

public class TelefoneBusiness {

    public static void createTelefone(String numero, Pessoa dono) {
        Telefone telefone = new Telefone();
        telefone.setNumero(numero);
        telefone.setDono(dono);
        TelefoneDAO.create(telefone);
    }

    public static boolean deleteFone(int id) {
        if (TelefoneDAO.findById(id) != null) {
            TelefoneDAO.delete(id);
            return true;
        }
        return false;
    }

    public static boolean verifyDonoTelefone(int idTel, Pessoa dono) {
        try {
            if (TelefoneDAO.findById(idTel).getDono().getId() == dono.getId()) {
                Class<? extends Pessoa> classe1 = TelefoneDAO.findById(idTel).getDono().getClass();
                Class<? extends Pessoa> classe2 = dono.getClass();

                if (classe1 == classe2) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
