
package DAOs;

import Modelos.Proveedor;
import java.util.HashMap;
import java.util.List;


public interface DAOProveedor extends DAO<Proveedor,Long>{
    //aqui van los metodos especificos
    List<HashMap<String,Object>>listaProveedores();
    List<Proveedor>filtrarPorId(String id);
    List<Proveedor>filtrarPorNombre(String nombre);
    List<Proveedor>filtrarPorDpi(String dpi);
    Proveedor getPorNit(String nit);
}
