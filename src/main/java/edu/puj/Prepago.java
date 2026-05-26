package edu.puj;

import java.util.ArrayList;

public class Prepago extends Cuenta{

    private long numeroMinutos;
    private ArrayList<Recarga> recargas= new ArrayList<>();

    public Prepago(long id, long numero, long numeroMinutos) {
        super(id, numero);
        this.numeroMinutos = numeroMinutos;
    }


    @Override
    public long obtenerPagoCuenta(int año, int mes) {
        return 0;
    }
}
