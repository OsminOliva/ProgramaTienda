package SentenciasSQL;

import Conexion.ClsConexion;
import DAOs.DAOCompra;
import Modelos.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SQLCompra implements DAOCompra{
    final String insertar="BEGIN PA_COMPRA(?,?,?,?);END;"; 
    final String getIdCompra="SELECT COMPRA_SEC.NEXTVAL FROM DUAL";
    final String rangoFechas="SELECT*FROM COMPRA WHERE FECHA_COMPRA BETWEEN TO_DATE(?,'DD-MM-YYYY') AND TO_DATE(?,'DD-MM-YYYY')ORDER BY ID_COMPRA ASC";
    //VARIABLES 
    ResultSet rs = null;
    PreparedStatement stmt = null;
    Connection cn = null;
    
    public SQLCompra(Connection cn){
        this.cn = cn;
    }
    

    @Override
    public int insertar(Compra a) {
        
        try{
            stmt = cn.prepareStatement(insertar);
            stmt.setInt(1,a.getIdCompra());
            stmt.setLong(2,a.getIdProveedor());
            stmt.setInt(3, a.getIdEmpleado());
            stmt.setString(4,fechaString(a.getFechaCompra()));
            return stmt.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("error al insertar encabezado compra "+ex);
            return 0;
        }
    }

    @Override
    public int getIdCompra() {
        
        int id =0;
        try{
            stmt = cn.prepareStatement(getIdCompra);
            rs = stmt.executeQuery();
            if(rs.next()){
                id= rs.getInt(1);
                return id;
            }
            
        }catch(SQLException ex){
            System.out.println("error al obtener idCompra "+ ex);
            
        }
        return id;
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
    public List<Compra> listaCompras(String fecha1,String fecha2) {
        List<Compra>lista = new ArrayList<>();
        
        try{
            stmt = cn.prepareStatement(rangoFechas);
            stmt.setString(1, fecha1);
            stmt.setString(2, fecha2);
            rs = stmt.executeQuery();
            while(rs.next()){
                lista.add(convertir(rs));
            }
            
        }catch(SQLException ex){
            
            System.out.println("error al listar compras "+ex);
        }
            
        
        return lista;
        
    }
    
    
    private Compra convertir (ResultSet rs) throws SQLException{
        
        int idProv = rs.getInt(2);
        int idEmple = rs.getInt(3);
        Date fecha = rs.getDate(4);
        Compra  compra = new Compra(idEmple, idProv, idEmple, fecha);
        compra.setIdCompra(rs.getInt(1));
        return compra;
        
        
    }
    
    
  
}
