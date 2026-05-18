package edu.puj;

import java.time.LocalDate;

public class LlamadaInternacional extends Llamada{

    public String paisDestinario;

    public LlamadaInternacional(long duracion, LocalDate fecha, long telefonoDestinario, long valor, String paisDestinario) {
        super(duracion, fecha, telefonoDestinario, valor);
        this.paisDestinario = paisDestinario;
    }


}
