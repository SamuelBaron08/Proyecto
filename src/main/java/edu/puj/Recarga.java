package edu.puj;

import java.time.LocalDate;

public class Recarga {

    private LocalDate fecha;

    private long valor;

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Recarga{" +
                "fecha=" + fecha +
                ", valor=" + valor +
                '}';
    }
}
