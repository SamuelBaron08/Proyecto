
package edu.puj;

import java.time.LocalDate;

public class LlamadaInternacional extends Llamada {

    private static final long serialVersionUID = 1L;

    private Indicativo indicativo;
    private String paisDestino;
    private String telefonoConIndicativo;


    public LlamadaInternacional(long duracion, LocalDate fecha, long telefonoDestinario, Indicativo indicativo) {
        super(duracion, fecha, telefonoDestinario);
        this.indicativo = indicativo;
        this.paisDestino = indicativo.getNombre();
        this.telefonoConIndicativo = indicativo.getCodigo() + "-" + telefonoDestinario;
    }


    @Override
    public long calcularValor(Cuenta cuenta) {

        long valorBase = getDuracion() * Utils.TARIFA_POR_MINUTO;
        long recargo = Math.round(valorBase * Utils.RECARGO_INTERNACIONAL);
        long total = valorBase + recargo;
        setValor(total);
        return total;
    }


    public Indicativo getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(Indicativo i) {
        this.indicativo = i;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String p) {
        this.paisDestino = p;
    }

    public String getTelefonoConIndicativo() {
        return telefonoConIndicativo;
    }

    public void setTelefonoConIndicativo(String t) {
        this.telefonoConIndicativo = t;
    }

    @Override
    public String toString() {
        return "LlamadaInternacional{" +
                "fecha=" + getFecha() +
                ", duracion=" + getDuracion() + " min" +
                ", tel=(" + indicativo.getCodigo() + ")" + getTelefonoDestinario() +
                ", pais=" + paisDestino +
                ", valor=$" + getValor() +
                '}';
    }
}
