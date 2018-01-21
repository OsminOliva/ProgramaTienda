
package Modelos;


public class Producto {
  private Long id = null;
  private String cod_barras;
  private int id_proveedor;
  private String nombre;
  private String descripcion;
  private int id_categoria;
  private int stock;
  private int stock_min;
  private float precio_uni;
  private float precio_costo;
  private int estado;
  private float utilidad;
  
  
  
    public Producto(){
        
    }
  

    public Producto(String cod_barras, int id_proveedor, String nombre, String descripcion, int id_categoria, int stock, int stock_min, float precio_uni, float precio_costo, int estado, float utilidad) {
        this.cod_barras = cod_barras;
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_categoria = id_categoria;
        this.stock = stock;
        this.stock_min = stock_min;
        this.precio_uni = precio_uni;
        this.precio_costo = precio_costo;
        this.estado = estado;
        this.utilidad = utilidad;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCod_barras() {
        return cod_barras;
    }

    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_min() {
        return stock_min;
    }

    public void setStock_min(int stock_min) {
        this.stock_min = stock_min;
    }

    public float getPrecio_uni() {
        return precio_uni;
    }

    public void setPrecio_uni(float precio_uni) {
        this.precio_uni = precio_uni;
    }

    public float getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(float precio_costo) {
        this.precio_costo = precio_costo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public float getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(float utilidad) {
        this.utilidad = utilidad;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", cod_barras=" + cod_barras + ", id_proveedor=" + id_proveedor + ", nombre=" + nombre + ", descripcion=" + descripcion + ", id_categoria=" + id_categoria + ", stock=" + stock + ", stock_min=" + stock_min + ", precio_uni=" + precio_uni + ", precio_costo=" + precio_costo + ", estado=" + estado + ", utilidad=" + utilidad + '}';
    }
    
   

    
  
  
}
