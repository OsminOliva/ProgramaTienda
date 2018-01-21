
package SentenciasSQL;

import Conexion.ClsConexion;
import DAOs.DAOEmpleado;
import Modelos.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;


public class SQLEmpleado implements DAOEmpleado{
    //variables sentencias
    final String insertar="BEGIN PA_INSERTAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);END;";
    final String actualizar="BEGIN PA_ACTUALIZAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);END;";
    final String obtenerTodo="SELECT*FROM EMPLEADO ORDER BY ID_EMPLEADO ASC";
    final String obtenerUno="SELECT*FROM EMPLEADO WHERE ID_EMPLEADO=?";
    final String getUser="SELECT USUARIO FROM EMPLEADO WHERE USUARIO =?";
    final String getName="SELECT * FROM EMPLEADO WHERE USUARIO=?";
    final String filtrarPorId="SELECT * FROM EMPLEADO WHERE ID_EMPLEADO LIKE ? ORDER BY ID_EMPLEADO ASC";
    final String filtrarPorApellido="SELECT * FROM EMPLEADO WHERE APELLIDO_PRI LIKE ? ORDER BY ID_EMPLEADO ASC";
    final String filtrarPorDpi="SELECT * FROM EMPLEADO WHERE DPI LIKE ? ORDER BY ID_EMPLEADO ASC";
    final String validarInicio="SELECT*FROM EMPLEADO E, TIPO_USUARIO T WHERE"
            + " E.ID_TIPO_USUARIO=T.ID_TIPO_USUARIO AND USUARIO=? AND CONTRASENA=? AND DESCRIPCION=?";
    final String obtenerEmpleados="SELECT ID_EMPLEADO,NOMBRE_PRI,APELLIDO_PRI FROM EMPLEADO ORDER BY ID_EMPLEADO ASC";
    
    
            
    //variables de tipo SQL
    private ResultSet rs= null;
    private PreparedStatement stmt=null;
    private Connection cn=null;
    
    public SQLEmpleado(Connection cn){
        this.cn=cn;
    }
    
    public void cerrarStmt(){
        try{
            stmt.close();
            
        }catch(SQLException ex){
            System.out.println("error al cerrar stmt"+ ex);
        }
            
    }
    
    public void cerrarRs(){
        try{
            rs.close();
            
        }catch(SQLException ex){
            System.out.println("error al cerrar Rs"+ ex);
        }
    }

    @Override
    public void insertar(Empleado a){
        
        try{
            stmt=cn.prepareStatement(insertar);
            stmt.setString(1, fechaString(a.getFechaIng()));
            stmt.setString(2, a.getNombre1());
            stmt.setString(3, a.getNombre2());
            stmt.setString(4, a.getApellido1());
            stmt.setString(5, a.getApellido2());
            stmt.setString(6, a.getEmail());
            stmt.setFloat(7, a.getSueldo());
            stmt.setString(8,a.getSexo());
            stmt.setString(9,fechaString(a.getFechaNac()));
            stmt.setInt(10, a.getEstado());
            stmt.setString(11, a.getDireccion());
            stmt.setString(12, a.getTelefono());
            stmt.setString(13,a.getCelular());
            stmt.setString(14, a.getDpi());
            stmt.setString(15,a.getUsuario());
            stmt.setString(16,a.getContrasena());
            stmt.setInt(17,a.getIdTipo());
            if(stmt.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
            }else{
                JOptionPane.showMessageDialog(null,"No se pudo ingresar");
            }
            
        }catch(SQLException ex){
            System.out.println("error al insertar empleado"+ ex);
        }finally{
            cerrarStmt();
            
        }
        
        
    }

    @Override
    public void actualizar(Empleado a){
       try{
            stmt=cn.prepareStatement(actualizar);
            stmt.setString(1, fechaString(a.getFechaIng()));
            stmt.setString(2, a.getNombre1());
            stmt.setString(3, a.getNombre2());
            stmt.setString(4, a.getApellido1());
            stmt.setString(5, a.getApellido2());
            stmt.setString(6, a.getEmail());
            stmt.setFloat(7, a.getSueldo());
            stmt.setString(8,a.getSexo());
            stmt.setString(9, fechaString(a.getFechaNac()));
            stmt.setInt(10, a.getEstado());
            stmt.setString(11, a.getDireccion());
            stmt.setString(12, a.getTelefono());
            stmt.setString(13,a.getCelular());
            stmt.setString(14, a.getDpi());
            stmt.setString(15,a.getContrasena());
            stmt.setInt(16,a.getIdTipo());
            stmt.setLong(17, a.getId());
            if(stmt.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
            }else{
                JOptionPane.showMessageDialog(null,"No se pudo modificar");
            }
            
        }catch(SQLException ex){
            System.out.println("error al modificar empleado"+ ex);
        }finally{
            cerrarStmt();
            
        }
        
        
    }


    @Override
    public List<Empleado> obtenerTodo() {
        
        List<Empleado> listEmpl=new ArrayList<>();
        try{
            stmt=cn.prepareStatement(obtenerTodo);
            rs=stmt.executeQuery();
            while(rs.next()){
                listEmpl.add(convertir(rs));
            }
            
        }catch(SQLException ex){
            System.out.println("error al obtener todo empleado "+ ex);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }finally{
            cerrarRs();
            cerrarStmt();
        }
        
        return listEmpl;
    }

