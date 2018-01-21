
package DAOs;

import Modelos.Empleado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface DAOEmpleado extends DAO<Empleado, Long>{
    
    Empleado getName(String user);
    Boolean inicioSesion(String user,String contra,String tipoUsuario);
    List<Empleado>filtrarPorId(String id);
    List<Empleado>filtrarPorApellido(String apellido);
    List<Empleado>filtrarPorDpi(String dpi);
    List<HashMap<String,Object>>listaEmpleado();
    
    
}
