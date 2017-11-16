package Business;

import DAO.CargoFDAO;
import Model.CargoF;
import java.util.List;

public class CargoBusiness {
    
    public static List<CargoF> findAll(){
        return CargoFDAO.findAll();
    }
    
    public static CargoF findById(int id){
        return CargoFDAO.findById(id);
    }
    
        public static boolean update(int id, String nome) {
        for (CargoF cargos: CargoFDAO.findAll()) {
            if (cargos.getId() == id) {
                cargos.setNome(nome);
                CargoFDAO.update(cargos);
                return true;
            }
        }
        return false;
    }
        
        
    
}
