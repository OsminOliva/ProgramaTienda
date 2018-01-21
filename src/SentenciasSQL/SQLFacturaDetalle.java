
package SentenciasSQL;

import Conexion.ClsConexion;
import DAOs.DAOFactura;
import DAOs.DAOFacturaDetalle;
import Modelos.FacturaDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLFacturaDetalle implements DAOs.DAOFacturaDetalle{
    
    final String insertar="BEGIN PA_FACTURA_DETALLE(?,?,?,?);END;";
    final String obtenerDetalles="SELECT*FROM FACTURA_DETALLE WHERE ID_LINEA=?";

    Connection cn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public SQLFacturaDetalle(Connection cn){
        this.cn=cn;
    }
    
    @Override
    public int insert(FacturaDetalle a) {
        
        try{
            stmt=cn.prepareStatement(insertar);
            stmt.setInt(1, a.getIdFactura());
            stmt.setInt(2,a.getIdProducto());
            stmt.setFloat(3, a.getPrecio());
            stmt.setInt(4, a.getCantidad());
            return stmt.executeUpdate();
                    
        }catch(SQLException ex){
            ex.printStackTrace();
            return 0;
        }finally{
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        
        
        
    }

    @Override
    public List<FacturaDetalle> listaDetalle(int idFactura) {
        List<FacturaDetalle>listaDetalle = new ArrayList<>();
        try{
            stmt = cn.prepareStatement(obtenerDetalles);
            stmt.setInt(1, idFactura);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaDetalle.add(convertir(rs));
            }
        }catch(SQLException ex){
            System.out.println("Error al traer detalles "+ ex);
            
        }
        return listaDetalle;
        
    }
    
    private FacturaDetalle convertir(ResultSet rs) throws SQLException{
        
        int idFactura = rs.getInt(2);
        int idProducto = rs.getInt(3);
        float precio = rs.getFloat(4);
        int cantidad = rs.getInt(5);
        FacturaDetalle factura = new FacturaDetalle(idFactura, idProducto, precio, cantidad);
        factura.setIdFacturaDetalle(rs.getInt(1));
        return factura;
        
        
        
    }
    
   
    
}
