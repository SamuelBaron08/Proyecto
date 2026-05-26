package edu.puj;

public class Postpago extends Cuenta {

    private long cargoFijo;

    public Postpago(long id, long numero, long cargoFijo) {
        super(id, numero);
        this.cargoFijo = cargoFijo;
    }

    @Override
    public long obtenerPagoCuenta(int anio, int mes) {
        // Ingresos postpago = cargo fijo + llamadas internacionales del mes
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

    public void setCargoFijo(long cargoFijo) {
        this.cargoFijo = cargoFijo;
    }

    @Override
    public String toString() {
        return "Postpago{id=" + getId() + ", numero=" + getNumero() +
                ", cargoFijo=" + cargoFijo + "}";
    }
}