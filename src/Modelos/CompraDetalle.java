
package Modelos;


public class CompraDetalle {
    
    private int idCompraDetalle;
    private int idCompra;
    private int idProducto;
    private float precio;
    private int cantidad;

    public CompraDetalle(int idCompra, int idProducto, float precio, int cantidad) {
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getIdCompraDetalle() {
        return idCompraDetalle;
    }

    public void setIdCompraDetalle(int idCompraDetalle) {
        this.idCompraDetalle = idCompraDetalle;
    }
    
    
    
    

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
   
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CompraDetalle{" + "idCompra=" + idCompra + ", idProducto=" + idProducto + ", cantidad=" + cantidad + '}';
    }

   
    
    
    
    
}
