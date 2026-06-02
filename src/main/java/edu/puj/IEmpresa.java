package edu.puj;

import java.io.Serializable;
import java.util.ArrayList;


public interface IEmpresa extends Serializable {

    ArrayList<Cliente> getClientes();

    ArrayList<Cuenta> getCuentas();


    void cargarClientes(String ruta) throws Exception;


    long agregarCuenta(String tipo, String identificacionCliente, long numero) throws Exception;


    void registrarLlamadaNacional(long cuentaId, String fecha,
                                  long telefonoDestinario, long duracion) throws Exception;


    void registrarLlamadaInternacional(long cuentaId, String fecha,
                                       long telefonoDestinario, long duracion,
                                       String pais) throws Exception;


    void agregarRecarga(long cuentaId, String fecha, long valor) throws Exception;


    String reportePostpago(int anio, int mes, String identificacionCliente) throws Exception;


    String reporteRecargas(int anio, int mes);
}
