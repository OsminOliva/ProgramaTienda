
package DAOs;

import Modelos.Factura;
import java.util.List;


public interface DAOFactura {
    
   int insertar(Factura a);
   int getIdFactura();
   List<Factura>getFechas(String fecha1,String fecha2);
    
}
