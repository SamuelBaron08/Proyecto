package edu.puj;

import java.util.ArrayList;

public abstract class Cuenta {

    private long id;
    private long numero;
    private ArrayList<Llamada> llamadas = new ArrayList<>();

    public Cuenta(long id, long numero) {
        this.id = id;
        this.numero = numero;
    }

    public abstract long obtenerPagoCuenta(int año, int mes);
}
