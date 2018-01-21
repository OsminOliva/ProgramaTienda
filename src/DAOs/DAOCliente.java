
package DAOs;

import Modelos.Cliente;
import java.util.HashMap;
import java.util.List;

public interface DAOCliente extends DAO<Cliente,Long>{
    
    
    Cliente obtenerPorNit(String nit);
    List<Cliente>filtrarPorId(String id);
    List<Cliente>filtrarPorApellido(String apellido);
    List<Cliente>filtrarPorNit(String nit);
    List<HashMap<String,Object>>obtenerClienteNombre();
    
    
}
