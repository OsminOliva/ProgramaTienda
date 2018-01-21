
package Modelos;

import java.util.Date;


public class Compra {
    
    
    private int idCompra;
    private long idProveedor;
    private int idEmpleado;
    private Date fechaCompra;

    public Compra(int idCompra, long idProveedor, int idEmpleado, Date fechaCompra) {
        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.idEmpleado = idEmpleado;
        this.fechaCompra = fechaCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Compra{" + "idCompra=" + idCompra + ", idProveedor=" + idProveedor + ", idEmpleado=" + idEmpleado + ", fechaCompra=" + fechaCompra + '}';
    }
    
    
    
}
