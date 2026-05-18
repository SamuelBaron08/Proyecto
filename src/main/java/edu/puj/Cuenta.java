package edu.puj;

public abstract class Cuenta {

    private long id;
    private long numero;

    public Cuenta(long id, long numero) {
        this.id = id;
        this.numero = numero;
    }

    public long obtenerPagoCuenta() {


        return 0;
    }

}
