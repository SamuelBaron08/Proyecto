package edu.puj;

import java.io.Serializable;
import java.util.ArrayList;


public interface IEmpresa extends Serializable {

    ArrayList<Cliente> getClientes();
    ArrayList<Cuenta>  getCuentas();

    /** Carga clientes desde un archivo de texto */
    void cargarClientes(String ruta) throws Exception;

    /** Crea una nueva cuenta (prepago o postpago) y la asocia al cliente */
    long agregarCuenta(String tipo, String identificacionCliente, long numero) throws Exception;

    /** Registra una llamada nacional en la cuenta indicada */
    void registrarLlamadaNacional(long cuentaId, String fecha,
                                  long telefonoDestinario, long duracion) throws Exception;

    /** Registra una llamada internacional en la cuenta indicada */
    void registrarLlamadaInternacional(long cuentaId, String fecha,
                                       long telefonoDestinario, long duracion,
                                       String pais) throws Exception;

    /** Agrega una recarga a una cuenta prepago */
    void agregarRecarga(long cuentaId, String fecha, long valor) throws Exception;

    /** Genera el reporte de facturación postpago para un cliente y mes */
    String reportePostpago(int anio, int mes, String identificacionCliente) throws Exception;

    /** Genera el reporte de recargas de todas las cuentas prepago en un mes */
    String reporteRecargas(int anio, int mes);
}
