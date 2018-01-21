
package SentenciasSQL;

import Conexion.ClsConexion;
import DAOs.DAOFactura;
import Modelos.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class SQLFactura implements DAOFactura{
    
    final String insertar="BEGIN PA_FACTURA(?,?,?,?,?);END;";
    final String rangoFechas="SELECT*FROM FACTURA WHERE FECHA_FAC BETWEEN TO_DATE(?,'DD-MM-YYYY') AND TO_DATE(?,'DD-MM-YYYY')ORDER BY ID_FACTURA ASC";
    final String obtenerId="SELECT FACT_SEC.NEXTVAL FROM DUAL";
    PreparedStatement stmt= null;
    Connection cn=null;
    ResultSet rs = null;
    
    public SQLFactura(Connection cn){
        this.cn=cn;
    }
    
    @Override
    public int insertar(Factura a) {
        try {

            stmt = cn.prepareStatement(insertar);
            stmt.setInt(1,a.getIdFactura());
            stmt.setString(2, a.getSerie());
            stmt.setLong(3, a.getIdCliente());
            stmt.setInt(4, a.getIdEmpleado());
            stmt.setString(5, fechaString(a.getFechaFac()));
            return stmt.executeUpdate();
            

        } catch (SQLException ex) {
            System.out.println("error al insertar encabezado factura"+ ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return 0;
        
    }

    public String fechaString(Date fecha) {
        String retorno = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (fecha != null) {
            retorno = sdf.format(fecha);
        }
        return retorno;
    }
    
    @Override
    public int getIdFactura() {
        int id =0 ;
        try{
            stmt=cn.prepareStatement(obtenerId);
            rs=stmt.executeQuery();
            if(rs.next()){
                id= rs.getInt(1);
                return id;
                
            }
        }catch(SQLException ex){
            System.out.println("error al obtener el valor siguiente de la secuencia"+ ex);
            return 0;
        }
        
        
        return id;
    }
     

    @Override
    public List<Factura> getFechas(String fecha1, String fecha2) {
        List<Factura> listaFechas = new ArrayList<>();
        try {
            stmt = cn.prepareStatement(rangoFechas);
            stmt.setString(1, fecha1);
            stmt.setString(2, fecha2);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaFechas.add(convertir(rs));
            }

        } catch (SQLException ex) {
            System.out.print("error getFechas "+ex);
        }
        return listaFechas;
    }
    
    public Factura convertir(ResultSet rs) throws SQLException{
        String serie = rs.getString(2);
        int idCliente=rs.getInt(3);
        int idEmpleado = rs.getInt(4);
        Date fecha= rs.getDate(5);
        Factura factura = new Factura(serie, idCliente, idEmpleado, fecha);
        factura.setIdFactura(rs.getInt(1));
        return factura;
        
    }
    
    
}
