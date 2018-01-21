
package Modelos;


public class TipoUsuario {
    
    private Long id=null;
    private String descripcion;
    private int regVentas;
    private int regCompras;
    private int regProducto;
    private int regProveedor;
    private int regEmpleado;
    private int regCliente;
    private int regCategoria;
    private int regTipoU;
    private int infoVentaR;
    private int infoCompraR;
    private int infoEstadistica;
  
    
    
    public TipoUsuario(){
        
    }

    public TipoUsuario(String descripcion, int regVentas, int regCompras, int regProducto, int regProveedor, int regEmpleado, int regCliente, int regCategoria, int regTipoU, int infoVentaR, int infoCompraR, int infoEstadistica) {
        this.descripcion = descripcion;
        this.regVentas = regVentas;
        this.regCompras = regCompras;
        this.regProducto = regProducto;
        this.regProveedor = regProveedor;
        this.regEmpleado = regEmpleado;
        this.regCliente = regCliente;
        this.regCategoria = regCategoria;
        this.regTipoU = regTipoU;
        this.infoVentaR = infoVentaR;
        this.infoCompraR = infoCompraR;
        this.infoEstadistica = infoEstadistica;
    }
    
    

   
    

    

    
    //getters y setterrs
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRegVentas() {
        return regVentas;
    }

    public void setRegVentas(int regVentas) {
        this.regVentas = regVentas;
    }

    public int getRegCompras() {
        return regCompras;
    }

    public void setRegCompras(int regCompras) {
        this.regCompras = regCompras;
    }

    public int getRegProducto() {
        return regProducto;
    }

    public void setRegProducto(int regProducto) {
        this.regProducto = regProducto;
    }

    public int getRegProveedor() {
        return regProveedor;
    }

    public void setRegProveedor(int regProveedor) {
        this.regProveedor = regProveedor;
    }

    public int getRegEmpleado() {
        return regEmpleado;
    }

    public void setRegEmpleado(int regEmpleado) {
        this.regEmpleado = regEmpleado;
    }

    public int getRegCliente() {
        return regCliente;
    }

    public void setRegCliente(int regCliente) {
        this.regCliente = regCliente;
    }

    public int getRegCategoria() {
        return regCategoria;
    }

    public void setRegCategoria(int regCategoria) {
        this.regCategoria = regCategoria;
    }

   

    public int getRegTipoU() {
        return regTipoU;
    }

    public void setRegTipoU(int regTipoU) {
        this.regTipoU = regTipoU;
    }

  



    public int getInfoVentaR() {
        return infoVentaR;
    }

    public void setInfoVentaR(int infoVentaR) {
        this.infoVentaR = infoVentaR;
    }


    public int getInfoCompraR() {
        return infoCompraR;
    }

    public void setInfoCompraR(int infoCompraR) {
        this.infoCompraR = infoCompraR;
    }



    public int getInfoEstadistica() {
        return infoEstadistica;
    }

    public void setInfoEstadistica(int infoEstadistica) {
        this.infoEstadistica = infoEstadistica;
    }

    

   
    
    
    
    
    
    
    
}
