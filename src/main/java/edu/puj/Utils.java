package edu.puj;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Utils {

    public static final long CARGO_FIJO_DEFAULT = 20000;
    public static final long MINUTOS_DEFAULT_PREPAGO = 5;
    public static final double RECARGO_INTERNACIONAL = 0.20;
    public static final long TARIFA_POR_MINUTO = 200;
    private static final DateTimeFormatter Formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static long CONSECUTIVO = 1;

    private Utils() {
    }

    public static long obtenerSiguienteConsecutivo() {
        return CONSECUTIVO++;
    }

    public static LocalDate convertirStringFecha(String fecha) {
        return LocalDate.parse(fecha, Formato);
    }

    public static String convertirFechaString(LocalDate fecha) {
        return fecha.format(Formato);
    }

    public static boolean perteneceAlMes(LocalDate fecha, int año, int mes) {
        return fecha.getYear() == año && fecha.getMonthValue() == mes;
    }
}