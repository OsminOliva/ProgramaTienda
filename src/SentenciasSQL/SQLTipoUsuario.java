
package SentenciasSQL;

import Modelos.TipoUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;


public class SQLTipoUsuario implements DAOs.DAOTipoUsuario{
    
    //variables sentencias

    final String insertar="BEGIN PA_INGRESAR_TIPO_USUARIO(?,?,?,?,?,?,?,?,?,?,?,?);END;";
    final String obtenerUno="SELECT*FROM TIPO_USUARIO WHERE ID_TIPO_USUARIO=?";
    final String actualizar="BEGIN PA_ACTUALIZAR_TIPO_USUARIO(?,?,?,?,?,?,?,?,?,?,?,?,?);END;";
    final String obtenerTodo="SELECT*FROM TIPO_USUARIO ORDER BY ID_TIPO_USUARIO ASC";
    final String obtenerPorUsuario="SELECT*FROM TIPO_USUARIO WHERE DESCRIPCION=?";
    final String obtenerDescripcion="SELECT DESCRIPCION FROM TIPO_USUARIO ORDER BY ID_TIPO_USUARIO ASC";
    final String obtenerDescripciones="SELECT ID_TIPO_USUARIO,DESCRIPCION FROM TIPO_USUARIO ORDER BY ID_TIPO_USUARIO ASC";
    final String filtrarPorId="SELECT * FROM TIPO_USUARIO WHERE ID_TIPO_USUARIO LIKE ? ORDER BY ID_TIPO_USUARIO ASC";
    final String filtrarPorDes="SELECT * FROM TIPO_USUARIO WHERE DESCRIPCION LIKE ? ORDER BY ID_TIPO_USUARIO ASC";
    //variables tipo sql
    private Connection cn=null;
    private PreparedStatement stmt=null;
    private ResultSet rs= null;
    
    
    public SQLTipoUsuario(Connection cn) {
        this.cn = cn;
    }

