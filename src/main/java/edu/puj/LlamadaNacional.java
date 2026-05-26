package edu.puj;

import java.time.LocalDate;

public abstract class LlamadaNacional extends Llamada{

    public LlamadaNacional(long duracion, LocalDate fecha, long telefonoDestinario, long valor) {
        super(duracion, fecha, telefonoDestinario, valor);
    }

    @Override
    public long calcularValor(Cuenta cuenta) {
        long valor;
        if (cuenta instanceof Postpago) {
            valor = 0;
        } else {
            valor = getDuracion() * Utils.TARIFA_POR_MINUTO;
        }
        setValor(valor);
        return valor;
    }
}
