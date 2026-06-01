package edu.puj;

import java.io.Serializable;
import java.util.ArrayList;



public abstract class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long numero;                          // número de teléfono asignado
    private ArrayList<Llamada> llamadas = new ArrayList<>();

    public Cuenta(long id, long numero) {
        this.id = id;
        this.numero = numero;
    }


    public abstract long obtenerPagoCuenta(int anio, int mes);

    public void agregarLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }


    public ArrayList<Llamada> getLlamadasDelMes(int anio, int mes) {
        ArrayList<Llamada> resultado = new ArrayList<>();
        for (Llamada l : llamadas) {
            if (Utils.perteneceAlMes(l.getFecha(), anio, mes)) {
                resultado.add(l);
            }
        }
        return resultado;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public ArrayList<Llamada> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(ArrayList<Llamada> l) {
        this.llamadas = l;
    }

    @Override
    public String toString() {
        return "Cuenta{id=" + id + ", numero=" + numero + "}";
    }
}
