
package Modelos;

import java.util.Date;


public class Factura {
    
    private int idFactura;
    private String serie;
    private long idCliente;
    private int idEmpleado;
    private Date fechaFac;

    public Factura(){
        
    }

    public Factura(int idFactura,String serie, long idCliente, int idEmpleado, Date fechaFac) {
        this.idFactura=idFactura;
        this.serie = serie;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.fechaFac = fechaFac;
    }

    public Factura(String serie, long idCliente, int idEmpleado, Date fechaFac) {
        this.serie = serie;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.fechaFac = fechaFac;
    }
    

   
   

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

   

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaFac() {
        return fechaFac;
    }

    public void setFechaFac(Date fechaFac) {
        this.fechaFac = fechaFac;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Factura{" + "idFactura=" + idFactura + ", serie=" + serie + ", idCliente=" + idCliente + ", idEmpleado=" + idEmpleado + ", fechaFac=" + fechaFac + '}';
    }
    
    
    
    
    
    
    
    
    
}
