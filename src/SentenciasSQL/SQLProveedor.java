
package SentenciasSQL;

import Conexion.ClsConexion;
import DAOs.DAOProveedor;
import Modelos.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;


public class SQLProveedor implements DAOProveedor{
    //variables  sentencias
    final String insertar="INSERT INTO PROVEEDOR(NOMBRE,DPI,NIT,DIRECCION,TELEFONO,CELULAR,EMAIL,ESTADO)VALUES(?,?,?,?,?,?,?,?)";
    final String actualizar="UPDATE PROVEEDOR SET NOMBRE=?,DPI=?,NIT=?,DIRECCION=?,TELEFONO=?,CELULAR=?,EMAIL=?,ESTADO=?"
            + "WHERE ID_PROVEEDOR=?";
    final String obtenerOne="SELECT*FROM PROVEEDOR WHERE ID_PROVEEDOR=?";
    final String obtenerTodo="SELECT*FROM PROVEEDOR ORDER BY ID_PROVEEDOR ASC";
    final String obtenerNombres="SELECT ID_PROVEEDOR FROM PROVEEDOR";
    final String obtenerPorNit="SELECT * FROM PROVEEDOR WHERE NIT =?";
    final String obtenerProveedores="SELECT ID_PROVEEDOR,NOMBRE FROM PROVEEDOR ORDER BY ID_PROVEEDOR ASC";
    final String filtrarPorId="SELECT*FROM PROVEEDOR WHERE ID_PROVEEDOR LIKE ? ORDER BY ID_PROVEEDOR ASC";
    final String filtrarPorNombre="SELECT * FROM PROVEEDOR WHERE NOMBRE LIKE ? ORDER BY ID_PROVEEDOR ASC";
    final String filtrarPorDpi="SELECT * FROM PROVEEDOR WHERE DPI LIKE ? ORDER BY ID_PROVEEDOR ASC";
    //vairbales de tipo sql
    private Connection cn;
    private ResultSet rs=null;
    private PreparedStatement stmt=null;
    private ClsConexion Conectar = new ClsConexion();
    
    
    public SQLProveedor(Connection cn){
        this.cn=cn;
    }
    
    void cerraStmt(){
        if(stmt!=null){
            try{
                 stmt.close();
            }catch(SQLException ex){
                System.out.println("Error al cerrarStmt "+ ex);
            }
        }
    }
    
