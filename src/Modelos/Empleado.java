
package Modelos;

import java.util.Calendar;
import java.util.Date;



public class Empleado {
    
    private Long id=null;
    private Date fechaIng;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String email;
    private float sueldo;
    private String sexo;
    private Date fechaNac;
    private int estado;
    private String direccion;
    private String telefono;
    private String celular;
    private String dpi;
    private String usuario;
    private String contrasena;
    private int idTipo;
    
    
    
    public Empleado(){
        
    }
    
    public Empleado(String nombre,String apellido){
        this.nombre1=nombre;
        this.apellido1=apellido;
        
    }

    public Empleado(Date fechaIng, String nombre1, String nombre2, String apellido1, String apellido2, String email, float sueldo, String sexo, Date fechaNac, int estado, String direccion, String telefono, String celular, String dpi, String usuario, String contrasena, int idTipo) {
        this.fechaIng = fechaIng;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.sueldo = sueldo;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
        this.estado = estado;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.dpi = dpi;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.idTipo = idTipo;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(Date fechaIng) {
        this.fechaIng = fechaIng;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
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

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", fechaIng=" + fechaIng + ", nombre1=" + nombre1 + ", nombre2=" + nombre2 + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", email=" + email + ", sueldo=" + sueldo + ", sexo=" + sexo + ", fechaNac=" + fechaNac + ", estado=" + estado + ", direccion=" + direccion + ", telefono=" + telefono + ", celular=" + celular + ", dpi=" + dpi + ", usuario=" + usuario + ", contrasena=" + contrasena + ", idTipo=" + idTipo + '}';
    }

   
    
       
    
    
    
    
}
