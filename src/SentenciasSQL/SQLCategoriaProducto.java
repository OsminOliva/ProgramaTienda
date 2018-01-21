
package SentenciasSQL;

import Conexion.ClsConexion;
import Modelos.CategoriaProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import DAOs.DAOCategoriaProducto;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.HashMap;


public class SQLCategoriaProducto implements DAOCategoriaProducto{//implementamos todos los metodos de plantilla especifa
    //Consultas finales
    final String insertar="BEGIN PA_INGRESAR_CATEGORIA(?);END;";
    final String actualizar="BEGIN PA_ACTUALIZAR_CATEGORIA(?,?);END;";
    final String obtenerTodos="SELECT*FROM CATEGORIA_PRODUCTO ORDER BY ID_CATEGORIA ASC";
    final String obtenerUno="SELECT*FROM CATEGORIA_PRODUCTO WHERE ID_CATEGORIA=?";
    final String filtrarCategoria="SELECT*FROM CATEGORIA_PRODUCTO WHERE DESCRIPCION LIKE ? ORDER BY ID_CATEGORIA ASC";
    final String filtrarPorId="SELECT*FROM CATEGORIA_PRODUCTO WHERE ID_CATEGORIA LIKE ? ORDER BY ID_CATEGORIA ASC";
    final String obtenerDescripciones="SELECT ID_CATEGORIA,DESCRIPCION FROM CATEGORIA_PRODUCTO ORDER BY ID_CATEGORIA ASC";
    
    //variables SQL
    private ResultSet rs=null;
    private Connection cn=null;
    private PreparedStatement stmt=null;
    
    
    
   

    public SQLCategoriaProducto(Connection cn) {
        this.cn=cn;
        
        
    }
    
    
    @Override
    public void insertar(CategoriaProducto a){
        try{
            stmt=cn.prepareStatement(insertar);//ejecutamos la inserccion
            stmt.setString(1, a.getDescripcion());//le seteamos la descripcion que esta en el modelo
            if(stmt.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "¡Upss!,datos no ingresados");
            }
           
        }catch(SQLException ex){
                 System.out.println("error al insertar categoria producto"+ ex);
        }finally{
            cerraStmt();
        }
    }

    @Override
    public void actualizar(CategoriaProducto a){
        
        try{
            stmt=cn.prepareStatement(actualizar);//ejecutamos la inserccion
            stmt.setString(1, a.getDescripcion());//le seteamos la descripcion
            stmt.setLong(2, a.getIdCategoria());
            if(stmt.executeUpdate()==1){
                JOptionPane.showMessageDialog(null, "Datos Modificados correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "¡Upss!,datos no modificados");
            }
        }catch(SQLException ex){
                 System.out.println("error al actualizar categoria"+ ex);
        }finally{
            cerraStmt();
            
        }
    }

    
    @Override
    public List<CategoriaProducto> obtenerTodo() {

        List<CategoriaProducto> cat = new ArrayList<>();//declaramos lista de tipo modelo
        try {
            stmt = cn.prepareStatement(obtenerTodos);//ejecutamos entorno
            rs = stmt.executeQuery();//obtenemos resultset
            while (rs.next()) {//lo recorremos puesto que se obtendran varios datos
                cat.add(convertir(rs));//los agregamos a la lista convirtiendo el resultset
            }
        } catch (SQLException ex) {
            System.out.println("error obtener todo categoria " + ex);

        } finally {
            cerraStmt();
            cerrarRs();
        }
        return cat;
    }

    @Override
    public CategoriaProducto obtenerUno(Long id) {//obetener dependiendo del ID
        CategoriaProducto a=null;//declaramos un objeto de nuestro modelo
        try{
            stmt=cn.prepareStatement(obtenerUno);//ejecutamos el entorno
            stmt.setLong(1,id);//a la posicion primera le seteamos el ID
            rs=stmt.executeQuery();//ejecutamos el resultset
            if(rs.next()){//si encuentra datos
                a=convertir(rs);//convertimos el resultset en objeto
            }else{
                JOptionPane.showMessageDialog(null, "dato no encontrado");
            }
        }catch(SQLException ex){
            System.out.println("error al obtener uno"+ ex);
            
        }finally{
            cerraStmt();//cerramos el stmt 
            cerrarRs();//cerramos el Resultset
        }
        return a;//retornamos el objeto
    }
    
    public void cerraStmt(){
        if(stmt!=null){
            try{
                 stmt.close();
            }catch(SQLException ex){
                System.out.println("error al cerrar stmt cat"+ ex);;
            }
        }
    }
    public void cerrarRs(){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException ex){
                System.out.println("error al cerrar Rs cat"+ ex);
            }
        }
    }
    
    private CategoriaProducto convertir(ResultSet rs)throws SQLException{
        
        String descripcion=rs.getString("DESCRIPCION");//metemos lo que tenga en al columna Descripcion dentro de un variable
        CategoriaProducto cat=new CategoriaProducto(descripcion);//creamos un objeto de tipo Modelo Categoria y le metemos la variable
        cat.setIdCategoria(rs.getLong("ID_CATEGORIA"));//al objeto le metemos el id obtiendolo del resultset
        return cat;//devolvemos el objeto
    }

    @Override
    public List<CategoriaProducto> filtrarCategoria(String categoria){
        List<CategoriaProducto>lista=new ArrayList<>();
        String filtro=categoria+"%";
        try{
            stmt=cn.prepareStatement( filtrarCategoria);
            stmt.setString(1, filtro);
            rs=stmt.executeQuery();
            while(rs.next()){
                lista.add(convertir(rs));
            }
            
            
        }catch(SQLException ex){
            System.out.println("error al filtrar categoria"+ ex);
        }finally{
            cerraStmt();
            cerrarRs();
        }
        
        return lista;
    }

    @Override
    public List<HashMap<String, Object>> objectList(){
        
        List<HashMap<String,Object>>lista=new ArrayList<>();
        
        try{
            stmt=cn.prepareStatement(obtenerDescripciones);
            rs=stmt.executeQuery();
            while(rs.next()){
                HashMap<String,Object>o=new HashMap<>();
                o.put("ID", rs.getInt(1));
                o.put("DESCRIPCION", rs.getString(2));
                lista.add(o);
            }
            
        }catch(SQLException ex){
            System.out.println("error obtener lista hashMap"+ ex);
        }finally{
            cerraStmt();
            cerrarRs();
        }
        
        return lista;
    }

    @Override
    public List<CategoriaProducto> filtrarPorID(String categoria) {
        List<CategoriaProducto> listaCat = new ArrayList<>();
        String filtro = categoria + "%";
        try {
            stmt = cn.prepareStatement(filtrarPorId);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaCat.add(convertir(rs));
            }

        } catch (SQLException ex) {
                System.out.println("error al filtrar por id"+ ex);
        } finally {
            cerraStmt();
            cerrarRs();
        }
        return listaCat;
    }

  
    
    
   
    
    
   
   

    
}
