package edu.puj;

import java.util.Scanner;

public class TestConsola {

    private static IEmpresa empresa = new Empresa("JaveMovil");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = -1;
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
                continue;
            }
            switch (opcion) {
                case 1: menuCargarClientes();    break;
                case 2: menuAgregarCuenta();     break;
                case 3: menuRegistrarLlamada();  break;
                case 4: menuAgregarRecarga();    break;
                case 5: menuReportePostpago();   break;
                case 6: menuReporteRecargas();   break;
                case 7: menuGuardarSistema();    break;
                case 8: menuCargarSistema();     break;
                case 0: System.out.println("Hasta luego."); break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("   JAVEMOVIL - SISTEMA DE FACTURACION  ");
        System.out.println("1. Cargar clientes desde archivo");
        System.out.println("2. Agregar cuenta (prepago/postpago)");
        System.out.println("3. Registrar llamada");
        System.out.println("4. Agregar recarga");
        System.out.println("5. Reporte facturacion postpago");
        System.out.println("6. Reporte recargas prepago");
        System.out.println("7. Guardar sistema en archivo");
        System.out.println("8. Cargar sistema desde archivo");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }


    private static void menuCargarClientes() {
        System.out.println("\n-- Cargar Clientes --");
        System.out.println("1. Seleccionar archivo de clientes");
        System.out.println("2. Regresar al menu");
        System.out.print("Opcion: ");
        String op = scanner.nextLine().trim();
        if (op.equals("1")) {
            System.out.print("Ruta del archivo: ");
            String ruta = scanner.nextLine().trim();
            try {
                empresa.cargarClientes(ruta);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private static void menuAgregarCuenta() {
        System.out.println("\n-- Agregar Cuenta --");
        System.out.print("Tipo (prepago/postpago): ");
        String tipo = scanner.nextLine().trim();
        System.out.print("Identificacion del cliente: ");
        String idCliente = scanner.nextLine().trim();
        System.out.print("Numero de telefono: ");
        try {
            long numero = Long.parseLong(scanner.nextLine().trim());
            long id = empresa.agregarCuenta(tipo, idCliente, numero);
            System.out.println("Cuenta creada con id: " + id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void menuRegistrarLlamada() {
        System.out.println("\n-- Registrar Llamada --");
        System.out.print("Id de la cuenta: ");
        try {
            long cuentaId = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Tipo (nacional/internacional): ");
            String tipo = scanner.nextLine().trim();
            System.out.print("Fecha (yyyy-MM-dd): ");
            String fecha = scanner.nextLine().trim();
            System.out.print("Telefono destinatario: ");
            long tel = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Duracion (minutos): ");
            long duracion = Long.parseLong(scanner.nextLine().trim());

            if (tipo.equalsIgnoreCase("nacional")) {
                empresa.registrarLlamadaNacional(cuentaId, fecha, tel, duracion);
            } else if (tipo.equalsIgnoreCase("internacional")) {
                System.out.print("Pais de destino: ");
                String pais = scanner.nextLine().trim();
                empresa.registrarLlamadaInternacional(cuentaId, fecha, tel, duracion, pais);
            } else {
                System.out.println("Tipo no valido.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

        private static void menuAgregarRecarga() {
        System.out.println("\n-- Agregar Recarga --");
        System.out.print("Id de la cuenta prepago: ");
        try {
            long cuentaId = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Fecha (yyyy-MM-dd): ");
            String fecha = scanner.nextLine().trim();
            System.out.print("Valor: ");
            long valor = Long.parseLong(scanner.nextLine().trim());
            empresa.agregarRecarga(cuentaId, fecha, valor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void menuReportePostpago() {
        System.out.println("\n-- Reporte Facturacion Postpago --");
        System.out.print("Anio: ");
        try {
            int anio = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Mes (1-12): ");
            int mes = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Identificacion del cliente: ");
            String id = scanner.nextLine().trim();
            System.out.println(empresa.reportePostpago(anio, mes, id));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void menuReporteRecargas() {
        System.out.println("\n-- Reporte Recargas Prepago --");
        System.out.print("Anio: ");
        try {
            int anio = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Mes (1-12): ");
            int mes = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(empresa.reporteRecargas(anio, mes));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void menuGuardarSistema() {
        System.out.println("\n-- Guardar Sistema --");
        System.out.print("Ruta y nombre del archivo (ej: empresa.dat): ");
        String ruta = scanner.nextLine().trim();
        try {
            ManejoArchivos.salvarSistema(ruta, empresa);
            System.out.println("Sistema guardado en: " + ruta);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void menuCargarSistema() {
        System.out.println("\n-- Cargar Sistema --");
        System.out.print("Ruta del archivo: ");
        String ruta = scanner.nextLine().trim();
        try {
            empresa = ManejoArchivos.cargarSistema(ruta);
            System.out.println("Sistema cargado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
