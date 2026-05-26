package edu.puj;

import java.time.LocalDate;

public class LlamadaNacional extends Llamada {

    public LlamadaNacional(long duracion, LocalDate fecha, String telefonoDestinario) {
        super(duracion, fecha, telefonoDestinario);
    }

    @Override
    public long calcularValor(Cuenta cuenta) {
        long valor;
        if (cuenta instanceof Postpago) {
            // Llamadas nacionales son ilimitadas (valor = 0) para postpago
            valor = 0;
        } else {
            // Para prepago se cobra por minuto
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
                ", valor=" + getValor() +
                '}';
    }
}
