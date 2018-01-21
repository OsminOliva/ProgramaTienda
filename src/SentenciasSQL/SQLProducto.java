
package SentenciasSQL;

import DAOs.DAOProducto;
import Modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class SQLProducto implements DAOProducto{
    //variables consultas
    final String insertar="BEGIN PA_INGRESAR_PRODUCTO(?,?,?,?,?,?,?,?,?,?,?);END;";
    final String actualizar="BEGIN PA_ACTUALIZAR_PRODUCTO(?,?,?,?,?,?,?,?,?,?,?,?);END;";
    final String obtenerTodo="SELECT*FROM PRODUCTO ORDER BY ID_PRODUCTO ASC";
    final String obtenerUno="SELECT*FROM PRODUCTO WHERE ID_PRODUCTO=?";
    final String obtenerPorCod="SELECT*FROM PRODUCTO WHERE CODIGO_BARRAS=? ";
    final String obtenerCodProv="SELECT*FROM PRODUCTO WHERE CODIGO_BARRAS=? AND ID_PROVEEDOR=?";
    final String filtrarPorCod="SELECT * FROM PRODUCTO WHERE CODIGO_BARRAS LIKE ? ORDER BY ID_PRODUCTO ASC";
    final String filtrarPorNombre="SELECT * FROM PRODUCTO WHERE NOMBRE LIKE ? ORDER BY ID_PRODUCTO ASC";
    //variables sql
    private Connection cn=null;
    private PreparedStatement stmt=null;
    private ResultSet rs=null;
    
    public SQLProducto(Connection cn){
        this.cn=cn;
    }
    
    
    public void cerrarStmt() {
        try {
            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            System.out.println("error al cerrarStmt " + ex);
        }

    }
    
    public void cerrarRs() {
        try{
            if(rs!=null){
                rs.close();
            }
            
        }catch(SQLException ex){
            System.out.println("error al cerrarStmt "+ ex);
        }
    }

    @Override
    public void insertar(Producto a) {
        
      try{
          
          stmt=cn.prepareStatement(insertar);
          stmt.setString(1,a.getCod_barras());
          stmt.setString(2,a.getNombre());
          stmt.setString(3,a.getDescripcion());
          stmt.setInt(4, a.getId_categoria());
          stmt.setInt(5,a.getStock());
          stmt.setInt(6, a.getStock_min());
          stmt.setFloat(7, a.getPrecio_uni());
          stmt.setFloat(8, a.getPrecio_costo());
          stmt.setInt(9,a.getEstado());
          stmt.setFloat(10, a.getUtilidad());
          stmt.setInt(11,a.getId_proveedor());
          
          if(stmt.executeUpdate()==1){
              JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
          }else{
              JOptionPane.showMessageDialog(null,"¡Lo siento!, los datos no han sido ingresados");
          }
          
      }catch(SQLException ex){
          System.out.println("Error al insertar producto "+ex);
      }finally{
          cerrarStmt();
      }
        
        
    }

    @Override
    public void actualizar(Producto a){
      try{
          stmt=cn.prepareStatement(actualizar);
          stmt.setString(1,a.getCod_barras());
          stmt.setString(2,a.getNombre());
          stmt.setString(3,a.getDescripcion());
          stmt.setInt(4, a.getId_categoria());
          stmt.setInt(5,a.getStock());
          stmt.setInt(6, a.getStock_min());
          stmt.setFloat(7, a.getPrecio_uni());
          stmt.setFloat(8, a.getPrecio_costo());
          stmt.setInt(9,a.getEstado());
          stmt.setFloat(10, a.getUtilidad());
          stmt.setInt(11,a.getId_proveedor());
          stmt.setLong(12,a.getId());
          
          if(stmt.executeUpdate()==1){
              JOptionPane.showMessageDialog(null, "Datos modificados correctamente");
          }else{
              JOptionPane.showMessageDialog(null,"¡Lo siento!, los datos no han sido modificados");
          }
          
      }catch(SQLException ex){
          System.out.println("Error al modidicar actualizar "+ ex);
      }finally{
          cerrarStmt();
      }
        
        
    }

    @Override
    public List<Producto> obtenerTodo() {
        
       List<Producto> listProd =new ArrayList<>();
       try{
           stmt=cn.prepareStatement(obtenerTodo);
           rs=stmt.executeQuery();
           
           while(rs.next()){
               listProd.add(convertir(rs));
           }
           
       }catch(SQLException ex){
           System.out.println("Error al obtenerTodo  "+ ex);
           
           
       }finally{
           cerrarRs();
           cerrarStmt();
       }
       return listProd;
        
        
    }

    @Override
    public Producto obtenerUno(Long id) {
        Producto prod =null;
        try{
            
           stmt=cn.prepareStatement(obtenerUno);
           stmt.setLong(1,id);
           rs=stmt.executeQuery();
           if(rs.next()){
               prod=convertir(rs);
           }else{
               JOptionPane.showMessageDialog(null,"No se encontro resultados");
           }
            
        }catch(SQLException ex){
            System.out.println("Error al obtener 1 "+ ex);
        }finally{
            cerrarRs();
            cerrarStmt();
        }
        return prod;
        
        
        
        
    }
    
    private Producto convertir(ResultSet rs) throws SQLException{
        
        String codbarras= rs.getString("CODIGO_BARRAS");
        int idprov=rs.getInt("ID_PROVEEDOR");
        String nombre =rs.getString("NOMBRE");
        String descripcion=rs.getString("DESCRIPCION");
        int idcat=rs.getInt("ID_CATEGORIA");
        int stock=rs.getInt("STOCK");
        int stockmin=rs.getInt("STOCK_MINIMO");
        float precioventa=rs.getFloat("PRECIO_UNITARIO");
        float preciocosto=rs.getFloat("PRECIO_COSTO");
        int estado =rs.getInt("ESTADO");
        float utilidad =rs.getFloat("UTILIDAD");
        Producto prod = new Producto(codbarras, idprov, nombre, descripcion, idcat, stock, stockmin, precioventa, preciocosto, estado, utilidad);
        prod.setId(rs.getLong("ID_PRODUCTO"));
        return prod;
        
    } 

    @Override
    public Producto getCodBarra(String cod) {
       Producto prod = null;
       try{
           stmt=cn.prepareStatement(obtenerPorCod);
           stmt.setString(1, cod);
           rs=stmt.executeQuery();
           if(rs.next()){
               
               if(rs.getInt("ESTADO")==1){
                    prod=convertir(rs);
               }else{
                   JOptionPane.showMessageDialog(null, "Este producto actualmente no esta activo, \n"
                            + "por favor comuniquese con su gerente","Alerta",JOptionPane.WARNING_MESSAGE);
                   prod=new Producto("", 0, "", "", 0, 0, 0, 0, 0, 0, 0);
               }
              
           }else{
               prod=new Producto("", 0, "", "", 0, 0, 0, 0, 0, 0, 0);
           }
           
           
       }catch(SQLException ex){
           System.out.println("Error getCode "+ ex);
       }finally{
           cerrarRs();
           cerrarStmt();
       }
        
        
        return prod;
    }

    @Override
    public List<Producto> filtrarPorCod(String cod) {
        List<Producto>listaProd = new ArrayList<>();
        String filtro = cod+"%";
        try{
            stmt = cn.prepareStatement(filtrarPorCod);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaProd.add(convertir(rs));
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            cerrarRs();
            cerrarStmt();
        }
        return listaProd;
        
        
    }

    @Override
    public List<Producto> filtrarPorNombre(String nombre) {
        List<Producto>listaProd= new ArrayList<>();
        String filtro =nombre+"%";
        try{
            stmt = cn.prepareStatement(filtrarPorNombre);
            stmt.setString(1, filtro);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaProd.add(convertir(rs));
            }
            
        }catch(SQLException ex){
            System.out.println("Error al filtrarNombre"+ ex);
        }finally{
            cerrarRs();
            cerrarStmt();
        }
        return listaProd;
        
        
    }

    @Override
    public Producto getCodBarraP(String cod, long idProv) {
        
        Producto p = new Producto();
        try{
            stmt = cn.prepareStatement(obtenerCodProv);
            stmt.setString(1, cod);
            stmt.setLong(2, idProv);
            rs = stmt.executeQuery();
            if(rs.next()){
                p = convertir(rs);
            }else{
                p = new Producto("", 0, "", "", 0, 0, 0, 0, 0, 0,0);
            }
            
        }catch(SQLException ex){
            
            System.out.println("error en obtener cod y prov "+ex);
        }
        
        return p;
        
    }

   

   
}