    public void cerrarStmt(){
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar stmt "+ ex);
            }
        }

    }

    public void cerrarRs() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrarRs "+ ex);
            }
        }

    }

    @Override
    public void insertar(TipoUsuario a) {
        try {
            stmt = cn.prepareStatement(insertar);
            stmt.setString(1, a.getDescripcion());
            stmt.setInt(2, a.getRegVentas());
            stmt.setInt(3, a.getRegCompras());
            stmt.setInt(4, a.getRegProducto());
            stmt.setInt(5, a.getRegProveedor());
            stmt.setInt(6, a.getRegEmpleado());
            stmt.setInt(7, a.getRegCliente());
            stmt.setInt(8, a.getRegCategoria());
            stmt.setInt(9, a.getRegTipoU());
            stmt.setInt(10, a.getInfoVentaR());
            stmt.setInt(11, a.getInfoEstadistica());
            stmt.setInt(12, a.getInfoCompraR());
            if (stmt.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "¡Datos ingresados correctamente!");
            } else {
                JOptionPane.showMessageDialog(null, "¡Lo siento¡, los datos no han sido guardados");
            }

        } catch (SQLException ex) {
            System.out.println("Error al ingresar tipoUsuario"+ ex);
            
        } finally {
            cerrarStmt();

        }

    }

    @Override
    public void actualizar(TipoUsuario a){

        try {
            stmt = cn.prepareStatement(actualizar);
            stmt.setString(1, a.getDescripcion());
            stmt.setInt(2, a.getRegVentas());
            stmt.setInt(3, a.getRegCompras());
            stmt.setInt(4, a.getRegProducto());
            stmt.setInt(5, a.getRegProveedor());
            stmt.setInt(6, a.getRegEmpleado());
            stmt.setInt(7, a.getRegCliente());
            stmt.setInt(8, a.getRegCategoria());
            stmt.setInt(9, a.getRegTipoU());
            stmt.setInt(10, a.getInfoVentaR());
            stmt.setInt(11, a.getInfoEstadistica());
            stmt.setInt(12, a.getInfoCompraR());
            stmt.setLong(13, a.getId());
            if (stmt.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "¡Datos modficados correctamente!");
            } else {
                JOptionPane.showMessageDialog(null, "¡Lo siento¡, los datos no se han podido ser modificados");
            }

        } catch (SQLException ex) {
            System.out.println("Error al actualizar "+ ex);
        } finally {
            cerrarStmt();
        }

    }

    @Override
    public List<TipoUsuario> obtenerTodo() {

        List<TipoUsuario> listaTipo = new ArrayList<>();
        try {
            stmt = cn.prepareStatement(obtenerTodo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaTipo.add(convertir(rs));
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtenerTodo tipoU"+ ex);
        } finally {
            cerrarRs();
            cerrarStmt();
        }
        return listaTipo;
    }

    @Override
    public TipoUsuario obtenerUno(Long id) {
        TipoUsuario tipoUsuario = null;
        try {
            stmt = cn.prepareStatement(obtenerUno);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                tipoUsuario = convertir(rs);
            } else {
                JOptionPane.showMessageDialog(null, "!Lo siento!, Datos no encontrados");
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtenerUno tipo "+ ex);
        } finally {
            cerrarRs();
            cerrarStmt();
        }
        return tipoUsuario;

    }

    @Override
    public TipoUsuario getUser(String descripcion) {
        TipoUsuario usuario = null;
        try {

            stmt = cn.prepareStatement(obtenerPorUsuario);
            stmt.setString(1, descripcion);
            rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = convertir(rs);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro");
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtenerUsuario"+ ex);
        }
        return usuario;
    }

    @Override
    public List<TipoUsuario> getDescripcion(){
        List<TipoUsuario> listaDescripcion = new ArrayList<>();
        try {
            stmt = cn.prepareStatement(obtenerDescripcion);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setDescripcion(rs.getString(1));
                listaDescripcion.add(tipoUsuario);
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtenerDEscripcion "+ ex);

        } finally {
            cerrarRs();
            cerrarStmt();
        }
        return listaDescripcion;
    }

    public TipoUsuario convertir(ResultSet rs) throws SQLException {

        String descripcion = rs.getString(2);
        int rgventas = rs.getInt(3);
        int rgcompras = rs.getInt(4);
        int rgprod = rs.getInt(5);
        int rgprove = rs.getInt(6);
        int rgemple = rs.getInt(7);
        int rgcliente = rs.getInt(8);
        int rgcategoria = rs.getInt(9);
        int rgtipou = rs.getInt(10);
        int infovr = rs.getInt(11);
        int infoes = rs.getInt(12);
        int infocr = rs.getInt(13);
        TipoUsuario tipousuario = new TipoUsuario(descripcion, rgventas, rgcompras, rgprod, rgprove, rgemple, rgcliente, rgcategoria, rgtipou, infovr, infocr, infoes);
        tipousuario.setId(rs.getLong(1));
        return tipousuario;
    }

    @Override
    public List<HashMap<String, Object>> obtenerDescripciones() {
        List<HashMap<String,Object>>descripciones = new ArrayList<>();
        try{
            stmt=cn.prepareStatement(obtenerDescripciones);
            rs= stmt.executeQuery();
            while(rs.next()){
                HashMap<String,Object>o= new HashMap<>();
                o.put("ID", rs.getInt(1));
                o.put("DESCRIPCION", rs.getString(2));
                descripciones.add(o);
                
            }
            
            
        }catch(SQLException ex){
            System.out.println("Error al obtenerDescrpciones hash "+ ex);
            
        }finally{
            cerrarRs();
            cerrarStmt();
        }
        
        return descripciones;
    }

    @Override
    public List<TipoUsuario> filtrarPorId(String id) {
         List<TipoUsuario> lista = new ArrayList<>();
         String filtro = id+"%";
         try{
             stmt = cn.prepareStatement(filtrarPorId);
             stmt.setString(1, filtro);
             rs = stmt.executeQuery();
             while(rs.next()){
                 lista.add(convertir(rs));
             }
             
         }catch(SQLException ex){
             ex.printStackTrace();
         }finally{
             cerrarRs();
             cerrarStmt();
         }
        
        return lista;
        
    }

    @Override
    public List<TipoUsuario> filtrarPorDescripcion(String des) {
        List<TipoUsuario>lista = new ArrayList<>();
        String filtro = des+"%";
        try{
            stmt= cn.prepareStatement(filtrarPorDes);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while(rs.next()){
                lista.add(convertir(rs));
                
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
            
        }finally{
            cerrarRs();
            cerrarStmt();
        }
        
        return lista;
        
    }
    

   
}
