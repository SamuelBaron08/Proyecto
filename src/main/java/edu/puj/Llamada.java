package edu.puj;

import java.io.Serializable;
import java.time.LocalDate;


public abstract class Llamada implements Serializable {

    private static final long serialVersionUID = 1L;

    private long duracion;
    private LocalDate fecha;
    private long telefonoDestinario;
    private long valor;

    public Llamada(long duracion, LocalDate fecha, long telefonoDestinario) {
        this.duracion = duracion;
        this.fecha = fecha;
        this.telefonoDestinario = telefonoDestinario;
        this.valor = 0;
    }


    public abstract long calcularValor(Cuenta cuenta);

    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long d) {
        this.duracion = d;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate f) {
        this.fecha = f;
    }

    public long getTelefonoDestinario() {
        return telefonoDestinario;
    }

    public void setTelefonoDestinario(long t) {
        this.telefonoDestinario = t;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long v) {
        this.valor = v;
    }

    @Override
    public String toString() {
        return "Llamada{fecha=" + fecha +
                ", duracion=" + duracion + " min" +
                ", tel=" + telefonoDestinario +
                ", valor=$" + valor + "}";
    }
}
