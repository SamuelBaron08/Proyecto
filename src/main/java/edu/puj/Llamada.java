package edu.puj;

import java.time.LocalDate;

public abstract class Llamada {

    private long duracion;
    private LocalDate fecha;
    private long telefonoDestinario;
    private long valor;

    public Llamada(long duracion, LocalDate fecha, long telefonoDestinario, long valor) {
        this.duracion = duracion;
        this.fecha = fecha;
        this.telefonoDestinario = telefonoDestinario;
        this.valor = valor;
    }

    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public long getTelefonoDestinario() {
        return telefonoDestinario;
    }

    public void setTelefonoDestinario(long telefonoDestinario) {
        this.telefonoDestinario = telefonoDestinario;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "duracion=" + duracion +
                ", fecha=" + fecha +
                ", telefonoDestinario=" + telefonoDestinario +
                ", valor=" + valor +
                '}';
    }

    public abstract long calcularValor();
}
