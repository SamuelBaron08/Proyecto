package edu.puj;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase abstracta que representa una cuenta de telefonía.
 * Se especializa en Prepago y Postpago.
 * Contiene la lista de llamadas realizadas desde esta cuenta.
 */
public abstract class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long numero;                          // número de teléfono asignado
    private ArrayList<Llamada> llamadas = new ArrayList<>();

    public Cuenta(long id, long numero) {
        this.id     = id;
        this.numero = numero;
    }

    /**
     * Calcula el pago/ingreso esperado de esta cuenta en el mes indicado.
     * Implementado de forma distinta en Prepago y Postpago.
     */
    public abstract long obtenerPagoCuenta(int anio, int mes);

    /** Agrega una llamada a la lista de llamadas de esta cuenta */
    public void agregarLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }

    /** Retorna las llamadas realizadas en el mes y año indicados */
    public ArrayList<Llamada> getLlamadasDelMes(int anio, int mes) {
        ArrayList<Llamada> resultado = new ArrayList<>();
        for (Llamada l : llamadas) {
            if (Utils.perteneceAlMes(l.getFecha(), anio, mes)) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // ── Getters y Setters ────────────────────────────────────────────────────

    public long getId()                          { return id; }
    public void setId(long id)                   { this.id = id; }

    public long getNumero()                      { return numero; }
    public void setNumero(long numero)           { this.numero = numero; }

    public ArrayList<Llamada> getLlamadas()      { return llamadas; }
    public void setLlamadas(ArrayList<Llamada> l){ this.llamadas = l; }

    @Override
    public String toString() {
        return "Cuenta{id=" + id + ", numero=" + numero + "}";
    }
}