    @Override
    public Empleado obtenerUno(Long id){
       Empleado empl=null;
       try{
           stmt=cn.prepareStatement(obtenerUno);
           stmt.setLong(1,id);
           rs=stmt.executeQuery();
           if(rs.next()){
               empl=convertir(rs);
           }else{
               JOptionPane.showMessageDialog(null,"no se encontro resultados");
               
           }
           
       }catch(SQLException ex){
           System.out.println("error al obtener uno empleado"+ ex);
       } catch (ParseException ex) {
            System.out.println("error en parseo empleado"+ ex);
        }finally{
           cerrarRs();
           cerrarStmt();
       }
            
        
        
        return empl;
    }
    
    public Empleado convertir(ResultSet rs) throws SQLException, ParseException{
        
        Date fechaIn=(rs.getDate(2));
        String nombre1=rs.getString(3);
        String nombre2= rs.getString(4);
        String apellido1=rs.getString(5);
        String apellido2= rs.getString(6);
        String email=rs.getString(7);
        float sueldo=rs.getFloat(8);
        String sexo = rs.getString(9);
        Date fechaNac=rs.getDate(10);
        int estado=rs.getInt(11);
        String direccion=rs.getString(12);
        String telefono=rs.getString(13);
        String celular=rs.getString(14);
        String dpi=rs.getString(15);
        String usuario=rs.getString(16);
        String contrasena=rs.getString(17);
        int idTipo=rs.getInt(18);
        Empleado empl= new Empleado(fechaIn, nombre1, nombre2, apellido1, apellido2, email, sueldo, sexo, fechaNac, estado, direccion, telefono, celular, dpi, usuario, contrasena, idTipo);
        empl.setId(rs.getLong(1));
        return empl;
    }
    
    public String fechaString(Date fecha){
        
        String retorno=null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if(fecha!=null){
            retorno = sdf.format(fecha);
        }
        return retorno;
    }

    @Override
    public Empleado getName(String user) {
        Empleado empl = null;
        try{
            stmt=cn.prepareStatement(getName);
            stmt.setString(1, user);
            rs=stmt.executeQuery();
            if(rs.next()){
                empl=convertir(rs);
            }else{
                JOptionPane.showMessageDialog(null, "no se encontro");
                empl=new Empleado(null, null);
            }
            
        }catch(SQLException ex){
            System.out.println("error al obtener Name"+ ex);
        } catch (ParseException ex) {
            System.out.println("error al parseo"+ ex);
            
        }finally{
           cerrarRs();
           cerrarStmt();
        }
        return empl;
        
        
        
        
    }

    @Override
    public Boolean inicioSesion(String user, String contra, String tipoUsuario)  {
        
        try{
           
            stmt=cn.prepareStatement(validarInicio);
            stmt.setString(1, user);
            stmt.setString(2, contra);
            stmt.setString(3, tipoUsuario);
            rs=stmt.executeQuery();
            if(rs.next()){
               if(rs.getInt("ESTADO")==1){
                   return true;
               }
            }
            
        }catch(SQLException ex){
            System.out.println("error al iniciar sesion"+ ex);
        }finally{
            cerrarRs();
            cerrarStmt();
        }
        return false;
    }
    
    @Override
    public List<Empleado> filtrarPorId(String id){
        List<Empleado>listaEmpleados= new ArrayList<>();
        String filtro = id+"%";
        try{
            stmt = cn.prepareStatement(filtrarPorId);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaEmpleados.add(convertir(rs));
            }
            
            
        }catch(SQLException ex){
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }finally{
            cerrarRs();
            cerrarStmt();
        }
        
        
        
        return listaEmpleados;
        
    }

    @Override
    public List<Empleado> filtrarPorApellido(String apellido){
        List<Empleado> listaEmpleados = new ArrayList<>();
        String filtro = apellido + "%";
        try {
            stmt = cn.prepareStatement(filtrarPorApellido);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaEmpleados.add(convertir(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            cerrarRs();
            cerrarStmt();
        }

        return listaEmpleados;
        
    }

    @Override
    public List<Empleado> filtrarPorDpi(String dpi){

        List<Empleado> listaEmpleados = new ArrayList<>();
        String filtro = dpi + "%";
        try {
            stmt = cn.prepareStatement(filtrarPorDpi);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaEmpleados.add(convertir(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            cerrarRs();
            cerrarStmt();
        }
        return listaEmpleados;
    }

    @Override
    public List<HashMap<String, Object>> listaEmpleado() {
       List<HashMap<String,Object>>listaEmpleado = new ArrayList<>();
       
       try{
           stmt = cn.prepareStatement(obtenerEmpleados);
           rs = stmt.executeQuery();
           while(rs.next()){
               HashMap<String,Object>o = new HashMap<>();
               o.put("ID",rs.getInt(1));
               o.put("NOMBRE",rs.getString(2)+" "+rs.getString(3));
               listaEmpleado.add(o);
           }
           
       }catch(SQLException ex){
           System.out.println("error al obtener id y nombre "+ex);
       }
        
        return listaEmpleado;
    }

    
    
}
