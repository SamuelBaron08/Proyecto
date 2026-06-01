package edu.puj;

import java.util.ArrayList;

public class Prepago extends Cuenta {

    private static final long serialVersionUID = 1L;

    private long numeroMinutos;
    private ArrayList<Recarga> recargas = new ArrayList<>();

    public Prepago(long id, long numero) {
        super(id, numero);
        this.numeroMinutos = Utils.MINUTOS_DEFAULT_PREPAGO; // 5 minutos por defecto
    }

    public Prepago(long id, long numero, long numeroMinutos) {
        super(id, numero);
        this.numeroMinutos = numeroMinutos;
    }


    @Override
    public long obtenerPagoCuenta(int anio, int mes) {
        long total = 0;
        for (Recarga r : recargas) {
            if (Utils.perteneceAlMes(r.getFecha(), anio, mes)) {
                total += r.getValor();
            }
        }
        return total;
    }


    public long calcularSaldoMes(int anio, int mes) {
        long totalRecargas = obtenerPagoCuenta(anio, mes);
        long totalLlamadas = 0;
        for (Llamada l : getLlamadasDelMes(anio, mes)) {
            totalLlamadas += l.getValor();
        }
        return totalRecargas - totalLlamadas;
    }


    public boolean tieneSaldoSuficiente(long valorLlamada, int anio, int mes) {
        long totalRecargas = 0;
        for (Recarga r : recargas) {
            totalRecargas += r.getValor();
        }
        long totalLlamadas = 0;
        for (Llamada l : getLlamadas()) {
            totalLlamadas += l.getValor();
        }
        return (totalRecargas - totalLlamadas) >= valorLlamada;
    }

    public void agregarRecarga(Recarga recarga) {
        recargas.add(recarga);
    }


    public ArrayList<Recarga> getRecargasDelMes(int anio, int mes) {
        ArrayList<Recarga> resultado = new ArrayList<>();
        for (Recarga r : recargas) {
            if (Utils.perteneceAlMes(r.getFecha(), anio, mes)) {
                resultado.add(r);
            }
        }
        return resultado;
    }


    public long getNumeroMinutos() {
        return numeroMinutos;
    }

    public void setNumeroMinutos(long n) {
        this.numeroMinutos = n;
    }

    public ArrayList<Recarga> getRecargas() {
        return recargas;
    }

    public void setRecargas(ArrayList<Recarga> r) {
        this.recargas = r;
    }

    @Override
    public String toString() {
        return "Prepago{id=" + getId() +
                ", numero=" + getNumero() +
                ", minutos=" + numeroMinutos +
                ", recargas=" + recargas.size() + "}";
    }
}
