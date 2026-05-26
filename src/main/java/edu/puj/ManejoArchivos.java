package edu.puj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManejoArchivos {

    public static ArrayList<Cliente> leerClientes(String ruta) throws Exception {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) continue;
                if (linea.equals("#FIN")) break;
                String[] partes = linea.split("\\*");
                if (partes.length < 3) continue;
                String nombre = partes[0].trim();
                String cedula = partes[1].trim();
                String direccion = partes[2].trim();
                clientes.add(new Cliente(nombre, cedula, "CC", direccion));
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Archivo no encontrado: " + ruta);
        } catch (IOException e) {
            throw new Exception("Error al leer el archivo: " + e.getMessage());
        }
        return clientes;
    }
}
