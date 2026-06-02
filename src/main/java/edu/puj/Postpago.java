package edu.puj;

public class Postpago extends Cuenta {

    private static final long serialVersionUID = 1L;

    private long cargoFijo;

    public Postpago(long id, long numero) {
        super(id, numero);
        this.cargoFijo = Utils.CARGO_FIJO_DEFAULT;
    }

    public Postpago(long id, long numero, long cargoFijo) {
        super(id, numero);
        this.cargoFijo = cargoFijo;
    }

    @Override
    public long obtenerPagoCuenta(int anio, int mes) {
        long totalInternacionales = 0;
        for (Llamada l : getLlamadas()) {
            if (l instanceof LlamadaInternacional && Utils.perteneceAlMes(l.getFecha(), anio, mes)) {
                totalInternacionales += l.getValor();
            }
        }
        return cargoFijo + totalInternacionales;
    }


    public long getCargoFijo() {
        return cargoFijo;
    }

    public void setCargoFijo(long c) {
        this.cargoFijo = c;
    }

    @Override
    public String toString() {
        return "Postpago{id=" + getId() +
                ", numero=" + getNumero() +
                ", cargoFijo=$" + cargoFijo + "}";
    }
}
