
package Modelos;


public class CategoriaProducto {
    //ATRIBUTOS
    private Long idCategoria=null;
    private String descripcion;
    
    
    public CategoriaProducto(){
        
        
    }
    public CategoriaProducto( String descripcion) {
        this.descripcion = descripcion;
    }
    
    //GETTERS Y SETTERS
    public Long getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "CategoriaProducto{" + "idCategoria=" + idCategoria + ", descripcion=" + descripcion + '}';
    }

    
    
    
    
    
}
