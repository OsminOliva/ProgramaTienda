
package DAOs;

import Modelos.FacturaDetalle;
import java.util.List;


public interface DAOFacturaDetalle {
    
    int insert(FacturaDetalle a);
    List<FacturaDetalle>listaDetalle(int idFactura);
    
}
