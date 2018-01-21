
package SentenciasSQL;

import Conexion.ClsConexion;
import DAOs.DAOCliente;
import Modelos.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;


public class SQLCliente implements DAOs.DAOCliente{
    //variables sentencias
    final String insertar="BEGIN PA_INGRESAR_CLIENTE(?,?,?,?,?,?,?,?,?,?,?);END;";
    final String actualizar="BEGIN PA_ACTUALIZAR_CLIENTE(?,?,?,?,?,?,?,?,?,?,?,?);END;";
    final String obtenerTodo="SELECT*FROM CLIENTE ORDER BY ID_CLIENTE ASC";
    final String obtenerUno="SELECT*FROM CLIENTE WHERE ID_CLIENTE=?";
    final String obtenerPorNit="SELECT*FROM CLIENTE WHERE NIT=?";
    final String filtrarPorNit="SELECT*FROM CLIENTE WHERE NIT LIKE ? ORDER BY ID_CLIENTE ASC";
    final String filtrarPorApellido="SELECT*FROM CLIENTE WHERE APELLIDO_PRI LIKE ? ORDER BY ID_CLIENTE ASC";
    final String filtrarPorId="SELECT*FROM CLIENTE WHERE ID_CLIENTE LIKE ? ORDER BY ID_CLIENTE ASC";
    final String nombreCliente="SELECT ID_CLIENTE,NOMBRE_PRI,APELLIDO_PRI FROM CLIENTE";
    //variables tipo sql
    private Connection cn =null;
    private ResultSet rs=null;
    private PreparedStatement stmt = null;
    
    
    
    public SQLCliente(Connection cn){
        this.cn=cn;
        
    }
    void cerrarStmt(){
        try{
            stmt.close();
        }catch(SQLException ex){
            System.out.println("error al cerrar stmt cliente"+ ex);
        }
        
    }
    void cerraRs(){
        
        try{
            rs.close();
            
        }catch(SQLException ex){
            System.out.println("error al cerrar rs cliente"+ ex);
        }
        
    }
    
