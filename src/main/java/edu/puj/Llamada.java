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

    public long calcularValor(){
        return 0;
    }
}
