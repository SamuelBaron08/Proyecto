package edu.puj;

public class Prepago extends Cuenta{

    private long numeroMinutos;

    public Prepago(long id, long numero, long numeroMinutos) {
        super(id, numero);
        this.numeroMinutos = numeroMinutos;
    }

    @Override
    public long obtenerPagoCuenta() {
        return super.obtenerPagoCuenta();
    }
}
