
package DAOs;


public interface DAOReportes {
    
    void verReporteCliente();
    void verReporteEmpleado();
    void verReporteFactura(int id,String total);
    void verReporteProducto();
    void verReporteProveedor();
    void verVendido();
    void verComprado();
    
}
