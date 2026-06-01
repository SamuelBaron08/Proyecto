package edu.puj;

import java.util.Scanner;


public class TestConsola {

    private static IEmpresa empresa = new Empresa("JaveMóvil");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = -1;
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ingrese un número válido.\n");
                continue;
            }
            switch (opcion) {
                case 1:  menuCargarClientes();   break;
                case 2:  menuAgregarCuenta();    break;
                case 3:  menuRegistrarLlamada(); break;
                case 4:  menuAgregarRecarga();   break;
                case 5:  menuReportePostpago();  break;
                case 6:  menuReporteRecargas();  break;
                case 7:  menuGuardarSistema();   break;
                case 8:  menuCargarSistema();    break;
                case 0:  System.out.println("¡Hasta luego!"); break;
                default: System.out.println("⚠ Opción inválida.\n");
            }
        } while (opcion != 0);
        scanner.close();
    }


    private static void mostrarMenu() {

        System.out.println(" JAVEMÓVIL - SISTEMA DE FACTURACIÓN  ");
        System.out.println("  1. Cargar clientes desde archivo");
        System.out.println("  2. Agregar cuenta (prepago / postpago)");
        System.out.println("  3. Registrar llamada");
        System.out.println("  4. Agregar recarga");
        System.out.println("  5. Reporte facturación postpago");
        System.out.println("  6. Reporte recargas prepago");
        System.out.println("  7. Guardar sistema en archivo");
        System.out.println("  8. Cargar sistema desde archivo");
        System.out.println("  0. Salir");
        System.out.print("Seleccione una opción: ");
    }



    private static void menuCargarClientes() {
        System.out.println(" Cargar Clientes ");
        System.out.println("1. Seleccionar archivo de clientes");
        System.out.println("2. Regresar al menú");
        System.out.print("Opción: ");
        String op = scanner.nextLine().trim();
        if (op.equals("1")) {
            System.out.print("Ruta del archivo (ej: clientes.txt): ");
            String ruta = scanner.nextLine().trim();
            try {
                empresa.cargarClientes(ruta);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }


    private static void menuAgregarCuenta() {
        System.out.println(" Agregar Cuenta ");
        System.out.println("Tipo de cuenta:");
        System.out.println("  1. Prepago");
        System.out.println("  2. Postpago");
        System.out.print("Opción: ");
        String opTipo = scanner.nextLine().trim();
        String tipo;
        if (opTipo.equals("1")) {
            tipo = "prepago";
        } else if (opTipo.equals("2")) {
            tipo = "postpago";
        } else {
            System.out.println("Opción de tipo inválida.");
            return;
        }

        // Mostrar clientes disponibles
        System.out.println("\nClientes registrados:");
        if (empresa.getClientes().isEmpty()) {
            System.out.println("  (No hay clientes. Cargue clientes primero.)");
            return;
        }
        for (Cliente c : empresa.getClientes()) {
            System.out.println("  " + c.getIdentificacion() + " - " + c.getNombre() +
                    (c.tieneCuenta() ? " ya tiene cuenta" : ""));
        }

        System.out.print("Identificación del cliente: ");
        String idCliente = scanner.nextLine().trim();
        System.out.print("Número de teléfono: ");
        try {
            long numero = Long.parseLong(scanner.nextLine().trim());
            long id = empresa.agregarCuenta(tipo, idCliente, numero);
            System.out.println(" Cuenta " + tipo + " creada. ID asignado: " + id);
        } catch (NumberFormatException e) {
            System.out.println(" El número de teléfono debe ser numérico.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void menuRegistrarLlamada() {
        System.out.println(" Registrar Llamada ");
        mostrarCuentas();
        System.out.print("ID de la cuenta: ");
        try {
            long cuentaId = Long.parseLong(scanner.nextLine().trim());

            System.out.println("Tipo de llamada:");
            System.out.println("  1. Nacional");
            System.out.println("  2. Internacional");
            System.out.print("Opción: ");
            String tipoOp = scanner.nextLine().trim();

            System.out.print("Fecha (yyyy-MM-dd): ");
            String fecha = scanner.nextLine().trim();
            System.out.print("Teléfono destinatario: ");
            long tel = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Duración (minutos): ");
            long duracion = Long.parseLong(scanner.nextLine().trim());

            if (tipoOp.equals("1")) {
                empresa.registrarLlamadaNacional(cuentaId, fecha, tel, duracion);
            } else if (tipoOp.equals("2")) {
                System.out.println("Países disponibles: Argentina, Brasil, Chile, Colombia,");
                System.out.println("  Ecuador, España, Estados Unidos, México, Panamá,");
                System.out.println("  Perú, Venezuela, Francia, Alemania, Italia, Reino Unido");
                System.out.print("País de destino: ");
                String pais = scanner.nextLine().trim();
                empresa.registrarLlamadaInternacional(cuentaId, fecha, tel, duracion, pais);
            } else {
                System.out.println(" Opción de tipo de llamada inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ingrese valores numéricos válidos para ID, teléfono y duración.");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }



    private static void menuAgregarRecarga() {
        System.out.println("Agregar Recarga ");
        System.out.println("Cuentas prepago disponibles:");
        boolean hayPrepago = false;
        for (Cuenta c : empresa.getCuentas()) {
            if (c instanceof Prepago) {
                System.out.println("  ID: " + c.getId() + "  Número: " + c.getNumero());
                hayPrepago = true;
            }
        }
        if (!hayPrepago) {
            System.out.println("  (No hay cuentas prepago registradas)");
            return;
        }

        System.out.print("ID de la cuenta prepago: ");
        try {
            long cuentaId = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Fecha (yyyy-MM-dd): ");
            String fecha = scanner.nextLine().trim();
            System.out.print("Valor de la recarga: $");
            long valor = Long.parseLong(scanner.nextLine().trim());
            empresa.agregarRecarga(cuentaId, fecha, valor);
            // El mensaje de confirmación lo imprime Empresa.agregarRecarga()
        } catch (NumberFormatException e) {
            System.out.println(" Ingrese valores numéricos válidos.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void menuReportePostpago() {
        System.out.println(" Reporte Facturación Postpago ");
        System.out.print("Año: ");
        try {
            int anio = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Mes (1-12): ");
            int mes = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Clientes con cuenta postpago:");
            boolean hayPostpago = false;
            for (Cliente c : empresa.getClientes()) {
                if (c.tieneCuenta() && c.getCuenta() instanceof Postpago) {
                    System.out.println("  " + c.getIdentificacion() + " - " + c.getNombre());
                    hayPostpago = true;
                }
            }
            if (!hayPostpago) {
                System.out.println("  (No hay clientes con cuenta postpago)");
                return;
            }

            System.out.print("Identificación del cliente: ");
            String id = scanner.nextLine().trim();
            System.out.println(empresa.reportePostpago(anio, mes, id));
        } catch (NumberFormatException e) {
            System.out.println("⚠ Ingrese valores numéricos válidos para año y mes.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    private static void menuReporteRecargas() {
        System.out.println("\n── Reporte Recargas Prepago ──");
        System.out.print("Año: ");
        try {
            int anio = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Mes (1-12): ");
            int mes = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(empresa.reporteRecargas(anio, mes));
        } catch (NumberFormatException e) {
            System.out.println(" Ingrese valores numéricos válidos para año y mes.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void menuGuardarSistema() {
        System.out.println("Guardar Sistema ");
        System.out.print("Ruta y nombre del archivo (ej: empresa.dat): ");
        String ruta = scanner.nextLine().trim();
        try {
            ManejoArchivos.salvarSistema(ruta, empresa);
            System.out.println(" Sistema guardado en: " + ruta);
        } catch (Exception e) {
            System.out.println(" Error al guardar: " + e.getMessage());
        }
    }



    private static void menuCargarSistema() {
        System.out.println("Cargar Sistema ");
        System.out.print("Ruta del archivo: ");
        String ruta = scanner.nextLine().trim();
        try {
            empresa = ManejoArchivos.cargarSistema(ruta);
            System.out.println(" Sistema cargado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }


    private static void mostrarCuentas() {
        if (empresa.getCuentas().isEmpty()) {
            System.out.println("  (No hay cuentas registradas)");
            return;
        }
        System.out.println("Cuentas disponibles:");
        for (Cuenta c : empresa.getCuentas()) {
            String tipo = (c instanceof Prepago) ? "Prepago" : "Postpago";
            System.out.println("  ID: " + c.getId() +
                    "  Tipo: " + tipo +
                    "  Número: " + c.getNumero());
        }
    }
}
