package edu.puj;

public class Postpago extends Cuenta{

    private long cargoFijo;

    public Postpago(long id, long numero, long cargoFijo) {
        super(id, numero);
        this.cargoFijo = cargoFijo;
    }

    @Override
    public long obtenerPagoCuenta() {
        return super.obtenerPagoCuenta();
    }
}
