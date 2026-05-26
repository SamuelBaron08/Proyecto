package edu.puj;

import java.util.Scanner;

public class TestConsola {

    private static IEmpresa empresa = new Empresa("JaveMovil");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Scanner sc = new Scanner();
        int opcion;
        do {
            mostrarMenu();
            System.out.println();("Seleccione una opcion: ");
            System.out.println("1. Cargar Clientes");
            System.out.println("2. Agregar Cuenta");
            System.out.println("3. Registrar Llamada");
            System.out.println("4. Agregar Recarga");
            System.out.println("5. Generar Reporte Postpago");
            System.out.println("6. Generar Reporte Recargas");
            System.out.println("7. Guardar Sistema");
            System.out.println("8. Cargar Sistema");
            opcion = sc.nextInt();
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

}
