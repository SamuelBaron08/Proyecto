package edu.puj;

import java.time.LocalDate;

import static edu.puj.Utils.TARIFA_POR_MINUTO;

public abstract class LlamadaInternacional extends Llamada {

    private static final double RECARGO_INTERNACIONAL = 0.20;
    private String paisDestinario;

    public LlamadaInternacional(long duracion, LocalDate fecha, long telefonoDestinario, long valor, String paisDestinario) {
        super(duracion, fecha, telefonoDestinario, valor);
        this.paisDestinario = paisDestinario;
    }

    @Override
    public long calcularValor(Cuenta cuenta) {
        long valorBase;
        if (cuenta instanceof Postpago) {
            valorBase = 0;
        } else {
            valorBase = getDuracion() * TARIFA_POR_MINUTO;
        }
        long recargo = Math.round(valorBase * RECARGO_INTERNACIONAL);
        long total = valorBase + recargo;
        setValor(total);
        return total;
    }

    public String getPaisDestinario() {
        return paisDestinario;
    }

    public void setPaisDestinario(String paisDestinario) {
        this.paisDestinario = paisDestinario;
    }
}
