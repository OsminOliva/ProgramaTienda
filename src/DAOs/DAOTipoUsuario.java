
package DAOs;

import Modelos.TipoUsuario;
import java.util.HashMap;
import java.util.List;


public interface DAOTipoUsuario extends DAO<TipoUsuario,Long> {
    
    TipoUsuario getUser(String a);
    List<TipoUsuario>getDescripcion();
    List<HashMap<String,Object>>obtenerDescripciones();
    List<TipoUsuario>filtrarPorId(String id);
    List<TipoUsuario>filtrarPorDescripcion(String des);
    
    
    
}
