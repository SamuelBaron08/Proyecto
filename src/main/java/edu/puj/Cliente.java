package edu.puj;

public class    Cliente {

    private String direccion;
    private String identificacion;
    private String nombre;
    private String tipold;
    private Cuenta cuenta;

    public Cliente(String nombre, String identificacion, String tipold, String direccion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.tipold = tipold;
        this.direccion = direccion;
        this.cuenta = null;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipold() {
        return tipold;
    }

    public void setTipold(String tipold) {
        this.tipold = tipold;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "direccion='" + direccion + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipold='" + tipold + '\'' +
                ", cuenta=" + cuenta +
                '}';
    }
}
