package edu.puj;

import java.time.LocalDate;

public class LlamadaInternacional extends Llamada{

    private static final double RECARGO_INTERNACIONAL = 0.20;
    public String paisDestinario;

    public LlamadaInternacional(long duracion, LocalDate fecha, long telefonoDestinario, long valor, String paisDestinario) {
        super(duracion, fecha, telefonoDestinario, valor);
        this.paisDestinario = paisDestinario;
    }

    @Override
    public long calcularValor() {
        long valorBase = 0;
        long recargo = Math.round(valorBase * RECARGO_INTERNACIONAL);
        long total = valorBase + recargo;
        setValor(total);
        return total;
    }
}
