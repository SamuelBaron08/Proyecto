package edu.puj;

public class JavemovilException extends Exception {

    private static final long serialVersionUID = 1L;

    public JavemovilException(String mensaje) {
        super(mensaje);
    }

    public JavemovilException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
