
package DAOs;

import Modelos.CompraDetalle;
import java.util.List;

public interface DAOCompraDetalle {
    
    int insertar(CompraDetalle a);
    List<CompraDetalle>listaDetalles(int idCompra);
    
    
    
    
    
    
}
