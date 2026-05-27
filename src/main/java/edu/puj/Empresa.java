package edu.puj;

import java.util.ArrayList;

public class Empresa implements IEmpresa {

    private String nombre;
    public ArrayList<Cliente> Clientes = new ArrayList<>();
    public ArrayList<Cuenta> Cuentas = new ArrayList<>();

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        Clientes = clientes;
    }


    public void setCuentas(ArrayList<Cuenta> cuentas) {
        Cuentas = cuentas;
    }

    @Override
    public ArrayList<Cliente> getClientes() {
        return Clientes;
    }

    @Override
    public ArrayList<Cuenta> getCuentas() {
        return null;
    }

    @Override
    public void cargarClientes(String ruta) throws Exception {

    }

    @Override
    public long agregarCuenta(String tipo, String identificacionCliente, long numero) throws Exception {
        return 0;
    }

    @Override
    public void registrarLlamadaNacional(long cuentaId, String fecha, long telefonoDestinario, long duracion) throws Exception {

    }

    @Override
    public void registrarLlamadaInternacional(long cuentaId, String fecha, long telefonoDestinario, long duracion, String pais) throws Exception {

    }

    @Override
    public void agregarRecarga(long cuentaId, String fecha, long valor) throws Exception {

    }

    @Override
    public String reportePostpago(int anio, int mes, String identificacionCliente) throws Exception {
        return "";
    }

    @Override
    public String reporteRecargas(int anio, int mes) {
        return "";
    }
}