    void cerrarRs(){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException ex){
                
                System.out.println("Error al modidicar actualizar "+ ex);
            }
        }
    }

    @Override
    public void insertar(Proveedor a) {
        try{
            stmt=cn.prepareStatement(insertar);
            stmt.setString(1,a.getNombre());
            stmt.setString(2, a.getDpi());
            stmt.setString(3, a.getNit());
            stmt.setString(4,a.getDireccion());
            stmt.setString(5, a.getTelefono());
            stmt.setString(6, a.getCelular());
            stmt.setString(7, a.getEmail());
            stmt.setInt(8, a.getEstado());
            if(stmt.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "¡Datos Guardados exitosamente!");
            }else{
                JOptionPane.showMessageDialog(null,"No se pudo guardar");
            }
            
        }catch(SQLException ex){
            System.out.println("Error al modidicar actualizar "+ ex);
            
        }finally{
            cerraStmt();
        }
        
    }

    @Override
    public void actualizar(Proveedor a) {
       
        try{
            stmt=cn.prepareStatement(actualizar);
            stmt.setString(1, a.getNombre());
            stmt.setString(2,a.getDpi());
            stmt.setString(3,a.getNit());
            stmt.setString(4,a.getDireccion());
            stmt.setString(5,a.getTelefono());
            stmt.setString(6,a.getCelular());
            stmt.setString(7,a.getEmail());
            stmt.setInt(8,a.getEstado());
            stmt.setLong(9, a.getId());
            
            if(stmt.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "¡Datos modificados correctamente!");
            }else{
                JOptionPane.showMessageDialog(null, "¡Upps!, los datos no se han podido modifcar");
            }
        }catch(SQLException ex){
             System.out.println("Error al modificar proveedor "+ ex);
        }finally{
            cerraStmt();
        }
        
    }

    @Override
    public List<Proveedor> obtenerTodo() {
        List<Proveedor> prov=new ArrayList<>();
        try{
            stmt=cn.prepareStatement(obtenerTodo);
            rs=stmt.executeQuery();
            while(rs.next()){
                prov.add(convertir(rs));
            }
            
        }catch(SQLException ex){
            System.out.println("Error al obtenerTodo "+ ex);
        }finally{
            cerraStmt();
            cerrarRs();
        }
        return prov;
        
        
        
        
    }

    @Override
    public Proveedor obtenerUno(Long id) {
        Proveedor a=null;
        try{
            
            stmt=cn.prepareStatement(obtenerOne);
            stmt.setLong(1,id);
            rs=stmt.executeQuery();
            if(rs.next()){
                a=convertir(rs);
            }else{
                a = new Proveedor("", "", "", "","", "", "", 0);
            }
        }catch(SQLException ex){
            System.out.println("Error al obtenerUno "+ ex);
        }finally{
            cerraStmt();
            cerrarRs();
        }
        return a;
    }
    
    
    private Proveedor convertir(ResultSet rs) throws SQLException{
        
        String nombre=rs.getString("NOMBRE");
        String dpi=rs.getString("DPI");
        String nit=rs.getString("NIT");
        String direccion =rs.getString("DIRECCION");
        String telefono=rs.getString("TELEFONO");
        String celular=rs.getString("CELULAR");
        String email=rs.getString("EMAIL");
        int estado=rs.getInt("ESTADO");
        Proveedor proveedor= new Proveedor(nombre,dpi,nit,direccion,telefono,celular,email,estado);
        proveedor.setId(rs.getLong("ID_PROVEEDOR"));
        return proveedor;
        
    }
    

   
   
    

    @Override
    public List<HashMap<String, Object>> listaProveedores() {
        List<HashMap<String, Object>> listaProveedores= new ArrayList<>();
        try{
            stmt=cn.prepareStatement(obtenerProveedores);
            rs=stmt.executeQuery();
            while(rs.next()){
                HashMap<String,Object>o = new HashMap<>();
                o.put("ID", rs.getInt(1));
                o.put("DESCRIPCION",rs.getString(2));
                listaProveedores.add(o);
                
            }
            
            
        }catch(SQLException ex){
            System.out.println("Error al obtener lista proveedores "+ ex);
        }
        
        
        
        return listaProveedores;
    }

   

    @Override
    public List<Proveedor> filtrarPorId(String id) {
        List<Proveedor>listaProveedor = new ArrayList<>();
        String filtro = id+"%";
        try{
            stmt = cn.prepareStatement(filtrarPorId);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaProveedor.add(convertir(rs));
            }
        }catch(SQLException ex){
            
        }finally{
            cerraStmt();
            cerrarRs();
            
        }
        return listaProveedor;
    }

    @Override
    public List<Proveedor> filtrarPorNombre(String nombre) {
        List<Proveedor> listaProv = new ArrayList<>();
        String filtro = nombre + "%";
        try {
            stmt = cn.prepareStatement(filtrarPorNombre);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaProv.add(convertir(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            cerraStmt();
            cerrarRs();
        }
        return listaProv;
    }

    @Override
    public List<Proveedor> filtrarPorDpi(String dpi) {
       List<Proveedor>listaProv = new ArrayList<>();
       String filtro = dpi+"%";
       try{
           stmt = cn.prepareStatement(filtrarPorDpi);
           stmt.setString(1, filtro);
           rs = stmt.executeQuery();
           while(rs.next()){
               listaProv.add(convertir(rs));
           }
           
       }catch(SQLException ex){
           ex.printStackTrace();
           
       }finally{
           cerraStmt();
           cerrarRs();
       }
        
        return listaProv;
    }

    @Override
    public Proveedor getPorNit(String nit) {
        
        Proveedor prov = new Proveedor();
        try{
            stmt = cn.prepareStatement(obtenerPorNit);
            stmt.setString(1, nit);
            rs = stmt.executeQuery();
            if(rs.next()){
                if(rs.getInt("ESTADO")==1){
                    prov = convertir(rs);
                }else{
                    JOptionPane.showMessageDialog(null, "Este proovedor actualmente no esta activo, \n"
                            + "por favor comuniquese con su gerente","Alerta",JOptionPane.WARNING_MESSAGE);
                }
                
            }else{
                prov = new Proveedor("", "", "", "", "","", "", 0);
            }
            
        }catch(SQLException ex){
            System.out.println("error al obtener proveedor por nit "+ ex);
            
        }finally{
            cerraStmt();
            cerrarRs();
        }
       return prov;
    }

   
    
}
