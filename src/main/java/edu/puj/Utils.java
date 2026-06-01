package edu.puj;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Utils {
    public static final long CARGO_FIJO_DEFAULT = 20000L;
    public static final long MINUTOS_DEFAULT_PREPAGO = 5L;
    public static final double RECARGO_INTERNACIONAL = 0.20;
    public static final long TARIFA_POR_MINUTO = 200L;

    public static long CONSECUTIVO = 1L;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Utils() {
    }

    public static long obtenerSiguienteConsecutivo() {
        return CONSECUTIVO++;
    }

    public static LocalDate convertirStringFecha(String fecha) throws Exception {
        try {
            return LocalDate.parse(fecha, FORMATO);
        } catch (DateTimeParseException e) {
            throw new Exception("Formato de fecha inválido. Use yyyy-MM-dd. Recibido: " + fecha);
        }
    }

    public static String convertirFechaString(LocalDate fecha) {
        return fecha.format(FORMATO);
    }

    public static boolean perteneceAlMes(LocalDate fecha, int anio, int mes) {
        return fecha.getYear() == anio && fecha.getMonthValue() == mes;
    }
}