    @Override
    public void insertar(Cliente a) {
        try {
            stmt = cn.prepareStatement(insertar);
            stmt.setString(1, a.getNombre1());
            stmt.setString(2, a.getNombre2());
            stmt.setString(3, a.getApellido1());
            stmt.setString(4, a.getApellido2());
            stmt.setString(5, a.getRazon());
            stmt.setString(6, a.getNit());
            stmt.setString(7, a.getDireccion());
            stmt.setString(8, a.getEmail());
            stmt.setString(9, a.getTelefono());
            stmt.setString(10, fechaString(a.getFechaIng()));
            stmt.setInt(11, a.getEstado());
            if (stmt.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Datos no ingresados", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            System.out.println("" + ex);

        } finally {
            cerrarStmt();
        }

        
        
    }

    @Override
    public void actualizar(Cliente a) {
        try{
            stmt=cn.prepareStatement(actualizar);
            stmt.setString(1, a.getNombre1());
            stmt.setString(2, a.getNombre2());
            stmt.setString(3,a.getApellido1());
            stmt.setString(4,a.getApellido2());
            stmt.setString(5, a.getRazon());
            stmt.setString(6,a.getNit());
            stmt.setString(7, a.getDireccion());
            stmt.setString(8,a.getEmail());
            stmt.setString(9,a.getTelefono());
            stmt.setString(10,fechaString( a.getFechaIng()));
            stmt.setInt(11, a.getEstado());
            stmt.setLong(12, a.getIdCliente());
            if(stmt.executeUpdate()==1){
                JOptionPane.showMessageDialog(null,"Datos modificados correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Los datos no se modificaron","Error",JOptionPane.ERROR_MESSAGE);
            }
            
        }catch(SQLException ex){
            System.out.println("error al modificar cliente"+ ex);
        }finally{
            cerrarStmt();
        }
        
    }

    @Override
    public List<Cliente> obtenerTodo() {
        
        List<Cliente>listaCliente=new ArrayList<>();
        try{
            stmt=cn.prepareStatement(obtenerTodo);
            rs=stmt.executeQuery();
            while(rs.next()){
                listaCliente.add(convertir(rs));
            }
        }catch(SQLException ex){
                System.out.println("error al obtener todo cliente"+ ex);
        }finally{
            cerraRs();
            cerrarStmt();
        }
        return listaCliente;
        
        
        
    }

    @Override
    public Cliente obtenerUno(Long id){
     
        Cliente cliente = null;
        try{
            stmt=cn.prepareStatement(obtenerUno);
            stmt.setLong(1,id );
            rs=stmt.executeQuery();
            if(rs.next()){
                
                cliente=convertir(rs);
            }else{
                JOptionPane.showMessageDialog(null,"No se encontraron resultados","Alerta", JOptionPane.WARNING_MESSAGE);
            }
         
            
            
        }catch(SQLException ex){
            System.out.println("error al obtener uno"+ ex);
        }finally{
            cerraRs();
            cerrarStmt();
        }
        
        return cliente;
        
    }
    
    public String fechaString(Date fecha){
        String retorno=null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if(fecha!=null){
            retorno=sdf.format(fecha);
        }
        return retorno;
    }
 
    public Cliente convertir(ResultSet rs) throws SQLException{
        String nombre1 = rs.getString(2);
        String nombre2= rs.getString(3);
        String apellido1=rs.getString(4);
        String apellido2=rs.getString(5);
        String razon = rs.getString(6);
        String nit =rs.getString(7);
        String direccion = rs.getString(8);
        String email = rs.getString(9);
        String telefono = rs.getString(10);
        Date fechaIng=rs.getDate(11);
        int estado = rs.getInt(12);
        Cliente cliente = new Cliente(nombre1, nombre2, apellido1, apellido2, razon, nit, direccion, email, telefono, fechaIng, estado);
        cliente.setIdCliente(rs.getLong(1));
        return cliente;
    }
    
    @Override
    public Cliente obtenerPorNit(String nit){
        Cliente cli=null;
        try{
            stmt=cn.prepareStatement(obtenerPorNit);
            stmt.setString(1, nit);
            rs=stmt.executeQuery();
            if(rs.next()){
               cli=convertir(rs);
            }else{
                cli=new Cliente("", "", "", "", "","", "", "", "", new Date(), 0);
            }
            
        }catch(SQLException ex){
            System.out.println("error al obtener por nit"+ ex);
        }finally{
            cerraRs();
            cerrarStmt();
        }
        
        
        return cli;
        
    }

    @Override
    public List<Cliente> filtrarPorId(String id) {
        List<Cliente>listaClientes = new ArrayList<>();
        String filtro = id+"%";
        try{
            stmt = cn.prepareStatement(filtrarPorId);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaClientes.add(convertir(rs));
            }
            
        }catch(SQLException ex){
            
        }finally{
            cerraRs();
            cerrarStmt();
        }
        
        return listaClientes;
        
    }

    @Override
    public List<Cliente> filtrarPorApellido(String apellido){
        List<Cliente>listaClientes = new ArrayList<>();
        String filtro = apellido+"%";
        try{
            stmt = cn.prepareStatement(filtrarPorApellido);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaClientes.add(convertir(rs));
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            cerraRs();
            cerrarStmt();
        }
        return listaClientes;
        
    }

    @Override
    public List<Cliente> filtrarPorNit(String nit) {
        
        List<Cliente>listaClientes = new ArrayList<>();
        String filtro = nit+"%";
        
        try{
            stmt = cn.prepareStatement(filtrarPorNit);
            stmt.setString(1, filtro);
            rs= stmt.executeQuery();
            while(rs.next()){
                listaClientes.add(convertir(rs));
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            cerraRs();
            cerrarStmt();
        }
        return listaClientes;
    }

    @Override
    public List<HashMap<String, Object>> obtenerClienteNombre() {
        List<HashMap<String,Object>>listaClientes = new ArrayList<>();
        try{
            stmt = cn.prepareStatement(nombreCliente);
            rs = stmt.executeQuery();
            while(rs.next()){
                HashMap<String,Object>o = new HashMap<>();
                o.put("ID", rs.getLong(1));
                o.put("NOMBRE", rs.getString(2)+" "+rs.getString(3));
                listaClientes.add(o);
            }
            
            
        }catch(SQLException ex){
            System.out.println("error al obtener nombre" + ex);
        }finally{
            cerraRs();
            cerrarStmt();
        }
        
        return listaClientes;
        
        
    }
}


