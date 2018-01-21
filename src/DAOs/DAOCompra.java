
package DAOs;

import Modelos.Compra;
import java.util.List;


public interface DAOCompra {
    
    int insertar(Compra a);
    int getIdCompra();
    List<Compra>listaCompras(String fecha1,String fecha2);
    
}
