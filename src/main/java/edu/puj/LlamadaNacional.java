package edu.puj;

import java.time.LocalDate;


public class LlamadaNacional extends Llamada {

    private static final long serialVersionUID = 1L;

    public LlamadaNacional(long duracion, LocalDate fecha, long telefonoDestinario) {
        super(duracion, fecha, telefonoDestinario);
    }


    @Override
    public long calcularValor(Cuenta cuenta) {
        long valor;
        if (cuenta instanceof Postpago) {
            valor = 0L;
        } else {
            valor = getDuracion() * Utils.TARIFA_POR_MINUTO;
        }
        setValor(valor);
        return valor;
    }

    @Override
    public String toString() {
        return "LlamadaNacional{" +
                "fecha=" + getFecha() +
                ", duracion=" + getDuracion() + " min" +
                ", tel=" + getTelefonoDestinario() +
                ", valor=$" + getValor() +
                '}';
    }
}
