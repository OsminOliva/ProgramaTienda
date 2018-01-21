
package DAOs;

import Modelos.CategoriaProducto;
import java.util.HashMap;
import java.util.List;


public interface DAOCategoriaProducto extends DAO<CategoriaProducto,Long>{//heredamos de la plantilla DAO
    //aquí podrían ir metodos especificos de nuestra clase
     
    List<CategoriaProducto>filtrarCategoria(String categoria);
    List<CategoriaProducto>filtrarPorID(String categoria);
    List<HashMap<String,Object>>objectList();
    
}
