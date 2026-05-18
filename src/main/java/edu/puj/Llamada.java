package edu.puj;

import java.time.LocalDate;

public abstract class Llamada {

    private long duracion;
    private LocalDate fecha;
    private long telefonoDestinario;
    private long valor;

    public long calcularValor(){
        return 0;
    }
}
