
package DAOs;

import java.util.List;


public interface DAO <T,k>{
    void insertar(T a);
    void actualizar(T a);
    List<T> obtenerTodo();
    T obtenerUno(k id);
    
    
}
