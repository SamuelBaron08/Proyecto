package edu.puj;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


public class Empresa implements IEmpresa {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> c) {
        this.clientes = c;
    }

    @Override
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> c) {
        this.cuentas = c;
    }

    @Override
    public void cargarClientes(String ruta) throws Exception {
        ArrayList<Cliente> nuevos = ManejoArchivos.leerClientes(ruta);
        int agregados   = 0;
        int repetidos   = 0;
        for (Cliente nuevo : nuevos) {
            if (buscarClientePorId(nuevo.getIdentificacion()) == null) {
                clientes.add(nuevo);
                agregados++;
            } else {
                repetidos++;
            }
        }
        System.out.println("Clientes cargados: " + agregados +
                (repetidos > 0 ? "  Repetidos ignorados: " + repetidos : ""));
    }

    @Override
    public long agregarCuenta(String tipo, String identificacionCliente, long numero)
            throws Exception {

        Cliente cliente = buscarClientePorId(identificacionCliente);
        if (cliente == null) {
            throw new JavemovilException("No existe un cliente con identificación: " + identificacionCliente);
        }
        if (cliente.tieneCuenta()) {
            throw new JavemovilException("El cliente ya tiene una cuenta asignada. " + "ID cuenta: " + cliente.getCuenta().getId());
        }

        long id = Utils.obtenerSiguienteConsecutivo();
        Cuenta nuevaCuenta;

        if (tipo.equalsIgnoreCase("prepago")) {
            nuevaCuenta = new Prepago(id, numero);
        } else if (tipo.equalsIgnoreCase("postpago")) {
            nuevaCuenta = new Postpago(id, numero);
        } else {
            throw new JavemovilException("Tipo de cuenta inválido: '" + tipo +
                    "'. Use 'prepago' o 'postpago'.");
        }

        cuentas.add(nuevaCuenta);
        cliente.setCuenta(nuevaCuenta);
        return id;
    }


    @Override
    public void registrarLlamadaNacional(long cuentaId, String fecha, long telefonoDestinario, long duracion)
            throws Exception {

        Cuenta cuenta = buscarCuentaPorId(cuentaId);
        if (cuenta == null) {
            throw new JavemovilException("No existe una cuenta con ID: " + cuentaId);
        }

        LocalDate fechaLocal = Utils.convertirStringFecha(fecha);
        LlamadaNacional llamada = new LlamadaNacional(duracion, fechaLocal, telefonoDestinario);
        long valor = llamada.calcularValor(cuenta);

        if (cuenta instanceof Prepago) {
            Prepago prepago = (Prepago) cuenta;
            int anio = fechaLocal.getYear();
            int mes  = fechaLocal.getMonthValue();
            if (!prepago.tieneSaldoSuficiente(valor, anio, mes)) {
                throw new JavemovilException(
                        "Saldo insuficiente en cuenta prepago " + cuentaId + ". Saldo disponible: $" + prepago.calcularSaldoMes(anio, mes) + "  Costo llamada: $" + valor);
            }
        }

        cuenta.agregarLlamada(llamada);
        System.out.println("Llamada nacional registrada. Valor: $" + valor);
    }


    @Override
    public void registrarLlamadaInternacional(long cuentaId, String fecha, long telefonoDestinario, long duracion, String pais) throws Exception {

        Cuenta cuenta = buscarCuentaPorId(cuentaId);
        if (cuenta == null) {
            throw new JavemovilException("No existe una cuenta con ID: " + cuentaId);
        }

        Indicativo indicativo = Indicativo.buscarPorNombre(pais);
        if (indicativo == null) {
            throw new JavemovilException("País no reconocido: '" + pais + "'. Países disponibles: " +
                    "Argentina, Brasil, Chile, Colombia, Ecuador, España, Estados Unidos, México, " +
                    "Panamá, Perú, Venezuela, Francia, Alemania, Italia, Reino Unido.");
        }

        LocalDate fechaLocal = Utils.convertirStringFecha(fecha);
        LlamadaInternacional llamada = new LlamadaInternacional(
                duracion, fechaLocal, telefonoDestinario, indicativo);
        long valor = llamada.calcularValor(cuenta);

        if (cuenta instanceof Prepago) {
            Prepago prepago = (Prepago) cuenta;
            int anio = fechaLocal.getYear();
            int mes  = fechaLocal.getMonthValue();
            if (!prepago.tieneSaldoSuficiente(valor, anio, mes)) {
                throw new JavemovilException(
                        "Saldo insuficiente en cuenta prepago " + cuentaId + "Saldo disponible: $" + prepago.calcularSaldoMes(anio, mes) + "  Costo llamada: $" + valor);
            }
        }

        cuenta.agregarLlamada(llamada);
        System.out.println("Llamada internacional registrada a " + pais + " (" + llamada.getTelefonoConIndicativo() + "). Valor: $" + valor);
    }

    @Override
    public void agregarRecarga(long cuentaId, String fecha, long valor) throws Exception {
        Cuenta cuenta = buscarCuentaPorId(cuentaId);
        if (cuenta == null) {
            throw new JavemovilException("No existe una cuenta con ID: " + cuentaId);
        }
        if (!(cuenta instanceof Prepago)) {
            throw new JavemovilException(
                    "La cuenta " + cuentaId + " no es de tipo prepago. " +
                            "Solo las cuentas prepago pueden recibir recargas.");
        }
        LocalDate fechaLocal = Utils.convertirStringFecha(fecha);
        Recarga recarga = new Recarga(fechaLocal, valor);
        ((Prepago) cuenta).agregarRecarga(recarga);
        System.out.println("Recarga de $" + valor + " agregada a la cuenta " + cuentaId);
    }
    @Override
    public String reportePostpago(int anio, int mes, String identificacionCliente) throws Exception {
        Cliente cliente = buscarClientePorId(identificacionCliente);
        if (cliente == null)
            throw new JavemovilException("No existe cliente con identificacion: " + identificacionCliente);
        if (!cliente.tieneCuenta() || !(cliente.getCuenta() instanceof Postpago))
            throw new JavemovilException("El cliente " + cliente.getNombre() + " no tiene cuenta postpago.");

        Postpago cuenta = (Postpago) cliente.getCuenta();

        String reporte = "";
        reporte += "REPORTE POSTPAGO - " + mes + "/" + anio + "\n";
        reporte += "Cliente : " + cliente.getNombre()         + "\n";
        reporte += "Tipo ID : " + cliente.getTipold()         + "\n";
        reporte += "Cedula  : " + cliente.getIdentificacion() + "\n";
        reporte += "Direc.  : " + cliente.getDireccion()      + "\n";
        reporte += "Cuenta ID: " + cuenta.getId() + "  Numero: " + cuenta.getNumero() + "\n";
        reporte += "Cargo fijo: $" + cuenta.getCargoFijo()    + "\n";
        reporte += "LLAMADAS DEL MES:\n";

        ArrayList<Llamada> llamadas = cuenta.getLlamadasDelMes(anio, mes);
        Collections.sort(llamadas, new Comparadores.ComparadorLlamadaPorFecha());

        long totalDuracion = 0;
        long totalValor    = 0;

        if (llamadas.isEmpty()) {
            reporte += "  (Sin llamadas en este mes)\n";
        } else {
            for (Llamada l : llamadas) {
                String linea = "  Fecha: " + l.getFecha() + " Duracion: " + l.getDuracion() + " min";
                if (l instanceof LlamadaInternacional) {
                    LlamadaInternacional li = (LlamadaInternacional) l;
                    linea += "  Tel: (" + li.getIndicativo().getCodigo() + ")" + l.getTelefonoDestinario();
                    linea += "  Pais: " + li.getPaisDestino();
                    linea += " |Tipo: Internacional";
                } else {
                    linea += "  Tel: " + l.getTelefonoDestinario();
                    linea += "  Tipo: Nacional";
                }
                linea += "  Valor: $" + l.getValor() + "\n";
                reporte += linea;
                totalDuracion += l.getDuracion();
                totalValor    += l.getValor();
            }
        }

        reporte += "Total duracion: " + totalDuracion + " min\n";
        reporte += "Total llamadas: $" + totalValor   + "\n";
        reporte += "TOTAL A PAGAR: $" + cuenta.obtenerPagoCuenta(anio, mes) + "\n";

        return reporte;
    }
    @Override
    public String reporteRecargas(int anio, int mes) {
        String reporte = "";
        reporte += "REPORTE RECARGAS PREPAGO " + mes + " " + anio + "\n";

        ArrayList<Cliente> clientesPrepago = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c.tieneCuenta() && c.getCuenta() instanceof Prepago)
                clientesPrepago.add(c);
        }
        Collections.sort(clientesPrepago, new Comparadores.ComparadorClientePorCedula());

        if (clientesPrepago.isEmpty()) {
            reporte += "No hay clientes con cuentas prepago.\n";
            return reporte;
        }

        long totalRecargasGlobal = 0;
        long totalDuracionGlobal = 0;

        for (Cliente cliente : clientesPrepago) {
            Prepago cuenta = (Prepago) cliente.getCuenta();

            reporte += "Cliente : " + cliente.getNombre()         + "\n";
            reporte += "Tipo ID : " + cliente.getTipold()           + "\n";
            reporte += "Cedula  : " + cliente.getIdentificacion()   + "\n";
            reporte += "Direc.  : " + cliente.getDireccion()        + "\n";
            reporte += "  Cuenta ID: " + cuenta.getId() + "  Numero: " + cuenta.getNumero() + "\n";

            ArrayList<Llamada> llamadas = cuenta.getLlamadasDelMes(anio, mes);
            Collections.sort(llamadas, new Comparadores.ComparadorLlamadaPorFecha());

            long duracionCliente      = 0;
            long valorLlamadasCliente = 0;

            reporte += "  LLAMADAS:\n";
            if (llamadas.isEmpty()) {
                reporte += "    (Sin llamadas en este mes)\n";
            } else {
                for (Llamada l : llamadas) {
                    String linea = "    Fecha: " + l.getFecha()
                            + " Duracion: " + l.getDuracion() + " min";
                    if (l instanceof LlamadaInternacional) {
                        LlamadaInternacional li = (LlamadaInternacional) l;
                        linea += "  Tel: (" + li.getIndicativo().getCodigo() + ")" + l.getTelefonoDestinario();
                    } else {
                        linea += "  Tel: " + l.getTelefonoDestinario();
                    }
                    linea += " |Valor: $" + l.getValor() + "\n";
                    reporte += linea;
                    duracionCliente      += l.getDuracion();
                    valorLlamadasCliente += l.getValor();
                }
            }
            reporte += "  Total duracion: " + duracionCliente + " min Total valor: $" + valorLlamadasCliente + "\n";

            ArrayList<Recarga> recargas = cuenta.getRecargasDelMes(anio, mes);
            Collections.sort(recargas, new Comparadores.ComparadorRecargaPorFecha());

            long totalRecargasCliente = 0;

            reporte += "  RECARGAS:\n";
            if (recargas.isEmpty()) {
                reporte += "    (Sin recargas en este mes)\n";
            } else {
                for (Recarga r : recargas) {
                    reporte += "    Fecha: " + r.getFecha() + " Valor: $" + r.getValor() + "\n";
                    totalRecargasCliente += r.getValor();
                }
            }
            reporte += "  Total recargas: $" + totalRecargasCliente + "\n";

            totalRecargasGlobal += totalRecargasCliente;
            totalDuracionGlobal += duracionCliente;
        }

        reporte += "\nTOTALES GLOBALES:\n";
        reporte += "  Total recargas: $" + totalRecargasGlobal + "\n";
        reporte += "  Total duracion: " + totalDuracionGlobal  + " min\n";

        return reporte;
    }


    private Cliente buscarClientePorId(String identificacion) {
        for (Cliente c : clientes) {
            if (c.getIdentificacion().equals(identificacion)) {
                return c;
            }
        }
        return null;
    }


    private Cuenta buscarCuentaPorId(long id) {
        for (Cuenta c : cuentas) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Empresa{nombre='" + nombre + '\'' +
                ", clientes=" + clientes.size() +
                ", cuentas=" + cuentas.size() + "}";
    }
}
