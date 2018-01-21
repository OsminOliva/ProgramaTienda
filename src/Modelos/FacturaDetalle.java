
package Modelos;


public class FacturaDetalle {
    
    
    private int idFacturaDetalle;
    private int idFactura;
    private int idProducto;
    private float precio;
    private int cantidad;

    
    public FacturaDetalle() {
        
        
    }

    public FacturaDetalle(int idFactura, int idProducto, float precio, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getIdFacturaDetalle() {
        return idFacturaDetalle;
    }

    public void setIdFacturaDetalle(int idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
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
     public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" + "idFacturaDetalle=" + idFacturaDetalle + ", idFactura=" + idFactura + ", idProducto=" + idProducto + ", cantidad=" + cantidad + '}';
    }

    

    

   
    
    
    
    
}
