package edu.puj;

import java.util.Scanner;

public class TestConsola {

    private static IEmpresa empresa = new Empresa("JaveMovil");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;
        do {
            mostrarMenu();
            switch (opcion) {
                case 1: menuCargarClientes(); break;
                case 2: menuAgregarCuenta(); break;
                case 3: menuRegistrarLlamada(); break;
                case 4: menuAgregarRecarga(); break;
                case 5: menuReportePostpago(); break;
                case 6: menuReporteRecargas(); break;
                case 7: menuGuardarSistema(); break;
                case 8: menuCargarSistema(); break;
                case 0: System.out.println("Hasta luego."); break;
                default: System.out.println("Opcion invalida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("   JAVEMOVIL - SISTEMA DE FACTURACION   ");
        System.out.println("========================================");
        System.out.println("1. Cargar clientes");
        System.out.println("2. Agregar cuenta (prepago/postpago)");
        System.out.println("3. Registrar llamada");
        System.out.println("4. Agregar recarga");
        System.out.println("5. Reporte facturacion postpago (fin de mes)");
        System.out.println("6. Reporte recargas prepago (fin de mes)");
        System.out.println("7. Guardar sistema en archivo");
        System.out.println("8. Cargar sistema desde archivo");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------");
    }

}
