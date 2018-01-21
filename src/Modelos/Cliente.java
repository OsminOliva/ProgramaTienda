
package Modelos;

import java.util.Date;


public class Cliente {
    
    private Long idCliente=null;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String razon;
    private String nit;
    private String direccion;
    private String email;
    private String telefono;
    private Date fechaIng;
    private int estado;

    public Cliente(String nombre1, String nombre2, String apellido1, String apellido2, String razon, String nit, String direccion, String email, String telefono, Date fechaIng, int estado) {
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.razon = razon;
        this.nit = nit;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.fechaIng = fechaIng;
        this.estado = estado;
    }

    public Cliente() {
        
        
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(Date fechaIng) {
        this.fechaIng = fechaIng;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre1=" + nombre1 + ", nombre2=" + nombre2 + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", razon=" + razon + ", nit=" + nit + ", direccion=" + direccion + ", email=" + email + ", telefono=" + telefono + ", fechaIng=" + fechaIng + ", estado=" + estado + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
