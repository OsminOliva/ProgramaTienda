
package SentenciasSQL;

import DAOs.DAOCompraDetalle;
import Modelos.CompraDetalle;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLCompraDetalle implements DAOCompraDetalle{
    
    final String insertar="BEGIN PA_COMPRAR_DETALLE(?,?,?,?);END;";
    final String obtenerCompraDetalle="SELECT * FROM COMPRA_DETALLE WHERE ID_COMPRA =?";
    
    //VARIABLES
    PreparedStatement  stmt = null;
    Connection cn = null;
    ResultSet rs = null;
    
    public SQLCompraDetalle(Connection cn){
        this.cn = cn;
    }

    @Override
    public int insertar(CompraDetalle a) {
        int id =0;
        try{
            stmt = cn.prepareStatement(insertar);
            stmt.setInt(1, a.getIdCompra());
            stmt.setInt(2,a.getIdProducto());
            stmt.setFloat(3, a.getPrecio());
            stmt.setInt(4, a.getCantidad());
            return stmt.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("error al insertar compra detalle "+ex);
            return 0;
        }
        
        
        
    }

    @Override
    public List<CompraDetalle> listaDetalles(int idFactura) {
        
        List<CompraDetalle>lista = new ArrayList<>();
        try{
            stmt = cn.prepareStatement(obtenerCompraDetalle);
            stmt.setInt(1, idFactura);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                lista.add(convertir(rs));
            }
            
        }catch(SQLException ex){
            
            System.out.println("error al tener detalles de compra "+ ex);
        }
        
        return lista;
        
        
    }
    
    private CompraDetalle convertir(ResultSet rs) throws SQLException{
        
        int idCompra = rs.getInt(2);
        int idProd = rs.getInt(3);
        float precio = rs.getFloat(4);
        int cantidad = rs.getInt(5);
        CompraDetalle compra = new CompraDetalle(idCompra, idProd, precio, cantidad);
        compra.setIdCompraDetalle(rs.getInt(1));
        return compra;
        
        
        
        
    }
    
    
    
}
