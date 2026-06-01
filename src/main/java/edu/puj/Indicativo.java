package edu.puj;

import java.io.Serializable;

public enum Indicativo implements Serializable {

    ARGENTINA("Argentina", "54"),
    BRASIL("Brasil", "55"),
    CHILE("Chile", "56"),
    COLOMBIA("Colombia", "57"),
    ECUADOR("Ecuador", "593"),
    ESPANA("España", "34"),
    ESTADOS_UNIDOS("Estados Unidos", "1"),
    MEXICO("México", "52"),
    PANAMA("Panamá", "507"),
    PERU("Perú", "51"),
    VENEZUELA("Venezuela", "58"),
    FRANCIA("Francia", "33"),
    ALEMANIA("Alemania", "49"),
    ITALIA("Italia", "39"),
    REINO_UNIDO("Reino Unido", "44");

    private final String nombre;
    private final String codigo;

    Indicativo(String nombre, String codigo) {
        this.nombre  = nombre;
        this.codigo  = codigo;
    }

    public String getNombre()  { return nombre; }
    public String getCodigo()  { return codigo; }

    /**
     * Busca un Indicativo por nombre de país (insensible a mayúsculas/tildes básicas).
     * @return el Indicativo encontrado, o null si no existe.
     */
    public static Indicativo buscarPorNombre(String nombrePais) {
        for (Indicativo ind : values()) {
            if (ind.nombre.equalsIgnoreCase(nombrePais)) {
                return ind;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return nombre + " (+" + codigo + ")";
    }
}
