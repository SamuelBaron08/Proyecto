package edu.puj;

import java.time.LocalDate;

public class LlamadaNacional extends Llamada{

    public LlamadaNacional(long duracion, LocalDate fecha, long telefonoDestinario, long valor) {
        super(duracion, fecha, telefonoDestinario, valor);
    }

    @Override
    public long calcularValor() {
        setValor(0);
        return getValor();
    }
}
