
package DAOs;


public interface DAOControlador {
    
    //El manager nos permite acceder a los distintos daos de nuestra aplicacion
    //metodos que obtendran los DAOs
    DAOCategoriaProducto getCategoriaProductoDAO();
    DAOProveedor getProveedorDAO();
    DAOTipoUsuario getTipousuarioDAO();
    DAOProducto getProductoDAO();
    DAOEmpleado getEmpleadoDAO();
    DAOCliente getClienteDAO();
    DAOFactura getFacturaDAO();
    DAOFacturaDetalle getFacturaDetalleDAO();
    DAOCompra getCompraDAO();
    DAOCompraDetalle getCompraDetalleDAO();
    //reportes
    DAOReportes getVerReportes();
    
    
    
    
}
