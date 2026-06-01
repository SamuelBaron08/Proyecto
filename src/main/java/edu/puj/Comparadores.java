package edu.puj;

import java.util.Comparator;

public class Comparadores {

    /** Ordena llamadas por fecha */
    public static class ComparadorLlamadaPorFecha implements Comparator<Llamada> {
        @Override
        public int compare(Llamada l1, Llamada l2) {
            return l1.getFecha().compareTo(l2.getFecha());
        }
    }

    /** Ordena clientes por cédula  */
    public static class ComparadorClientePorCedula implements Comparator<Cliente> {
        @Override
        public int compare(Cliente c1, Cliente c2) {
            return c1.getIdentificacion().compareTo(c2.getIdentificacion());
        }
    }

    /** Ordena recargas por fecha*/
    public static class ComparadorRecargaPorFecha implements Comparator<Recarga> {
        @Override
        public int compare(Recarga r1, Recarga r2) {
            return r1.getFecha().compareTo(r2.getFecha());
        }
    }
}
