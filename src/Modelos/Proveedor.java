
package Modelos;


public class Proveedor {
    
    private Long id=null;
    private String nombre;
    private String dpi;
    private String nit;
    private String direccion;
    private String telefono;
    private String celular;
    private String email;
    private int estado;

    public Proveedor(){
        
        
    }
    public Proveedor(long id){
        this.id=id;
    }

    public Proveedor(String nombre, String dpi, String nit, String direccion, String telefono, String celular, String email, int estado) {
        this.nombre = nombre;
        this.dpi = dpi;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nombre=" + nombre + ", dpi=" + dpi + ", nit=" + nit + ", direccion=" + direccion + ", telefono=" + telefono + ", celular=" + celular + ", email=" + email + ", estado=" + estado + '}';
    }
    
   
    
}
