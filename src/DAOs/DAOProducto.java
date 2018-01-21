
package DAOs;

import Modelos.Producto;
import java.util.List;


public interface DAOProducto extends DAO<Producto, Long>{
    //metodos especificos
    Producto getCodBarra(String cod);
    Producto getCodBarraP(String cod,long idProv);
    List<Producto>filtrarPorCod(String cod);
    List<Producto>filtrarPorNombre(String nombre);
    
    
}
