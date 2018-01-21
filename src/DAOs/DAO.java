
package DAOs;

import java.util.List;


public interface DAO <T,k>{
    void insertar();
    void actualizar();
    List<T> obtenerTodo();
    T obtenerUno(k id);
    
    
}
